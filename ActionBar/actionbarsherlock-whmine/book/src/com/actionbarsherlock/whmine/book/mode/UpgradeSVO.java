package com.actionbarsherlock.whmine.book.mode;

import android.database.Cursor;
import com.actionbarsherlock.exception.AppException;
import com.actionbarsherlock.exception.SysException;
import com.actionbarsherlock.vo.GenericVO;

public class UpgradeSVO extends GenericVO {
    private final static String TAG = "UpgradeSVO";
    private String apkurl;
    public String getApkurl() {
         return apkurl;
    }
    public void setApkurl(String apkurl) {
         this.apkurl = apkurl;
    }
    private String badversion;
    public String getBadversion() {
         return badversion;
    }
    public void setBadversion(String badversion) {
         this.badversion = badversion;
    }
    private String description;
    public String getDescription() {
         return description;
    }
    public void setDescription(String description) {
         this.description = description;
    }
    private String releasedate;
    public String getReleasedate() {
         return releasedate;
    }
    public void setReleasedate(String releasedate) {
         this.releasedate = releasedate;
    }
    private String newversionname;
    public String getNewversionname() {
         return newversionname;
    }
    public void setNewversionname(String newversionname) {
         this.newversionname = newversionname;
    }
    private String newversioncode;
    public String getNewversioncode() {
         return newversioncode;
    }
    public void setNewversioncode(String newversioncode) {
         this.newversioncode = newversioncode;
    }
    private String minversioncode;
    public String getMinversioncode() {
         return minversioncode;
    }
    public void setMinversioncode(String minversioncode) {
         this.minversioncode = minversioncode;
    }
    private String id;
    public String getId() {
         return id;
    }
    public void setId(String id) {
         this.id = id;
    }
    public UpgradeSVO() {
        mtableName = "upgrade";
    }

    public UpgradeSVO(Cursor cursor) throws AppException, SysException {

        mtableName = "upgrade";
        this.apkurl = cursor.getString(cursor.getColumnIndex("apkurl"));
        this.badversion = cursor.getString(cursor.getColumnIndex("badversion"));
        this.description = cursor.getString(cursor.getColumnIndex("description"));
        this.releasedate = cursor.getString(cursor.getColumnIndex("releasedate"));
        this.newversionname = cursor.getString(cursor.getColumnIndex("newversionname"));
        this.newversioncode = cursor.getString(cursor.getColumnIndex("newversioncode"));
        this.minversioncode = cursor.getString(cursor.getColumnIndex("minversioncode"));
        this.id = cursor.getString(cursor.getColumnIndex("id"));
    }

    public String[] toArray() {
        String[] arr = new String[8];
        arr[0] = apkurl;
        arr[1] = badversion;
        arr[2] = description;
        arr[3] = releasedate;
        arr[4] = newversionname;
        arr[5] = newversioncode;
        arr[6] = minversioncode;
        arr[7] = id;
        return arr;
    }

}
