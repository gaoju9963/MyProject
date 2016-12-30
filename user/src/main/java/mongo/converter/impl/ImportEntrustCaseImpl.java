package mongo.converter.impl;

import mongo.converter.*;
import mongo.modle.Employee;

import java.util.List;

/**
 * Created by pengshu on 2016/11/5.
 */
public class ImportEntrustCaseImpl implements ImportEntrustCase {
    @Override
    public List<Employee> importExcel(String filePath, String templatePath) {
        MappingManager mappingManager = null;
        ExcelManager excelManager = null;
        try {
            mappingManager = MapBuilder.BuilderConfig.getMapBuilder(templatePath).builderMapping();
            excelManager = ExcelBuilder.BuilderConfig.getExcelBuilder(filePath).builderExcel();

            reading(mappingManager, excelManager);
            return mappingManager.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void reading(MappingManager mappingManager, ExcelManager excelManager) throws Exception {
        mappingManager.mapHeader(excelManager.readHeader());
        while (excelManager.nextRow()) {
            mappingManager.mapValue(excelManager.getRow());
        }
    }
}
