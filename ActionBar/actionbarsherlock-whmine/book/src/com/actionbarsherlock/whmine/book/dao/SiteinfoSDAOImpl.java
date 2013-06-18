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
import com.actionbarsherlock.whmine.book.mode.SiteinfoSVO;

public class SiteinfoSDAOImpl implements ISDAO {

    private final static String TAG = "SiteinfoSDao";
    private SQLiteDatabase db;

    public SiteinfoSDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
        Cursor cursor = null;
        StringBuffer whereSelect = new StringBuffer(" 1=1 ");
        List<String> selectionArgs = new ArrayList<String>();
        SiteinfoSVO siteinfo = (SiteinfoSVO) vo;
        try {
            if (!StringUtil.isBlank(siteinfo.getSiteid())) {
                whereSelect.append(" and siteid=? ");
                selectionArgs.add(siteinfo.getSiteid());
            }
            cursor = db.query("siteInfo", null, whereSelect.toString(),
                    (String[]) selectionArgs.toArray(), null, null, "siteid");
            if (cursor.moveToFirst()) {
                SiteinfoSVO tempsiteinfo = new SiteinfoSVO(cursor);
                return tempsiteinfo;
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
            SiteinfoSVO siteinfo = (SiteinfoSVO) vo;
            StringBuffer sql = new StringBuffer("INSERT INTO siteInfo(siteId,host,extra,siteName)");
            sql.append("values(?,?,?,?)");
            db.execSQL(sql.toString(),siteinfo.toArray());
        } catch (SQLException e) {
            throw new AppException(TAG, "执行siteinfo数据插入异常："+e);
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
        SiteinfoSVO siteinfo = (SiteinfoSVO) vo;
        if (StringUtil.isBlank(siteinfo.getSiteid())) {
            throw new AppException("100002", "缺少对象的主键！");
        }
        StringBuffer sql = new StringBuffer("UPDATE siteInfo SET ");
        List<String> bindArgs = new ArrayList<String>();
        if(!StringUtil.isBlank(siteinfo.getSiteid())){
            sql.append(" siteid = ? ,");
            bindArgs.add(siteinfo.getSiteid());
        }
        if(!StringUtil.isBlank(siteinfo.getHost())){
            sql.append(" host = ? ,");
            bindArgs.add(siteinfo.getHost());
        }
        if(!StringUtil.isBlank(siteinfo.getExtra())){
            sql.append(" extra = ? ,");
            bindArgs.add(siteinfo.getExtra());
        }
        if(!StringUtil.isBlank(siteinfo.getSitename())){
            sql.append(" sitename = ? ");
            bindArgs.add(siteinfo.getSitename());
        }
        sql.append(" WHERE 1=1 ");
        sql.append("and siteid = ?");
        bindArgs.add(siteinfo.getSiteid());
        db.execSQL(sql.toString(),bindArgs.toArray());
    }

    @Override
    public void delete(GenericVO vo) throws AppException, SysException {
       SiteinfoSVO siteinfo = (SiteinfoSVO) vo;
        StringBuffer sql = new StringBuffer("delete from siteInfo");
        if (StringUtil.isBlank(siteinfo.getSiteid())) {
            throw new AppException("100002", "缺少对象的主键！");
        }else{
            sql.append(" where siteid = ? ");
        }
        db.execSQL(sql.toString(),new String[]{siteinfo.getSiteid()});
    }

    @Override
    public void unable(GenericVO vo) throws AppException, SysException {
    }

    @Override
    public List findByVO(GenericVO vo) throws AppException, SysException {
        List<SiteinfoSVO> mListData = new ArrayList<SiteinfoSVO>();
        Cursor cursor = null;
        StringBuffer whereSelect = new StringBuffer(" 1=1 ");
        List<String> selectionArgs = new ArrayList<String>();
        SiteinfoSVO siteinfo = (SiteinfoSVO) vo;
        try {
            if(!StringUtil.isBlank(siteinfo.getSiteid())){
                whereSelect.append(" and  siteid = ?,");
                selectionArgs.add(siteinfo.getSiteid());
             }
            if(!StringUtil.isBlank(siteinfo.getHost())){
                whereSelect.append(" and  host = ?,");
                selectionArgs.add(siteinfo.getHost());
             }
            if(!StringUtil.isBlank(siteinfo.getExtra())){
                whereSelect.append(" and  extra = ?,");
                selectionArgs.add(siteinfo.getExtra());
             }
            if(!StringUtil.isBlank(siteinfo.getSitename())){
                whereSelect.append(" and  sitename = ?,");
                selectionArgs.add(siteinfo.getSitename());
             }
            cursor = db.query(vo.getTableName(), null, whereSelect.toString(),
                    (String[]) selectionArgs.toArray(), null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    mListData.add(new SiteinfoSVO(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            throw new AppException(TAG, "执行siteinfo数据查询异常："+e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return mListData;
    }

}
