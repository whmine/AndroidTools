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
import com.actionbarsherlock.whmine.book.mode.UpgradeSVO;

public class UpgradeSDAOImpl implements ISDAO {

    private final static String TAG = "UpgradeSDao";
    private SQLiteDatabase db;

    public UpgradeSDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
        Cursor cursor = null;
        StringBuffer whereSelect = new StringBuffer(" 1=1 ");
        List<String> selectionArgs = new ArrayList<String>();
        UpgradeSVO upgrade = (UpgradeSVO) vo;
        try {
            if (!StringUtil.isBlank(upgrade.getId())) {
                whereSelect.append(" and id=? ");
                selectionArgs.add(upgrade.getId());
            }
            cursor = db.query("upgrade", null, whereSelect.toString(),
                    (String[]) selectionArgs.toArray(), null, null, "id");
            if (cursor.moveToFirst()) {
                UpgradeSVO tempupgrade = new UpgradeSVO(cursor);
                return tempupgrade;
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
            UpgradeSVO upgrade = (UpgradeSVO) vo;
            StringBuffer sql = new StringBuffer("INSERT INTO upgrade(apkUrl,badVersion,description,releaseDate,newVersionName,newVersionCode,minVersionCode,id)");
            sql.append("values(?,?,?,?,?,?,?,?)");
            db.execSQL(sql.toString(),upgrade.toArray());
        } catch (SQLException e) {
            throw new AppException(TAG, "执行upgrade数据插入异常："+e);
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
        UpgradeSVO upgrade = (UpgradeSVO) vo;
        if (StringUtil.isBlank(upgrade.getId())) {
            throw new AppException("100002", "缺少对象的主键！");
        }
        StringBuffer sql = new StringBuffer("UPDATE upgrade SET ");
        List<String> bindArgs = new ArrayList<String>();
        if(!StringUtil.isBlank(upgrade.getApkurl())){
            sql.append(" apkurl = ? ,");
            bindArgs.add(upgrade.getApkurl());
        }
        if(!StringUtil.isBlank(upgrade.getBadversion())){
            sql.append(" badversion = ? ,");
            bindArgs.add(upgrade.getBadversion());
        }
        if(!StringUtil.isBlank(upgrade.getDescription())){
            sql.append(" description = ? ,");
            bindArgs.add(upgrade.getDescription());
        }
        if(!StringUtil.isBlank(upgrade.getReleasedate())){
            sql.append(" releasedate = ? ,");
            bindArgs.add(upgrade.getReleasedate());
        }
        if(!StringUtil.isBlank(upgrade.getNewversionname())){
            sql.append(" newversionname = ? ,");
            bindArgs.add(upgrade.getNewversionname());
        }
        if(!StringUtil.isBlank(upgrade.getNewversioncode())){
            sql.append(" newversioncode = ? ,");
            bindArgs.add(upgrade.getNewversioncode());
        }
        if(!StringUtil.isBlank(upgrade.getMinversioncode())){
            sql.append(" minversioncode = ? ,");
            bindArgs.add(upgrade.getMinversioncode());
        }
        if(!StringUtil.isBlank(upgrade.getId())){
            sql.append(" id = ? ");
            bindArgs.add(upgrade.getId());
        }
        sql.append(" WHERE 1=1 ");
        sql.append("and id = ?");
        bindArgs.add(upgrade.getId());
        db.execSQL(sql.toString(),bindArgs.toArray());
    }

    @Override
    public void delete(GenericVO vo) throws AppException, SysException {
       UpgradeSVO upgrade = (UpgradeSVO) vo;
        StringBuffer sql = new StringBuffer("delete from upgrade");
        if (StringUtil.isBlank(upgrade.getId())) {
            throw new AppException("100002", "缺少对象的主键！");
        }else{
            sql.append(" where id = ? ");
        }
        db.execSQL(sql.toString(),new String[]{upgrade.getId()});
    }

    @Override
    public void unable(GenericVO vo) throws AppException, SysException {
    }

    @Override
    public List findByVO(GenericVO vo) throws AppException, SysException {
        List<UpgradeSVO> mListData = new ArrayList<UpgradeSVO>();
        Cursor cursor = null;
        StringBuffer whereSelect = new StringBuffer(" 1=1 ");
        List<String> selectionArgs = new ArrayList<String>();
        UpgradeSVO upgrade = (UpgradeSVO) vo;
        try {
            if(!StringUtil.isBlank(upgrade.getApkurl())){
                whereSelect.append(" and  apkurl = ?,");
                selectionArgs.add(upgrade.getApkurl());
             }
            if(!StringUtil.isBlank(upgrade.getBadversion())){
                whereSelect.append(" and  badversion = ?,");
                selectionArgs.add(upgrade.getBadversion());
             }
            if(!StringUtil.isBlank(upgrade.getDescription())){
                whereSelect.append(" and  description = ?,");
                selectionArgs.add(upgrade.getDescription());
             }
            if(!StringUtil.isBlank(upgrade.getReleasedate())){
                whereSelect.append(" and  releasedate = ?,");
                selectionArgs.add(upgrade.getReleasedate());
             }
            if(!StringUtil.isBlank(upgrade.getNewversionname())){
                whereSelect.append(" and  newversionname = ?,");
                selectionArgs.add(upgrade.getNewversionname());
             }
            if(!StringUtil.isBlank(upgrade.getNewversioncode())){
                whereSelect.append(" and  newversioncode = ?,");
                selectionArgs.add(upgrade.getNewversioncode());
             }
            if(!StringUtil.isBlank(upgrade.getMinversioncode())){
                whereSelect.append(" and  minversioncode = ?,");
                selectionArgs.add(upgrade.getMinversioncode());
             }
            if(!StringUtil.isBlank(upgrade.getId())){
                whereSelect.append(" and  id = ?,");
                selectionArgs.add(upgrade.getId());
             }
            cursor = db.query(vo.getTableName(), null, whereSelect.toString(),
                    (String[]) selectionArgs.toArray(), null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    mListData.add(new UpgradeSVO(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            throw new AppException(TAG, "执行upgrade数据查询异常："+e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return mListData;
    }

}
