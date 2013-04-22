package com.kfcreservation.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper{
	
	private static final String DBNAME = "kfcdb";
	private static final int VERSION = 1;
	private static SQLiteDatabase db;
	
	public MySQLiteHelper(Context context){
		this(context,DBNAME,null,VERSION);
	}

	public MySQLiteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	public static SQLiteDatabase getDB(Context context){
		if(null==db)
			db = new MySQLiteHelper(context).getWritableDatabase();
		return db;
	}
	
	public static boolean insertBysql(String sql){
		try {
			db.execSQL(sql);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static List<HashMap<String, Object>> lQuery(String sql){
		
		List<HashMap<String,Object>> lists =new ArrayList<HashMap<String,Object>>();
		
		try {
			Cursor cur= db.rawQuery(sql, new String[] {});
		
			while(cur.moveToNext()){
				HashMap<String,Object> maps =new HashMap<String,Object>();
				
				int col = cur.getColumnCount();
				for (int i = 0; i < col; i++) {
					maps.put(cur.getColumnName(i).toString(),cur.getString(i));
				}
				lists.add(maps);
			}
			cur.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lists;
	}

	public static Vector<Vector<String>> vQuery(String sql) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();// 新建存放查询结果的向量
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
			// 系统表
			"CREATE TABLE IF NOT EXISTS android_metadata (locale TEXT);",
			"INSERT INTO \"main\".\"android_metadata\" VALUES (\'en_US\');",
				
			// 食品类型表
			"CREATE TABLE IF NOT EXISTS \"FoodType\" (" +
			"\"_fid\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"Name\"" +
			");",
			// 插入食品类型数据
			"INSERT INTO \"main\".\"FoodType\" VALUES (1,\"主食\");",
			"INSERT INTO \"main\".\"FoodType\" VALUES (2,\"配餐\");",
			"INSERT INTO \"main\".\"FoodType\" VALUES (3,\"饮料\");",
			"INSERT INTO \"main\".\"FoodType\" VALUES (4,\"套餐\");",
			"INSERT INTO \"main\".\"FoodType\" VALUES (5,\"新品\");",
			
			// 食品表
			"CREATE TABLE IF NOT EXISTS \"FoodAll\" (" +
			"\"_id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"Name\" text," +
			"\"Price\" real," +
			"\"FoodType\" integer," +
			"\"Img\" text" +
			");",
			
			//任何品项套餐命名为 品项名+t
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"雪顶咖啡\",\"10\",\"3\",\"coffee\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"百事可乐\",\"7.5\",\"3\",\"coke\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"醇豆浆甜\",\"7\",\"3\",\"dou\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"劲脆鸡腿堡餐\",\"23\",\"4\",\"etct\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"薯条\",\"7.5\",\"1\",\"ff\");",
            "INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"香辣鸡腿堡\",\"15\",\"1\",\"hb\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"二块香辣鸡翅\",\"8.5\",\"1\",\"hw\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"黄金海皇星2个\",\"9\",\"1\",\"hx\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"九珍(中)\",\"9\",\"3\",\"jz\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"香醇奶茶(热)\",\"10\",\"3\",\"nc\");",
            "INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"柠乐\",\"8\",\"3\",\"nl\");",
            "INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"二块新奥尔良烤翅\",\"9\",\"1\",\"nw2\");",
            "INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"新奥尔良烤腿堡\",\"15.5\",\"1\",\"nw\");",
            "INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"二块吮指原味鸡\",\"16\",\"1\",\"or2\");",
            "INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"一块吮指原味鸡\",\"9\",\"1\",\"or\");",
            "INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"上校鸡块5块装\",\"6\",\"1\",\"sx\");",
            "INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"田园脆鸡堡\",\"10\",\"1\",\"ty\");",
			
			// 用户表
			"CREATE TABLE IF NOT EXISTS \"UserInfo\" (" +
			"\"_uid\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"PhoneNum\" VARCHAR(20)," +
			"\"Password\" VARCHAR(20)" +
			");"
		};
		
		for (String o : sql) {
			db.execSQL(o);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {	}
	
}
