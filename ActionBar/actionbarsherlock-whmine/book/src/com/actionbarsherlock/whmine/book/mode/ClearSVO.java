package com.actionbarsherlock.whmine.book.mode;

import android.database.Cursor;
import com.actionbarsherlock.exception.AppException;
import com.actionbarsherlock.exception.SysException;
import com.actionbarsherlock.vo.GenericVO;

public class ClearSVO extends GenericVO {
    private final static String TAG = "ClearSVO";
    private String bookid;
    public String getBookid() {
         return bookid;
    }
    public void setBookid(String bookid) {
         this.bookid = bookid;
    }
    private String chaptersequences;
    public String getChaptersequences() {
         return chaptersequences;
    }
    public void setChaptersequences(String chaptersequences) {
         this.chaptersequences = chaptersequences;
    }
    public ClearSVO() {
        mtableName = "clear";
    }

    public ClearSVO(Cursor cursor) throws AppException, SysException {

        mtableName = "clear";
        this.bookid = cursor.getString(cursor.getColumnIndex("bookid"));
        this.chaptersequences = cursor.getString(cursor.getColumnIndex("chaptersequences"));
    }

    public String[] toArray() {
        String[] arr = new String[2];
        arr[0] = bookid;
        arr[1] = chaptersequences;
        return arr;
    }

}
