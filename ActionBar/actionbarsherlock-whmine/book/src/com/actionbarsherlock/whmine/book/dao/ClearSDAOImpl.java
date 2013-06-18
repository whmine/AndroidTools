package com.actionbarsherlock.whmine.book.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.actionbarsherlock.dao.ISDAO;
import com.actionbarsherlock.exception.AppException;
import com.actionbarsherlock.exception.SysException;
import com.actionbarsherlock.utils.StringUtil;
import com.actionbarsherlock.vo.GenericVO;
import com.actionbarsherlock.whmine.book.mode.ClearSVO;

public class ClearSDAOImpl implements ISDAO {

    private final static String TAG = "ClearSDao";
    private SQLiteDatabase db;

    public ClearSDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
        Cursor cursor = null;
        StringBuffer whereSelect = new StringBuffer(" 1=1 ");
        List<String> selectionArgs = new ArrayList<String>();
        ClearSVO clear = (ClearSVO) vo;
        try {
            if (!StringUtil.isBlank(clear.getBookid())) {
                whereSelect.append(" and bookid=? ");
                selectionArgs.add(clear.getBookid());
            }
            cursor = db.query("clear", null, whereSelect.toString(),
                    (String[]) selectionArgs.toArray(), null, null, "bookid");
            if (cursor.moveToFirst()) {
                ClearSVO tempclear = new ClearSVO(cursor);
                return tempclear;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    @Override
    public void add(GenericVO vo) throws AppException, SysException {
        try {
            ClearSVO clear = (ClearSVO) vo;
            StringBuffer sql = new StringBuffer("INSERT INTO clear(bookId,chapterSequences)");
            sql.append("values(?,?)");
            db.execSQL(sql.toString(),clear.toArray());
        } catch (SQLException e) {
            throw new AppException(TAG, "执行clear数据插入异常："+e);
        }
    }

    @Override
    public void addBat(List vos) throws AppException, SysException {

    }

    @Override
    public void update(GenericVO vo) throws AppException, SysException {
        if (vo == null) {
            throw new AppException("100001", "缺少DAO操作对象！");
        }
        ClearSVO clear = (ClearSVO) vo;
        if (StringUtil.isBlank(clear.getBookid())) {
            throw new AppException("100002", "缺少对象的主键！");
        }
        StringBuffer sql = new StringBuffer("UPDATE clear SET ");
        List<String> bindArgs = new ArrayList<String>();
        if(!StringUtil.isBlank(clear.getBookid())){
            sql.append(" bookid = ? ,");
            bindArgs.add(clear.getBookid());
        }
        if(!StringUtil.isBlank(clear.getChaptersequences())){
            sql.append(" chaptersequences = ? ");
            bindArgs.add(clear.getChaptersequences());
        }
        sql.append(" WHERE 1=1 ");
        sql.append("and bookid = ?");
        bindArgs.add(clear.getBookid());
        db.execSQL(sql.toString(),bindArgs.toArray());
    }

    @Override
    public void delete(GenericVO vo) throws AppException, SysException {
       ClearSVO clear = (ClearSVO) vo;
        StringBuffer sql = new StringBuffer("delete from clear");
        if (StringUtil.isBlank(clear.getBookid())) {
            throw new AppException("100002", "缺少对象的主键！");
        }else{
            sql.append(" where bookid = ? ");
        }
        db.execSQL(sql.toString(),new String[]{clear.getBookid()});
    }

    @Override
    public void unable(GenericVO vo) throws AppException, SysException {
    }

    @Override
    public List findByVO(GenericVO vo) throws AppException, SysException {
        List<ClearSVO> mListData = new ArrayList<ClearSVO>();
        Cursor cursor = null;
        StringBuffer whereSelect = new StringBuffer(" 1=1 ");
        List<String> selectionArgs = new ArrayList<String>();
        ClearSVO clear = (ClearSVO) vo;
        try {
            if(!StringUtil.isBlank(clear.getBookid())){
                whereSelect.append(" and  bookid = ?,");
                selectionArgs.add(clear.getBookid());
             }
            if(!StringUtil.isBlank(clear.getChaptersequences())){
                whereSelect.append(" and  chaptersequences = ?,");
                selectionArgs.add(clear.getChaptersequences());
             }
            cursor = db.query(vo.getTableName(), null, whereSelect.toString(),
                    (String[]) selectionArgs.toArray(), null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    mListData.add(new ClearSVO(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            throw new AppException(TAG, "执行clear数据查询异常："+e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return mListData;
    }

}
