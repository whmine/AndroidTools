package com.actionbarsherlock.whmine.book.mode;

import android.database.Cursor;
import com.actionbarsherlock.exception.AppException;
import com.actionbarsherlock.exception.SysException;
import com.actionbarsherlock.vo.GenericVO;

public class ChapterSVO extends GenericVO {
    private final static String TAG = "ChapterSVO";
    private String id;
    public String getId() {
         return id;
    }
    public void setId(String id) {
         this.id = id;
    }
    private String title;
    public String getTitle() {
         return title;
    }
    public void setTitle(String title) {
         this.title = title;
    }
    private String bookid;
    public String getBookid() {
         return bookid;
    }
    public void setBookid(String bookid) {
         this.bookid = bookid;
    }
    private String chapterurl;
    public String getChapterurl() {
         return chapterurl;
    }
    public void setChapterurl(String chapterurl) {
         this.chapterurl = chapterurl;
    }
    private String downtime;
    public String getDowntime() {
         return downtime;
    }
    public void setDowntime(String downtime) {
         this.downtime = downtime;
    }
    private String sequence;
    public String getSequence() {
         return sequence;
    }
    public void setSequence(String sequence) {
         this.sequence = sequence;
    }
    private String volume;
    public String getVolume() {
         return volume;
    }
    public void setVolume(String volume) {
         this.volume = volume;
    }
    private String wordnums;
    public String getWordnums() {
         return wordnums;
    }
    public void setWordnums(String wordnums) {
         this.wordnums = wordnums;
    }
    private String position;
    public String getPosition() {
         return position;
    }
    public void setPosition(String position) {
         this.position = position;
    }
    private String chaptertype;
    public String getChaptertype() {
         return chaptertype;
    }
    public void setChaptertype(String chaptertype) {
         this.chaptertype = chaptertype;
    }
    private String tempurl;
    public String getTempurl() {
         return tempurl;
    }
    public void setTempurl(String tempurl) {
         this.tempurl = tempurl;
    }
    public ChapterSVO() {
        mtableName = "chapter";
    }

    public ChapterSVO(Cursor cursor) throws AppException, SysException {

        mtableName = "chapter";
        this.id = cursor.getString(cursor.getColumnIndex("id"));
        this.title = cursor.getString(cursor.getColumnIndex("title"));
        this.bookid = cursor.getString(cursor.getColumnIndex("bookid"));
        this.chapterurl = cursor.getString(cursor.getColumnIndex("chapterurl"));
        this.downtime = cursor.getString(cursor.getColumnIndex("downtime"));
        this.sequence = cursor.getString(cursor.getColumnIndex("sequence"));
        this.volume = cursor.getString(cursor.getColumnIndex("volume"));
        this.wordnums = cursor.getString(cursor.getColumnIndex("wordnums"));
        this.position = cursor.getString(cursor.getColumnIndex("position"));
        this.chaptertype = cursor.getString(cursor.getColumnIndex("chaptertype"));
        this.tempurl = cursor.getString(cursor.getColumnIndex("tempurl"));
    }

    public String[] toArray() {
        String[] arr = new String[11];
        arr[0] = id;
        arr[1] = title;
        arr[2] = bookid;
        arr[3] = chapterurl;
        arr[4] = downtime;
        arr[5] = sequence;
        arr[6] = volume;
        arr[7] = wordnums;
        arr[8] = position;
        arr[9] = chaptertype;
        arr[10] = tempurl;
        return arr;
    }

}
