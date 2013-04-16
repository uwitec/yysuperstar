package com.kfcreservation.core;

import java.util.Vector;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper{
	
	private static final String DBNAME = "mydb";
	private static final int VERSION = 1;
	private static SQLiteDatabase db;
	
	//创建构造数据库
	public MySQLiteHelper(Context context){
		this(context,DBNAME,null,VERSION);
	}
	/*
	 * context上下文
	 * name数据库名称
	 * factory为Cursor对象
	 * version版本
	 */
	public MySQLiteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	//获得一个SqlIteDatabase对象,通过单例模式创建数据库对象
	public static SQLiteDatabase getDB(Context context){
		if(null==db)
			db = new MySQLiteHelper(context).getWritableDatabase();
		return db;
	}
	
	public static boolean insert(String sql)// 插入数据
	{
		//SQLiteDatabase sld = createOrOpenDatabase();// 连接数据库
		//MySQLiteHelper.getDB(context);
		
		try {
			db.execSQL(sql);

			//db.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static Vector<Vector<String>> query(String sql)// 查询
	{
		Vector<Vector<String>> vector = new Vector<Vector<String>>();// 新建存放查询结果的向量
		//SQLiteDatabase sld = createOrOpenDatabase();// 得到连接数据库的连接
		//MySQLiteHelper.getDB(context);

		try {
			Cursor cur = db.rawQuery(sql, new String[] {});// 得到结果集

			while (cur.moveToNext())// 如果存在下一条
			{
				Vector<String> v = new Vector<String>();
				int col = cur.getColumnCount(); // 将其放入向量
				for (int i = 0; i < col; i++) {
					v.add(cur.getString(i));
				}
				vector.add(v);
			}
			cur.close();// 关闭结果集
			//db.close();// 关闭连接
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}
	
	//DBHelper创建表数据的方法,当创建数据库同时执行该方法创建数据表结构，以后不再执行
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String sql[] = new String[] 
		{
			//系统表
			"CREATE TABLE IF NOT EXISTS android_metadata (locale TEXT);",
			"INSERT INTO \"main\".\"android_metadata\" VALUES (\'en_US\');",
				
			// 建立班级表
			"CREATE TABLE IF NOT EXISTS \"Classes\" (" +
			"\"_cid\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"className\"  TEXT NOT NULL DEFAULT 未命名班级," +
			"\"type\"  INTEGER," +
			"\"icon\"  INTEGER NOT NULL DEFAULT 0," +
			"\"createDate\"  TEXT," +
			"CONSTRAINT \"tp\" FOREIGN KEY (\"type\") REFERENCES \"ClassesType\" (\"_tid\")" +
			");",
			
			"INSERT INTO \"main\".\"Classes\" VALUES (1, 'Android 1班', 1, 0, 20130129);",
			"INSERT INTO \"main\".\"Classes\" VALUES (2, 'Android 2班', 1, 0, 20130129);",
			
			// 建立专业表
			"CREATE TABLE IF NOT EXISTS \"ClassesType\" (" +
			"\"_tid\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
			"\"classesType\"  TEXT" +
			");",
			
			"INSERT INTO \"main\".\"ClassesType\" VALUES (1, 'Android');",
			"INSERT INTO \"main\".\"ClassesType\" VALUES (2, 'IOS');",
			
			// 学生表
			"CREATE TABLE IF NOT EXISTS \"Students\" (" +
			"\"_sid\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"name\"  TEXT NOT NULL," +
			"\"classesId\"  INTEGER NOT NULL," +
			"\"joindate\"  TEXT NOT NULL," +
			"CONSTRAINT \"cid\" FOREIGN KEY (\"classesId\") REFERENCES \"Classes\" (\"_cid\")" +
			");",
			
			"INSERT INTO \"main\".\"Students\" VALUES (1, '杨须斌', 2, 20121228);",
			"INSERT INTO \"main\".\"Students\" VALUES (2, '杨仁杰', 2, 20130107);",
		};
		
		for (String o : sql) {
			//MySQLiteHelper.getDB(context).;
			db.execSQL(o);
		}
	}

	//用于数据版本升级操作的方法
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
}
