package com.actionbarsherlock.whmine.book.mode;

import android.database.Cursor;
import com.actionbarsherlock.exception.AppException;
import com.actionbarsherlock.exception.SysException;
import com.actionbarsherlock.vo.GenericVO;

public class SiteinfoSVO extends GenericVO {
    private final static String TAG = "SiteinfoSVO";
    private String siteid;
    public String getSiteid() {
         return siteid;
    }
    public void setSiteid(String siteid) {
         this.siteid = siteid;
    }
    private String host;
    public String getHost() {
         return host;
    }
    public void setHost(String host) {
         this.host = host;
    }
    private String extra;
    public String getExtra() {
         return extra;
    }
    public void setExtra(String extra) {
         this.extra = extra;
    }
    private String sitename;
    public String getSitename() {
         return sitename;
    }
    public void setSitename(String sitename) {
         this.sitename = sitename;
    }
    public SiteinfoSVO() {
        mtableName = "siteInfo";
    }

    public SiteinfoSVO(Cursor cursor) throws AppException, SysException {

        mtableName = "siteInfo";
        this.siteid = cursor.getString(cursor.getColumnIndex("siteid"));
        this.host = cursor.getString(cursor.getColumnIndex("host"));
        this.extra = cursor.getString(cursor.getColumnIndex("extra"));
        this.sitename = cursor.getString(cursor.getColumnIndex("sitename"));
    }

    public String[] toArray() {
        String[] arr = new String[4];
        arr[0] = siteid;
        arr[1] = host;
        arr[2] = extra;
        arr[3] = sitename;
        return arr;
    }

}
