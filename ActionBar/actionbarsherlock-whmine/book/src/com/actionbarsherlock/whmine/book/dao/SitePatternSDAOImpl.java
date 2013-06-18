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
import com.actionbarsherlock.whmine.book.mode.SitePatternSVO;

public class SitePatternSDAOImpl implements ISDAO {

    private final static String TAG = "SitePatternSDao";
    private SQLiteDatabase db;

    public SitePatternSDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
        Cursor cursor = null;
        StringBuffer whereSelect = new StringBuffer(" 1=1 ");
        List<String> selectionArgs = new ArrayList<String>();
        SitePatternSVO sitePattern = (SitePatternSVO) vo;
        try {
            if (!StringUtil.isBlank(sitePattern.getId())) {
                whereSelect.append(" and id=? ");
                selectionArgs.add(sitePattern.getId());
            }
            cursor = db.query("site_pattern", null, whereSelect.toString(),
                    (String[]) selectionArgs.toArray(), null, null, "id");
            if (cursor.moveToFirst()) {
                SitePatternSVO tempsitePattern = new SitePatternSVO(cursor);
                return tempsitePattern;
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
            SitePatternSVO sitePattern = (SitePatternSVO) vo;
            StringBuffer sql = new StringBuffer("INSERT INTO site_pattern(_Id,url,siteName,startMark,endMark,deleteMark)");
            sql.append("values(?,?,?,?,?,?)");
            db.execSQL(sql.toString(),sitePattern.toArray());
        } catch (SQLException e) {
            throw new AppException(TAG, "执行sitePattern数据插入异常："+e);
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
        SitePatternSVO sitePattern = (SitePatternSVO) vo;
        if (StringUtil.isBlank(sitePattern.getId())) {
            throw new AppException("100002", "缺少对象的主键！");
        }
        StringBuffer sql = new StringBuffer("UPDATE site_pattern SET ");
        List<String> bindArgs = new ArrayList<String>();
        if(!StringUtil.isBlank(sitePattern.getId())){
            sql.append(" id = ? ,");
            bindArgs.add(sitePattern.getId());
        }
        if(!StringUtil.isBlank(sitePattern.getUrl())){
            sql.append(" url = ? ,");
            bindArgs.add(sitePattern.getUrl());
        }
        if(!StringUtil.isBlank(sitePattern.getSitename())){
            sql.append(" sitename = ? ,");
            bindArgs.add(sitePattern.getSitename());
        }
        if(!StringUtil.isBlank(sitePattern.getStartmark())){
            sql.append(" startmark = ? ,");
            bindArgs.add(sitePattern.getStartmark());
        }
        if(!StringUtil.isBlank(sitePattern.getEndmark())){
            sql.append(" endmark = ? ,");
            bindArgs.add(sitePattern.getEndmark());
        }
        if(!StringUtil.isBlank(sitePattern.getDeletemark())){
            sql.append(" deletemark = ? ");
            bindArgs.add(sitePattern.getDeletemark());
        }
        sql.append(" WHERE 1=1 ");
        sql.append("and id = ?");
        bindArgs.add(sitePattern.getId());
        db.execSQL(sql.toString(),bindArgs.toArray());
    }

    @Override
    public void delete(GenericVO vo) throws AppException, SysException {
       SitePatternSVO sitePattern = (SitePatternSVO) vo;
        StringBuffer sql = new StringBuffer("delete from site_pattern");
        if (StringUtil.isBlank(sitePattern.getId())) {
            throw new AppException("100002", "缺少对象的主键！");
        }else{
            sql.append(" where id = ? ");
        }
        db.execSQL(sql.toString(),new String[]{sitePattern.getId()});
    }

    @Override
    public void unable(GenericVO vo) throws AppException, SysException {
    }

    @Override
    public List findByVO(GenericVO vo) throws AppException, SysException {
        List<SitePatternSVO> mListData = new ArrayList<SitePatternSVO>();
        Cursor cursor = null;
        StringBuffer whereSelect = new StringBuffer(" 1=1 ");
        List<String> selectionArgs = new ArrayList<String>();
        SitePatternSVO sitePattern = (SitePatternSVO) vo;
        try {
            if(!StringUtil.isBlank(sitePattern.getId())){
                whereSelect.append(" and  id = ?,");
                selectionArgs.add(sitePattern.getId());
             }
            if(!StringUtil.isBlank(sitePattern.getUrl())){
                whereSelect.append(" and  url = ?,");
                selectionArgs.add(sitePattern.getUrl());
             }
            if(!StringUtil.isBlank(sitePattern.getSitename())){
                whereSelect.append(" and  sitename = ?,");
                selectionArgs.add(sitePattern.getSitename());
             }
            if(!StringUtil.isBlank(sitePattern.getStartmark())){
                whereSelect.append(" and  startmark = ?,");
                selectionArgs.add(sitePattern.getStartmark());
             }
            if(!StringUtil.isBlank(sitePattern.getEndmark())){
                whereSelect.append(" and  endmark = ?,");
                selectionArgs.add(sitePattern.getEndmark());
             }
            if(!StringUtil.isBlank(sitePattern.getDeletemark())){
                whereSelect.append(" and  deletemark = ?,");
                selectionArgs.add(sitePattern.getDeletemark());
             }
            cursor = db.query(vo.getTableName(), null, whereSelect.toString(),
                    (String[]) selectionArgs.toArray(), null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    mListData.add(new SitePatternSVO(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            throw new AppException(TAG, "执行sitePattern数据查询异常："+e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return mListData;
    }

}
