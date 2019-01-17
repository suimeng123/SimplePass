package com.lx.simplepass.parser;

import com.base.library.http.parser.BaseParser;

/**
 * com.lx.simplepass
 * SimplePass
 * Created by lixiao2
 * 2019/1/10.
 */

public class MyParser extends BaseParser {

    private int code = 200;
    private String codeName = "retCode";
    private String message = "msg";
    private String dataName = "result";

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getCodeName() {
        return codeName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDataName() {
        return dataName;
    }
}
