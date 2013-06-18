package com.actionbarsherlock.dbutils;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class BaseSQLiteHelper extends SQLiteOpenHelper {
    public List<String> mCreateSql = new ArrayList<String>();
    public List<String> mUpgradeSql = new ArrayList<String>();

    public BaseSQLiteHelper(Context context, String name,
            CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public abstract void InitCreateSql();

    public abstract void InitUpgradeSql();

    @Override
    public void onCreate(SQLiteDatabase db) {
        InitCreateSql();
        for (int j = 0; j < mCreateSql.size(); j++) {
            db.execSQL(mCreateSql.get(j));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        InitUpgradeSql();
        for (int i = 0; i < mUpgradeSql.size(); i++) {
            db.execSQL(mUpgradeSql.get(i));
        }
    }

}
