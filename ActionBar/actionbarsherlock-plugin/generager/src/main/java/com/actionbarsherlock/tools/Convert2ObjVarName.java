package com.actionbarsherlock.tools;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class Convert2ObjVarName implements TemplateMethodModel {

    @Override
    public Object exec(List args) throws TemplateModelException {

        if (args.size() != 1) {
            throw new TemplateModelException("请检查输入参数，此方法只允许1个入参！");
        }
        String name = (String) args.get(0);
        char[] lowerTableName = name.toLowerCase().toCharArray();
        char prefix = '_';
        int daoNameSize = name.replace(String.valueOf(prefix), "").length();
        char[] daoName = new char[daoNameSize];

        // 首字母小写
        int daoNameIdx = 0;

        for (int i = 0; i < lowerTableName.length; i++) {
            if (lowerTableName[i] == prefix) {// 下划线后首字母大写
                if (i == 0) {
                    daoName[daoNameIdx] = Character
                            .toLowerCase(lowerTableName[i + 1]);
                } else {
                    daoName[daoNameIdx] = Character
                            .toUpperCase(lowerTableName[i + 1]);
                }
                i++;
            } else {
                daoName[daoNameIdx] = lowerTableName[i];
            }
            daoNameIdx++;
        }
        return new String(daoName);

    }

}
