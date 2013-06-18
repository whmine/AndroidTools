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
import com.actionbarsherlock.whmine.book.mode.BookSVO;

public class BookSDAOImpl implements ISDAO {

    private final static String TAG = "BookSDao";
    private SQLiteDatabase db;

    public BookSDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
        Cursor cursor = null;
        StringBuffer whereSelect = new StringBuffer(" 1=1 ");
        List<String> selectionArgs = new ArrayList<String>();
        BookSVO book = (BookSVO) vo;
        try {
            if (!StringUtil.isBlank(book.getId())) {
                whereSelect.append(" and id=? ");
                selectionArgs.add(book.getId());
            }
            cursor = db.query("book", null, whereSelect.toString(),
                    (String[]) selectionArgs.toArray(), null, null, "id");
            if (cursor.moveToFirst()) {
                BookSVO tempbook = new BookSVO(cursor);
                return tempbook;
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
            BookSVO book = (BookSVO) vo;
            StringBuffer sql = new StringBuffer("INSERT INTO book(author,uri,updateTime,brief,siteName,categoryName,coverUrl,newChapterUrl,newChapterName,name,lastRead,lastReadTime,downloadTime,id,needUpdate,newChapterId,maxReadChapterId,defaultBook,sequence,siteId,categoryId,temp,type,autoDownload,autoDelete,userId)");
            sql.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            db.execSQL(sql.toString(),book.toArray());
        } catch (SQLException e) {
            throw new AppException(TAG, "执行book数据插入异常："+e);
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
        BookSVO book = (BookSVO) vo;
        if (StringUtil.isBlank(book.getId())) {
            throw new AppException("100002", "缺少对象的主键！");
        }
        StringBuffer sql = new StringBuffer("UPDATE book SET ");
        List<String> bindArgs = new ArrayList<String>();
        if(!StringUtil.isBlank(book.getAuthor())){
            sql.append(" author = ? ,");
            bindArgs.add(book.getAuthor());
        }
        if(!StringUtil.isBlank(book.getUri())){
            sql.append(" uri = ? ,");
            bindArgs.add(book.getUri());
        }
        if(!StringUtil.isBlank(book.getUpdatetime())){
            sql.append(" updatetime = ? ,");
            bindArgs.add(book.getUpdatetime());
        }
        if(!StringUtil.isBlank(book.getBrief())){
            sql.append(" brief = ? ,");
            bindArgs.add(book.getBrief());
        }
        if(!StringUtil.isBlank(book.getSitename())){
            sql.append(" sitename = ? ,");
            bindArgs.add(book.getSitename());
        }
        if(!StringUtil.isBlank(book.getCategoryname())){
            sql.append(" categoryname = ? ,");
            bindArgs.add(book.getCategoryname());
        }
        if(!StringUtil.isBlank(book.getCoverurl())){
            sql.append(" coverurl = ? ,");
            bindArgs.add(book.getCoverurl());
        }
        if(!StringUtil.isBlank(book.getNewchapterurl())){
            sql.append(" newchapterurl = ? ,");
            bindArgs.add(book.getNewchapterurl());
        }
        if(!StringUtil.isBlank(book.getNewchaptername())){
            sql.append(" newchaptername = ? ,");
            bindArgs.add(book.getNewchaptername());
        }
        if(!StringUtil.isBlank(book.getName())){
            sql.append(" name = ? ,");
            bindArgs.add(book.getName());
        }
        if(!StringUtil.isBlank(book.getLastread())){
            sql.append(" lastread = ? ,");
            bindArgs.add(book.getLastread());
        }
        if(!StringUtil.isBlank(book.getLastreadtime())){
            sql.append(" lastreadtime = ? ,");
            bindArgs.add(book.getLastreadtime());
        }
        if(!StringUtil.isBlank(book.getDownloadtime())){
            sql.append(" downloadtime = ? ,");
            bindArgs.add(book.getDownloadtime());
        }
        if(!StringUtil.isBlank(book.getId())){
            sql.append(" id = ? ,");
            bindArgs.add(book.getId());
        }
        if(!StringUtil.isBlank(book.getNeedupdate())){
            sql.append(" needupdate = ? ,");
            bindArgs.add(book.getNeedupdate());
        }
        if(!StringUtil.isBlank(book.getNewchapterid())){
            sql.append(" newchapterid = ? ,");
            bindArgs.add(book.getNewchapterid());
        }
        if(!StringUtil.isBlank(book.getMaxreadchapterid())){
            sql.append(" maxreadchapterid = ? ,");
            bindArgs.add(book.getMaxreadchapterid());
        }
        if(!StringUtil.isBlank(book.getDefaultbook())){
            sql.append(" defaultbook = ? ,");
            bindArgs.add(book.getDefaultbook());
        }
        if(!StringUtil.isBlank(book.getSequence())){
            sql.append(" sequence = ? ,");
            bindArgs.add(book.getSequence());
        }
        if(!StringUtil.isBlank(book.getSiteid())){
            sql.append(" siteid = ? ,");
            bindArgs.add(book.getSiteid());
        }
        if(!StringUtil.isBlank(book.getCategoryid())){
            sql.append(" categoryid = ? ,");
            bindArgs.add(book.getCategoryid());
        }
        if(!StringUtil.isBlank(book.getTemp())){
            sql.append(" temp = ? ,");
            bindArgs.add(book.getTemp());
        }
        if(!StringUtil.isBlank(book.getType())){
            sql.append(" type = ? ,");
            bindArgs.add(book.getType());
        }
        if(!StringUtil.isBlank(book.getAutodownload())){
            sql.append(" autodownload = ? ,");
            bindArgs.add(book.getAutodownload());
        }
        if(!StringUtil.isBlank(book.getAutodelete())){
            sql.append(" autodelete = ? ,");
            bindArgs.add(book.getAutodelete());
        }
        if(!StringUtil.isBlank(book.getUserid())){
            sql.append(" userid = ? ");
            bindArgs.add(book.getUserid());
        }
        sql.append(" WHERE 1=1 ");
        sql.append("and id = ?");
        bindArgs.add(book.getId());
        db.execSQL(sql.toString(),bindArgs.toArray());
    }

    @Override
    public void delete(GenericVO vo) throws AppException, SysException {
       BookSVO book = (BookSVO) vo;
        StringBuffer sql = new StringBuffer("delete from book");
        if (StringUtil.isBlank(book.getId())) {
            throw new AppException("100002", "缺少对象的主键！");
        }else{
            sql.append(" where id = ? ");
        }
        db.execSQL(sql.toString(),new String[]{book.getId()});
    }

    @Override
    public void unable(GenericVO vo) throws AppException, SysException {
    }

    @Override
    public List findByVO(GenericVO vo) throws AppException, SysException {
        List<BookSVO> mListData = new ArrayList<BookSVO>();
        Cursor cursor = null;
        StringBuffer whereSelect = new StringBuffer(" 1=1 ");
        List<String> selectionArgs = new ArrayList<String>();
        BookSVO book = (BookSVO) vo;
        try {
            if(!StringUtil.isBlank(book.getAuthor())){
                whereSelect.append(" and  author = ?,");
                selectionArgs.add(book.getAuthor());
             }
            if(!StringUtil.isBlank(book.getUri())){
                whereSelect.append(" and  uri = ?,");
                selectionArgs.add(book.getUri());
             }
            if(!StringUtil.isBlank(book.getUpdatetime())){
                whereSelect.append(" and  updatetime = ?,");
                selectionArgs.add(book.getUpdatetime());
             }
            if(!StringUtil.isBlank(book.getBrief())){
                whereSelect.append(" and  brief = ?,");
                selectionArgs.add(book.getBrief());
             }
            if(!StringUtil.isBlank(book.getSitename())){
                whereSelect.append(" and  sitename = ?,");
                selectionArgs.add(book.getSitename());
             }
            if(!StringUtil.isBlank(book.getCategoryname())){
                whereSelect.append(" and  categoryname = ?,");
                selectionArgs.add(book.getCategoryname());
             }
            if(!StringUtil.isBlank(book.getCoverurl())){
                whereSelect.append(" and  coverurl = ?,");
                selectionArgs.add(book.getCoverurl());
             }
            if(!StringUtil.isBlank(book.getNewchapterurl())){
                whereSelect.append(" and  newchapterurl = ?,");
                selectionArgs.add(book.getNewchapterurl());
             }
            if(!StringUtil.isBlank(book.getNewchaptername())){
                whereSelect.append(" and  newchaptername = ?,");
                selectionArgs.add(book.getNewchaptername());
             }
            if(!StringUtil.isBlank(book.getName())){
                whereSelect.append(" and  name = ?,");
                selectionArgs.add(book.getName());
             }
            if(!StringUtil.isBlank(book.getLastread())){
                whereSelect.append(" and  lastread = ?,");
                selectionArgs.add(book.getLastread());
             }
            if(!StringUtil.isBlank(book.getLastreadtime())){
                whereSelect.append(" and  lastreadtime = ?,");
                selectionArgs.add(book.getLastreadtime());
             }
            if(!StringUtil.isBlank(book.getDownloadtime())){
                whereSelect.append(" and  downloadtime = ?,");
                selectionArgs.add(book.getDownloadtime());
             }
            if(!StringUtil.isBlank(book.getId())){
                whereSelect.append(" and  id = ?,");
                selectionArgs.add(book.getId());
             }
            if(!StringUtil.isBlank(book.getNeedupdate())){
                whereSelect.append(" and  needupdate = ?,");
                selectionArgs.add(book.getNeedupdate());
             }
            if(!StringUtil.isBlank(book.getNewchapterid())){
                whereSelect.append(" and  newchapterid = ?,");
                selectionArgs.add(book.getNewchapterid());
             }
            if(!StringUtil.isBlank(book.getMaxreadchapterid())){
                whereSelect.append(" and  maxreadchapterid = ?,");
                selectionArgs.add(book.getMaxreadchapterid());
             }
            if(!StringUtil.isBlank(book.getDefaultbook())){
                whereSelect.append(" and  defaultbook = ?,");
                selectionArgs.add(book.getDefaultbook());
             }
            if(!StringUtil.isBlank(book.getSequence())){
                whereSelect.append(" and  sequence = ?,");
                selectionArgs.add(book.getSequence());
             }
            if(!StringUtil.isBlank(book.getSiteid())){
                whereSelect.append(" and  siteid = ?,");
                selectionArgs.add(book.getSiteid());
             }
            if(!StringUtil.isBlank(book.getCategoryid())){
                whereSelect.append(" and  categoryid = ?,");
                selectionArgs.add(book.getCategoryid());
             }
            if(!StringUtil.isBlank(book.getTemp())){
                whereSelect.append(" and  temp = ?,");
                selectionArgs.add(book.getTemp());
             }
            if(!StringUtil.isBlank(book.getType())){
                whereSelect.append(" and  type = ?,");
                selectionArgs.add(book.getType());
             }
            if(!StringUtil.isBlank(book.getAutodownload())){
                whereSelect.append(" and  autodownload = ?,");
                selectionArgs.add(book.getAutodownload());
             }
            if(!StringUtil.isBlank(book.getAutodelete())){
                whereSelect.append(" and  autodelete = ?,");
                selectionArgs.add(book.getAutodelete());
             }
            if(!StringUtil.isBlank(book.getUserid())){
                whereSelect.append(" and  userid = ?,");
                selectionArgs.add(book.getUserid());
             }
            cursor = db.query(vo.getTableName(), null, whereSelect.toString(),
                    (String[]) selectionArgs.toArray(), null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    mListData.add(new BookSVO(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            throw new AppException(TAG, "执行book数据查询异常："+e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return mListData;
    }

}
