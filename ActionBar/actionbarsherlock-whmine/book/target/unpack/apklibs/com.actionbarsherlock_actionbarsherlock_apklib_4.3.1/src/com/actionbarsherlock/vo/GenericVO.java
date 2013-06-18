package com.actionbarsherlock.vo;

import com.actionbarsherlock.exception.AppException;
import com.actionbarsherlock.exception.SysException;

public class GenericVO implements ISVO {
    public String mtableName;

    @Override
    public String getTableName() throws AppException, SysException {
        return mtableName;
    }

    @Override
    public String[] toArray() {
        return null;
    }
}
