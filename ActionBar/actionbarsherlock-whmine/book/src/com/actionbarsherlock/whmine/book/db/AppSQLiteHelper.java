package com.actionbarsherlock.whmine.book.db;

import com.actionbarsherlock.dbutils.BaseSQLiteHelper;
import com.actionbarsherlock.whmine.book.R;

import android.content.Context;

public class AppSQLiteHelper extends BaseSQLiteHelper {
    private Context context;

    public AppSQLiteHelper(Context context, String name, int version) {
        super(context, name, null, version);
        this.context = context;

    }

    @Override
    public void InitCreateSql() {
        String[] tableSql = context.getApplicationContext().getResources()
                .getStringArray(R.array.tableSql);
        for (int i = 0; i < tableSql.length; i++) {
            mCreateSql.add(tableSql[i]);
        }
    }

    @Override
    public void InitUpgradeSql() {
        String[] tableSql = context.getApplicationContext().getResources()
                .getStringArray(R.array.tableUpdateSql);
        for (int i = 0; i < tableSql.length; i++) {
            mUpgradeSql.add(tableSql[i]);
        }
    }
}
