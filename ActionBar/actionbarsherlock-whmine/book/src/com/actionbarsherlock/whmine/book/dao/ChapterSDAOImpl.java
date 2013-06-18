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
import com.actionbarsherlock.whmine.book.mode.ChapterSVO;

public class ChapterSDAOImpl implements ISDAO {

    private final static String TAG = "ChapterSDao";
    private SQLiteDatabase db;

    public ChapterSDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
        Cursor cursor = null;
        StringBuffer whereSelect = new StringBuffer(" 1=1 ");
        List<String> selectionArgs = new ArrayList<String>();
        ChapterSVO chapter = (ChapterSVO) vo;
        try {
            if (!StringUtil.isBlank(chapter.getId())) {
                whereSelect.append(" and id=? ");
                selectionArgs.add(chapter.getId());
            }
            cursor = db.query("chapter", null, whereSelect.toString(),
                    (String[]) selectionArgs.toArray(), null, null, "id");
            if (cursor.moveToFirst()) {
                ChapterSVO tempchapter = new ChapterSVO(cursor);
                return tempchapter;
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
            ChapterSVO chapter = (ChapterSVO) vo;
            StringBuffer sql = new StringBuffer("INSERT INTO chapter(_Id,title,bookID,chapterURL,downTime,sequence,volume,wordNums,position,chapterType,tempUrl)");
            sql.append("values(?,?,?,?,?,?,?,?,?,?,?)");
            db.execSQL(sql.toString(),chapter.toArray());
        } catch (SQLException e) {
            throw new AppException(TAG, "执行chapter数据插入异常："+e);
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
        ChapterSVO chapter = (ChapterSVO) vo;
        if (StringUtil.isBlank(chapter.getId())) {
            throw new AppException("100002", "缺少对象的主键！");
        }
        StringBuffer sql = new StringBuffer("UPDATE chapter SET ");
        List<String> bindArgs = new ArrayList<String>();
        if(!StringUtil.isBlank(chapter.getId())){
            sql.append(" id = ? ,");
            bindArgs.add(chapter.getId());
        }
        if(!StringUtil.isBlank(chapter.getTitle())){
            sql.append(" title = ? ,");
            bindArgs.add(chapter.getTitle());
        }
        if(!StringUtil.isBlank(chapter.getBookid())){
            sql.append(" bookid = ? ,");
            bindArgs.add(chapter.getBookid());
        }
        if(!StringUtil.isBlank(chapter.getChapterurl())){
            sql.append(" chapterurl = ? ,");
            bindArgs.add(chapter.getChapterurl());
        }
        if(!StringUtil.isBlank(chapter.getDowntime())){
            sql.append(" downtime = ? ,");
            bindArgs.add(chapter.getDowntime());
        }
        if(!StringUtil.isBlank(chapter.getSequence())){
            sql.append(" sequence = ? ,");
            bindArgs.add(chapter.getSequence());
        }
        if(!StringUtil.isBlank(chapter.getVolume())){
            sql.append(" volume = ? ,");
            bindArgs.add(chapter.getVolume());
        }
        if(!StringUtil.isBlank(chapter.getWordnums())){
            sql.append(" wordnums = ? ,");
            bindArgs.add(chapter.getWordnums());
        }
        if(!StringUtil.isBlank(chapter.getPosition())){
            sql.append(" position = ? ,");
            bindArgs.add(chapter.getPosition());
        }
        if(!StringUtil.isBlank(chapter.getChaptertype())){
            sql.append(" chaptertype = ? ,");
            bindArgs.add(chapter.getChaptertype());
        }
        if(!StringUtil.isBlank(chapter.getTempurl())){
            sql.append(" tempurl = ? ");
            bindArgs.add(chapter.getTempurl());
        }
        sql.append(" WHERE 1=1 ");
        sql.append("and id = ?");
        bindArgs.add(chapter.getId());
        db.execSQL(sql.toString(),bindArgs.toArray());
    }

    @Override
    public void delete(GenericVO vo) throws AppException, SysException {
       ChapterSVO chapter = (ChapterSVO) vo;
        StringBuffer sql = new StringBuffer("delete from chapter");
        if (StringUtil.isBlank(chapter.getId())) {
            throw new AppException("100002", "缺少对象的主键！");
        }else{
            sql.append(" where id = ? ");
        }
        db.execSQL(sql.toString(),new String[]{chapter.getId()});
    }

    @Override
    public void unable(GenericVO vo) throws AppException, SysException {
    }

    @Override
    public List findByVO(GenericVO vo) throws AppException, SysException {
        List<ChapterSVO> mListData = new ArrayList<ChapterSVO>();
        Cursor cursor = null;
        StringBuffer whereSelect = new StringBuffer(" 1=1 ");
        List<String> selectionArgs = new ArrayList<String>();
        ChapterSVO chapter = (ChapterSVO) vo;
        try {
            if(!StringUtil.isBlank(chapter.getId())){
                whereSelect.append(" and  id = ?,");
                selectionArgs.add(chapter.getId());
             }
            if(!StringUtil.isBlank(chapter.getTitle())){
                whereSelect.append(" and  title = ?,");
                selectionArgs.add(chapter.getTitle());
             }
            if(!StringUtil.isBlank(chapter.getBookid())){
                whereSelect.append(" and  bookid = ?,");
                selectionArgs.add(chapter.getBookid());
             }
            if(!StringUtil.isBlank(chapter.getChapterurl())){
                whereSelect.append(" and  chapterurl = ?,");
                selectionArgs.add(chapter.getChapterurl());
             }
            if(!StringUtil.isBlank(chapter.getDowntime())){
                whereSelect.append(" and  downtime = ?,");
                selectionArgs.add(chapter.getDowntime());
             }
            if(!StringUtil.isBlank(chapter.getSequence())){
                whereSelect.append(" and  sequence = ?,");
                selectionArgs.add(chapter.getSequence());
             }
            if(!StringUtil.isBlank(chapter.getVolume())){
                whereSelect.append(" and  volume = ?,");
                selectionArgs.add(chapter.getVolume());
             }
            if(!StringUtil.isBlank(chapter.getWordnums())){
                whereSelect.append(" and  wordnums = ?,");
                selectionArgs.add(chapter.getWordnums());
             }
            if(!StringUtil.isBlank(chapter.getPosition())){
                whereSelect.append(" and  position = ?,");
                selectionArgs.add(chapter.getPosition());
             }
            if(!StringUtil.isBlank(chapter.getChaptertype())){
                whereSelect.append(" and  chaptertype = ?,");
                selectionArgs.add(chapter.getChaptertype());
             }
            if(!StringUtil.isBlank(chapter.getTempurl())){
                whereSelect.append(" and  tempurl = ?,");
                selectionArgs.add(chapter.getTempurl());
             }
            cursor = db.query(vo.getTableName(), null, whereSelect.toString(),
                    (String[]) selectionArgs.toArray(), null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    mListData.add(new ChapterSVO(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            throw new AppException(TAG, "执行chapter数据查询异常："+e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return mListData;
    }

}
