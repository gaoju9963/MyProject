package cn.converter.impl;

import cn.converter.ExcelBuilder;
import cn.converter.ExcelManager;
import cn.converter.entity.MapEntity;
import cn.function.TwoArg;
import com.model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by pengshu on 2016/11/5.
 */
public class ExcelManagerImpl implements ExcelManager {

    private ExcelBuilder.BuilderConfig builderConfig;

    public ExcelManagerImpl(ExcelBuilder.BuilderConfig builderConfig) {
        this.builderConfig = builderConfig;
    }

    @Override
    public List<String> readHeader() {
        Workbook wb = builderConfig.getWorkbook();
        Row row = wb.getSheetAt(0).getRow(0);
        int column = 0;
        List headerInfo = new ArrayList<>();
        while (true) {
            Cell cell = row.getCell(column);
            if (null == cell) {
                break;
            }
            headerInfo.add(column, String.valueOf(cell));
            column++;
        }
        return headerInfo;
    }

    @Override
    public boolean nextRow() {
        Workbook wb = builderConfig.getWorkbook();
        int rowEnd = wb.getSheetAt(0).getLastRowNum();
        if (builderConfig.getRowIndex() <= rowEnd) {
            return true;
        }
        return false;
    }

    @Override
    public List getRow() throws Exception {
        Workbook wb = builderConfig.getWorkbook();
        Sheet sheet = wb.getSheetAt(0);
        int colEnd = sheet.getRow(0).getLastCellNum();
        Row row = sheet.getRow(builderConfig.getRowIndex());
        List arr = new ArrayList<>();
        Cell cell = null;

        for (int col = 0; col <= colEnd; col++) {
            cell = row.getCell(col);
            arr.add(getCellValue(cell));
        }
        builderConfig.setRowIndex(builderConfig.getRowIndex() + 1);
        return arr;
    }

    private Object getCellValue(Cell cell) throws Exception {
        if (null == cell) {
            return new TwoArg("String", "");
        }
        /*short dataformat = cell.getCellStyle().getDataFormat();
        if (dataformat >= 14 && dataformat <= 22) {
            if (cell.getCellType() == 1) {
                throw new ServiceException(ServiceError.SYS_FILE_UPLOAD_DATE_FORMAT_NOT_MATCH);
            }
            return new TwoArg("Date", cell.getDateCellValue());
        }*/
        String value = String.valueOf(cell);
        if (isDigital(value)) {
            value = removeExcessZero(value);
        }
        return new TwoArg("String", value.trim());
    }

    private boolean isDigital(String str) {
        String reg = "^(-?\\d+)(\\.\\d+)?$";
        return Pattern.compile(reg).matcher(str).find();
    }

    private String removeExcessZero(String str) {
        if (str.indexOf(".") > 0) {
            str = str.replaceAll("0+?$", "");//去掉多余的0
            str = str.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return str;
    }
}
