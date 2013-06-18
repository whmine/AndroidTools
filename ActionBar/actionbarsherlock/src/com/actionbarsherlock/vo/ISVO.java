package com.actionbarsherlock.vo;

import com.actionbarsherlock.exception.AppException;
import com.actionbarsherlock.exception.SysException;

public interface ISVO extends java.io.Serializable {
    public String getTableName()throws AppException,SysException;
    public String[] toArray();
}
