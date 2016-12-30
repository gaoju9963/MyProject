package cn.converter;

import cn.function.TwoArg;

import java.util.List;

/**
 * Created by pengshu on 2016/11/5.
 */
public interface ExcelManager {
    List<String> readHeader();
    boolean nextRow();


    List getRow() throws Exception;
}
