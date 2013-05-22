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
						"INSERT INTO \"main\".\"FoodType\" VALUES (1,\"冷菜\");",
						"INSERT INTO \"main\".\"FoodType\" VALUES (2,\"热炒\");",
						"INSERT INTO \"main\".\"FoodType\" VALUES (3,\"干煎\");",
						"INSERT INTO \"main\".\"FoodType\" VALUES (4,\"清蒸\");",
						"INSERT INTO \"main\".\"FoodType\" VALUES (5,\"香煮\");",
						
						// 食品表
						"CREATE TABLE IF NOT EXISTS \"Foods\" (" +
						"\"_id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
						"\"Name\" text," +
						"\"Price\" real," +
						"\"FoodType\" integer," +
						"\"Img\" text" +
						");",
						
						//任何品项套餐命名为 品项名+t
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"凉拌老虎菜\",\"10\",\"1\",\"dban01\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"醋溜花生\",\"10\",\"1\",\"dban02\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"酸辣黄瓜条\",\"8\",\"1\",\"dban03\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"水果色拉\",\"10\",\"1\",\"dban04\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"香菜干丝\",\"6\",\"1\",\"dban05\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"地三鲜\",\"22\",\"2\",\"chao01\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"番茄炒花菜\",\"18\",\"2\",\"chao02\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"干煸四季豆\",\"20\",\"2\",\"chao03\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"宫保鸡丁\",\"20\",\"2\",\"chao04\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"荷塘月色\",\"15\",\"2\",\"chao05\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"虎皮包肉\",\"25\",\"3\",\"fjian01\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"香煎银鳕鱼\",\"35\",\"3\",\"fjian02\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"香煎杏鲍菇\",\"32\",\"3\",\"fjian03\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"铁板松茸\",\"78\",\"3\",\"fjian04\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"肉末跑蛋\",\"16\",\"3\",\"fjian05\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"粉蒸狮子头\",\"30\",\"4\",\"dzheng01\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"玉米粉蒸肉\",\"32\",\"4\",\"dzheng02\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"清蒸带鱼\",\"42\",\"4\",\"dzheng03\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"清蒸鲈鱼\",\"38\",\"4\",\"dzheng04\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"清蒸多宝鱼\",\"45\",\"4\",\"dzheng05\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"五谷丰登\",\"15\",\"5\",\"ezhu01\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"松茸五花肉\",\"25\",\"5\",\"ezhu02\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"本帮猪脚\",\"50\",\"5\",\"ezhu08\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"黑白太极羹\",\"16\",\"5\",\"ezhu04\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"麻汁豆角\",\"12\",\"5\",\"ezhu05\");",
			
			// 用户表
			"CREATE TABLE IF NOT EXISTS \"UserInfo\" (" +
			"\"_uid\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"PhoneNum\" VARCHAR(20)," +
			"\"Password\" VARCHAR(20)" +
			");",
			//联系方式表 与用户名_uid关联
			"create table UserPhone (_Pid Integer PRIMARY key AUTOINCREMENT,_uid Integer not NULL,PhoneNum text,FOREIGN key(_uid) REfERENCES UserInfo (_uid))",
			
			
			"create table UserAddress(_Aid Integer PRIMARY KEY AUTOINCREMENT,_Uid Integer not NULL,Address text,FOREIGN key(_Uid) REFERENCES UserInfo (_Uid))",
			
			
			// 用户选择的食物
			"CREATE TABLE IF NOT EXISTS \"UserFoods\" (" +
			"\"_ufid\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"Userid\" INTEGER," +
			"\"Foodid\" INTEGER," +
			"\"Serial\" INTEGER," +
			"\"Count\" INTEGER," +
			"\"Status\" INTEGER"+
			");",
			
			//订单表
			"CREATE TABLE IF NOT EXISTS \"Orders\" (" +
			"\"_oid\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"Userid\" INTEGER," +
			"\"Serial\" INTEGER," +
			"\"CreateData\" TIMESTAMP," +
			"\"UpdateData\" TIMESTAMP," +
			"\"Status\" INTEGER"+
			");"
		};
		
		for (String o : sql) {
			db.execSQL(o);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {	}
	
}
