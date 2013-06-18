package com.actionbarsherlock.tools;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoMetaData {
    private String moduleName;
    private String tableName;
    private String className;
    private List columns = new ArrayList();
    private List pkColNames = new ArrayList();
    private List pkColumns = new ArrayList();

    public DaoMetaData(DatabaseMetaData dbMeta, String moduleName,
            String tableName) throws SQLException {
        this.setColumns(dbMeta, tableName);
        this.setPkColNames(dbMeta, tableName);
        this.setTableName(tableName);
        this.setClassName(tableName);
        this.setModuleName(moduleName);
        this.setPkColumns();
    }

    public List getPkColumns() {
        return pkColumns;
    }

    public void setPkColumns(List pkColumns) {
        this.pkColumns = pkColumns;
    }

    /**
     * 得到对应表的所有字段，将字段名称、数据类型、是否为空属性取出
     * @param dbMeta
     * @param table
     * @throws SQLException
     */

    public void setColumns(DatabaseMetaData dbMeta, String table)
            throws SQLException {
        List cols = new ArrayList();
        ResultSet colRs = dbMeta.getColumns(null, "SPS_DEV", table, null);
        while (colRs.next()) {
            ColumnMetaData colMeta = new ColumnMetaData();
            colMeta.setName(colRs.getString("COLUMN_NAME"));
            colMeta.setType(colRs.getString("TYPE_NAME"));// DATA_TYPE
            colMeta.setIsNullAble(colRs.getString("IS_NULLABLE"));
            cols.add(colMeta);
        }
        DBHelper.close(colRs);
        this.columns = cols;
    }

    /**
     * 得到主键列名
     * @param dbMeta
     * @param table
     * @throws SQLException
     */

    public void setPkColNames(DatabaseMetaData dbMeta, String table)
            throws SQLException {
        List pkColumnNames = new ArrayList();
        ResultSet pkColRs = dbMeta.getPrimaryKeys(null, "SPSDEV", table);
        while (pkColRs.next()) {
            String pkCol = pkColRs.getString("COLUMN_NAME");
            pkColumnNames.add(pkCol);
        }
        this.pkColNames = pkColumnNames;
        DBHelper.close(pkColRs);
    }

    /**
     * 如果该表主键为联合主键，则得到所有主键列的集合
     */
    public void setPkColumns() {
        for (int i = 0; i < this.pkColNames.size(); i++) {
            for (int j = 0; j < this.columns.size(); j++) {
                ColumnMetaData colMeta = (ColumnMetaData) columns.get(j);
                String pkColName = (String) pkColNames.get(i);
                if (pkColName.equals(colMeta.getName())) {
                    this.pkColumns.add(colMeta);
                }
            }
        }
    }

    /**
     * 根据表名生成类名 （首字母大写，去下划线后一位大写）
     * @param name
     * @return
     */
    public static String convert2ClassName(String name) {

        char[] lowerTableName = name.toLowerCase().toCharArray();
        char prefix = '_';
        int daoNameSize = name.replace(String.valueOf(prefix), "").length();
        char[] daoName = new char[daoNameSize];
        int daoNameIdx = 1;
        // 首字母大写
        if (daoNameSize > 0) {
            daoName[0] = Character.toUpperCase(lowerTableName[0]);
        }

        for (int i = 1; i < lowerTableName.length; i++) {
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

    /**
     * 根据字段名生成方法名 （首字母大写，去下划线后一位大写）
     * @param name
     * @return
     */
    public static String convert2MethodName(String name) {
        return DaoMetaData.convert2ClassName(name);
    }

    /**
     * 根据表名生成对象名 （首字母小写，去下划线后一位大写）
     * @param name
     * @return
     */
    public static String convert2ObjName(String name) {
        char[] lowerTableName = name.toLowerCase().toCharArray();
        char prefix = '_';
        int daoNameSize = name.replace(String.valueOf(prefix), "").length();
        char[] daoName = new char[daoNameSize];

        // 首字母小写
        int daoNameIdx = 0;

        for (int i = 0; i < lowerTableName.length; i++) {
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

    /**
     * 根据字段名生成变量名 （首字母小写，去下划线后一位大写）
     * @param name
     * @return
     */
    public static String convert2VarName(String name) {
        return DaoMetaData.convert2ObjName(name);
    }

    public static void main(String[] args) {
        System.out.println(DaoMetaData
                .convert2ClassName("INTER_SERVICE_PFORDERS"));
        System.out.println(DaoMetaData
                .convert2ObjName("INTER_SERVICE_PFORDERS"));
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String tableName) {
        this.className = convert2ClassName(tableName);
    }

    public List getColumns() {
        return columns;
    }

    public void setColumns(List columns) {
        this.columns = columns;
    }

    public List getPkColNames() {
        return pkColNames;
    }

    public void setPkColNames(List pkColNames) {
        this.pkColNames = pkColNames;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getObjName() {
        return convert2ObjName(tableName);
    }

    public void setObjName(String objName) {
        //
    }

    public String getColumnsStr() {
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < columns.size(); i++) {
            str.append(",");
            ColumnMetaData column = (ColumnMetaData) columns.get(i);
            str.append(column.getName());
        }

        return str.substring(1);
    }

    public String getColumnsParamStr() {
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < columns.size(); i++) {
            str.append(",:");
            ColumnMetaData column = (ColumnMetaData) columns.get(i);
            str.append(convert2ObjName(column.getName()));
        }

        return str.substring(1);
    }
}
