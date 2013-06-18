package com.actionbarsherlock.tools;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class ConvertColumnTypeName implements TemplateMethodModel {

    @Override
    public Object exec(List args) throws TemplateModelException {

        if (args.size() != 1) {
            throw new TemplateModelException("");
        }
        String columnType = (String) args.get(0);
        if ("NUMBER".equals(columnType)) {
            return "Long";
        } else if ("CLOB".equals(columnType)) {
            return "CLOB";
        } else if ("BLOB".equals(columnType)) {
            return "BLOB";
        } else if ("DATE".equals(columnType)) {
            return "Timestamp";
        } else {
            return "String";
        }
    }

}
