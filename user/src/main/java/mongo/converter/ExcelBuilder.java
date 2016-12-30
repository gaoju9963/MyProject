package mongo.converter;

import mongo.converter.impl.ExcelManagerImpl;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by pengshu on 2016/11/5.
 */
public class ExcelBuilder {

    private BuilderConfig builderConfig;

    private ExcelBuilder(BuilderConfig builderConfig) {
        this.builderConfig = builderConfig;
    }

    public ExcelManager builderExcel() {
        ExcelManager excelManager = new ExcelManagerImpl(builderConfig);
        return excelManager;
    }



    public static class BuilderConfig {
        private Workbook wb;
        private Integer rowIndex=1;

        private BuilderConfig(String filePath) throws Exception {
            wb = readExcelFile(filePath);
        }

        private BuilderConfig(File file) throws Exception {
            wb = readExcelFile(file);
        }

        private Workbook readExcelFile(String filePath) throws Exception {
            File file = new File(filePath);
            InputStream stream = new FileInputStream(file);
            return WorkbookFactory.create(file);
//            return new XSSFWorkbook(stream);
        }

        private Workbook readExcelFile(File file) throws Exception {
            InputStream stream = new FileInputStream(file);
            return WorkbookFactory.create(file);
//            return new XSSFWorkbook(stream);
        }

        public Workbook getWorkbook() {
            return wb;
        }

        public void setRowIndex(int rowIndex) {
            this.rowIndex = rowIndex;
        }

        public int getRowIndex(){
            return rowIndex;
        }

        public static ExcelBuilder getExcelBuilder(String filePath) throws Exception {
            return new ExcelBuilder(new BuilderConfig(filePath));
        }

        public static ExcelBuilder getExcelBuilder(File file) throws Exception {
            return new ExcelBuilder(new BuilderConfig(file));
        }
    }
}
