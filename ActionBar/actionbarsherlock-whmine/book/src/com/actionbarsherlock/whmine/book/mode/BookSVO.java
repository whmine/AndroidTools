package com.actionbarsherlock.whmine.book.mode;

import android.database.Cursor;
import com.actionbarsherlock.exception.AppException;
import com.actionbarsherlock.exception.SysException;
import com.actionbarsherlock.vo.GenericVO;

public class BookSVO extends GenericVO {
    private final static String TAG = "BookSVO";
    private String author;
    public String getAuthor() {
         return author;
    }
    public void setAuthor(String author) {
         this.author = author;
    }
    private String uri;
    public String getUri() {
         return uri;
    }
    public void setUri(String uri) {
         this.uri = uri;
    }
    private String updatetime;
    public String getUpdatetime() {
         return updatetime;
    }
    public void setUpdatetime(String updatetime) {
         this.updatetime = updatetime;
    }
    private String brief;
    public String getBrief() {
         return brief;
    }
    public void setBrief(String brief) {
         this.brief = brief;
    }
    private String sitename;
    public String getSitename() {
         return sitename;
    }
    public void setSitename(String sitename) {
         this.sitename = sitename;
    }
    private String categoryname;
    public String getCategoryname() {
         return categoryname;
    }
    public void setCategoryname(String categoryname) {
         this.categoryname = categoryname;
    }
    private String coverurl;
    public String getCoverurl() {
         return coverurl;
    }
    public void setCoverurl(String coverurl) {
         this.coverurl = coverurl;
    }
    private String newchapterurl;
    public String getNewchapterurl() {
         return newchapterurl;
    }
    public void setNewchapterurl(String newchapterurl) {
         this.newchapterurl = newchapterurl;
    }
    private String newchaptername;
    public String getNewchaptername() {
         return newchaptername;
    }
    public void setNewchaptername(String newchaptername) {
         this.newchaptername = newchaptername;
    }
    private String name;
    public String getName() {
         return name;
    }
    public void setName(String name) {
         this.name = name;
    }
    private String lastread;
    public String getLastread() {
         return lastread;
    }
    public void setLastread(String lastread) {
         this.lastread = lastread;
    }
    private String lastreadtime;
    public String getLastreadtime() {
         return lastreadtime;
    }
    public void setLastreadtime(String lastreadtime) {
         this.lastreadtime = lastreadtime;
    }
    private String downloadtime;
    public String getDownloadtime() {
         return downloadtime;
    }
    public void setDownloadtime(String downloadtime) {
         this.downloadtime = downloadtime;
    }
    private String id;
    public String getId() {
         return id;
    }
    public void setId(String id) {
         this.id = id;
    }
    private String needupdate;
    public String getNeedupdate() {
         return needupdate;
    }
    public void setNeedupdate(String needupdate) {
         this.needupdate = needupdate;
    }
    private String newchapterid;
    public String getNewchapterid() {
         return newchapterid;
    }
    public void setNewchapterid(String newchapterid) {
         this.newchapterid = newchapterid;
    }
    private String maxreadchapterid;
    public String getMaxreadchapterid() {
         return maxreadchapterid;
    }
    public void setMaxreadchapterid(String maxreadchapterid) {
         this.maxreadchapterid = maxreadchapterid;
    }
    private String defaultbook;
    public String getDefaultbook() {
         return defaultbook;
    }
    public void setDefaultbook(String defaultbook) {
         this.defaultbook = defaultbook;
    }
    private String sequence;
    public String getSequence() {
         return sequence;
    }
    public void setSequence(String sequence) {
         this.sequence = sequence;
    }
    private String siteid;
    public String getSiteid() {
         return siteid;
    }
    public void setSiteid(String siteid) {
         this.siteid = siteid;
    }
    private String categoryid;
    public String getCategoryid() {
         return categoryid;
    }
    public void setCategoryid(String categoryid) {
         this.categoryid = categoryid;
    }
    private String temp;
    public String getTemp() {
         return temp;
    }
    public void setTemp(String temp) {
         this.temp = temp;
    }
    private String type;
    public String getType() {
         return type;
    }
    public void setType(String type) {
         this.type = type;
    }
    private String autodownload;
    public String getAutodownload() {
         return autodownload;
    }
    public void setAutodownload(String autodownload) {
         this.autodownload = autodownload;
    }
    private String autodelete;
    public String getAutodelete() {
         return autodelete;
    }
    public void setAutodelete(String autodelete) {
         this.autodelete = autodelete;
    }
    private String userid;
    public String getUserid() {
         return userid;
    }
    public void setUserid(String userid) {
         this.userid = userid;
    }
    public BookSVO() {
        mtableName = "book";
    }

    public BookSVO(Cursor cursor) throws AppException, SysException {

        mtableName = "book";
        this.author = cursor.getString(cursor.getColumnIndex("author"));
        this.uri = cursor.getString(cursor.getColumnIndex("uri"));
        this.updatetime = cursor.getString(cursor.getColumnIndex("updatetime"));
        this.brief = cursor.getString(cursor.getColumnIndex("brief"));
        this.sitename = cursor.getString(cursor.getColumnIndex("sitename"));
        this.categoryname = cursor.getString(cursor.getColumnIndex("categoryname"));
        this.coverurl = cursor.getString(cursor.getColumnIndex("coverurl"));
        this.newchapterurl = cursor.getString(cursor.getColumnIndex("newchapterurl"));
        this.newchaptername = cursor.getString(cursor.getColumnIndex("newchaptername"));
        this.name = cursor.getString(cursor.getColumnIndex("name"));
        this.lastread = cursor.getString(cursor.getColumnIndex("lastread"));
        this.lastreadtime = cursor.getString(cursor.getColumnIndex("lastreadtime"));
        this.downloadtime = cursor.getString(cursor.getColumnIndex("downloadtime"));
        this.id = cursor.getString(cursor.getColumnIndex("id"));
        this.needupdate = cursor.getString(cursor.getColumnIndex("needupdate"));
        this.newchapterid = cursor.getString(cursor.getColumnIndex("newchapterid"));
        this.maxreadchapterid = cursor.getString(cursor.getColumnIndex("maxreadchapterid"));
        this.defaultbook = cursor.getString(cursor.getColumnIndex("defaultbook"));
        this.sequence = cursor.getString(cursor.getColumnIndex("sequence"));
        this.siteid = cursor.getString(cursor.getColumnIndex("siteid"));
        this.categoryid = cursor.getString(cursor.getColumnIndex("categoryid"));
        this.temp = cursor.getString(cursor.getColumnIndex("temp"));
        this.type = cursor.getString(cursor.getColumnIndex("type"));
        this.autodownload = cursor.getString(cursor.getColumnIndex("autodownload"));
        this.autodelete = cursor.getString(cursor.getColumnIndex("autodelete"));
        this.userid = cursor.getString(cursor.getColumnIndex("userid"));
    }

    public String[] toArray() {
        String[] arr = new String[26];
        arr[0] = author;
        arr[1] = uri;
        arr[2] = updatetime;
        arr[3] = brief;
        arr[4] = sitename;
        arr[5] = categoryname;
        arr[6] = coverurl;
        arr[7] = newchapterurl;
        arr[8] = newchaptername;
        arr[9] = name;
        arr[10] = lastread;
        arr[11] = lastreadtime;
        arr[12] = downloadtime;
        arr[13] = id;
        arr[14] = needupdate;
        arr[15] = newchapterid;
        arr[16] = maxreadchapterid;
        arr[17] = defaultbook;
        arr[18] = sequence;
        arr[19] = siteid;
        arr[20] = categoryid;
        arr[21] = temp;
        arr[22] = type;
        arr[23] = autodownload;
        arr[24] = autodelete;
        arr[25] = userid;
        return arr;
    }

}
