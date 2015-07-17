package com.frame.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库的公共操作类
 *
 * @version V1.0
 * @Title: DBLogic.java
 * @Package: com.bangyibang.weixinmh.common.dblogic
 * @company: byb
 * @author: ollie
 * @date 2015-1-12 下午5:58:14
 */
public class DBLogic {
    /**
     * 插入数据
     *
     * @param @param sql
     * @param @param sqliteOpenHelper
     * @return void
     * @throws
     * @Title: insert
     */
    public boolean insert(String sql, SQLiteOpenHelper sqliteOpenHelper) {
        if (sqliteOpenHelper != null) {
            SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();
            try {
                db.beginTransaction();
                db.execSQL(sql);
                db.setTransactionSuccessful();
                db.endTransaction();
            } catch (Exception e) {
                return false;
            } finally {

            }
            return true;
        }
        return false;
    }

    /**
     * @param @param  sql
     * @param @return
     * @return boolean
     * @throws
     * @Title: execute
     * @Description: 执行操作，次方法只合增、删、改
     */
    public boolean delExecute(String sql, SQLiteOpenHelper sqliteOpenHelper) {
        if (sqliteOpenHelper != null) {
            SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();
            try {
                db.beginTransaction();
                db.execSQL(sql);
                db.setTransactionSuccessful();
            } catch (Exception e) {
                return false;
            } finally {
                db.endTransaction();
            }
            return true;
        }
        return false;
    }

    /**
     * @param @param sql
     * @param @param list
     * @return void
     * @throws
     * @Title: execute
     * @Description: 执行操作，次方法只是适合增、删、改。因为此操作不存在返回
     * 批量数据处理，避免使用此方法，因为重复初始化数据库，是比较消耗性能
     */
    public boolean updateExecute(String sql, List<Object> list,
                                 SQLiteOpenHelper sqliteOpenHelper) {
        if (sqliteOpenHelper != null) {
            SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();
            try {
                db.beginTransaction();
                db.execSQL(sql, DBSql.changeList(list));
                db.setTransactionSuccessful();
                db.endTransaction();
            } catch (Exception e) {
                return false;
            } finally {
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查找数据
     *
     * @param @param  sql
     * @param @param  object
     * @param @param  sqliteOpenHelper
     * @param @return
     * @return List<Map<String,String>>
     * @throws
     * @Title: getDate
     */
    public List<Map<String, String>> getDate(String sql, String[] object,
                                             SQLiteOpenHelper sqliteOpenHelper) {
        List<Map<String, String>> list = null;
        Cursor cursor = null;
        if (sqliteOpenHelper != null) {
            SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();
            try {
                db.beginTransaction();
                cursor = db.rawQuery(sql, object);// ?
                if (cursor != null) {
                    list = new ArrayList<Map<String, String>>();
                    while (cursor.moveToNext()) {
                        Map<String, String> map = new HashMap<String, String>();
                        int count = cursor.getColumnCount();
                        for (int i = 0; i < count; i++) {
                            map.put(cursor.getColumnName(i),
                                    cursor.getString(i));
                        }
                        list.add(map);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                }
                db.setTransactionSuccessful();
                db.endTransaction();
            } finally {
            }
        }
        return list;
    }

    /**
     * 获取数据表的单条数据
     *
     * @param @param  sql
     * @param @param  object
     * @param @param  sqliteOpenHelper
     * @param @return
     * @return Map<String,String>
     * @throws
     * @Title: getMapData
     */
    public Map<String, String> getMapData(String sql, String[] object,
                                          SQLiteOpenHelper sqliteOpenHelper) {
        Map<String, String> map = null;
        if (sqliteOpenHelper != null) {
            SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();
            Cursor cursor = null;
            db.beginTransaction();
            try {
                cursor = db.rawQuery(sql, object);//
                if (cursor != null) {
                    map = new HashMap<String, String>();
                    while (cursor.moveToNext()) {
                        int count = cursor.getColumnCount();
                        for (int i = 0; i < count; i++) {
                            map.put(cursor.getColumnName(i),
                                    cursor.getString(i));
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                }
                db.setTransactionSuccessful();
                db.endTransaction();
            } finally {
                closedb(db);
            }
        }
        return map;
    }

    private void closedb(SQLiteDatabase db) {
        if (db != null) {
            db.close();
        }
    }
}
