package com.actionbarsherlock.tools;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class Convert2ClassMethodName implements TemplateMethodModel {

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
        int daoNameIdx = 1;
        // 首字母大写
        if (daoNameSize > 0) {
            if (lowerTableName[0] == prefix) {
                daoName[0] = Character.toUpperCase(lowerTableName[1]);
            } else {
                daoName[0] = Character.toUpperCase(lowerTableName[0]);
            }

        }
        int i = 1;
        if (lowerTableName[0] == prefix) {
            i = 2;
        }
        for (; i < lowerTableName.length; i++) {
            if (lowerTableName[i] == prefix) {// 下划线后首字母大写
                daoName[daoNameIdx] = Character
                        .toUpperCase(lowerTableName[i + 1]);
                i++;
            } else {
                daoName[daoNameIdx] = lowerTableName[i];
            }
            daoNameIdx++;
        }
        return new String(daoName);

    }

}
