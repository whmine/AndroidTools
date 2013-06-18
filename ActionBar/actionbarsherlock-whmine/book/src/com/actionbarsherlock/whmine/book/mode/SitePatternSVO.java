package com.actionbarsherlock.whmine.book.mode;

import android.database.Cursor;
import com.actionbarsherlock.exception.AppException;
import com.actionbarsherlock.exception.SysException;
import com.actionbarsherlock.vo.GenericVO;

public class SitePatternSVO extends GenericVO {
    private final static String TAG = "SitePatternSVO";
    private String id;
    public String getId() {
         return id;
    }
    public void setId(String id) {
         this.id = id;
    }
    private String url;
    public String getUrl() {
         return url;
    }
    public void setUrl(String url) {
         this.url = url;
    }
    private String sitename;
    public String getSitename() {
         return sitename;
    }
    public void setSitename(String sitename) {
         this.sitename = sitename;
    }
    private String startmark;
    public String getStartmark() {
         return startmark;
    }
    public void setStartmark(String startmark) {
         this.startmark = startmark;
    }
    private String endmark;
    public String getEndmark() {
         return endmark;
    }
    public void setEndmark(String endmark) {
         this.endmark = endmark;
    }
    private String deletemark;
    public String getDeletemark() {
         return deletemark;
    }
    public void setDeletemark(String deletemark) {
         this.deletemark = deletemark;
    }
    public SitePatternSVO() {
        mtableName = "site_pattern";
    }

    public SitePatternSVO(Cursor cursor) throws AppException, SysException {

        mtableName = "site_pattern";
        this.id = cursor.getString(cursor.getColumnIndex("id"));
        this.url = cursor.getString(cursor.getColumnIndex("url"));
        this.sitename = cursor.getString(cursor.getColumnIndex("sitename"));
        this.startmark = cursor.getString(cursor.getColumnIndex("startmark"));
        this.endmark = cursor.getString(cursor.getColumnIndex("endmark"));
        this.deletemark = cursor.getString(cursor.getColumnIndex("deletemark"));
    }

    public String[] toArray() {
        String[] arr = new String[6];
        arr[0] = id;
        arr[1] = url;
        arr[2] = sitename;
        arr[3] = startmark;
        arr[4] = endmark;
        arr[5] = deletemark;
        return arr;
    }

}
