package com.kfcreservation.core;

import java.util.Vector;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MySQLite {

	// 连接数据库
	public static SQLiteDatabase createOrOpenDatabase()
	{
		SQLiteDatabase sld = null;
		try {
			// 连接并创建数据库，如果不存在则创建
			sld = SQLiteDatabase.openDatabase("/data/data/com.rollbook/databases/mydb", null, SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.CREATE_IF_NECESSARY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sld;// 返回该连接
	}

	public static boolean insert(String sql)// 插入数据
	{
		SQLiteDatabase sld = createOrOpenDatabase();// 连接数据库
		try {
			sld.execSQL(sql);

			sld.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static Vector<Vector<String>> query(String sql)// 查询
	{
		Vector<Vector<String>> vector = new Vector<Vector<String>>();// 新建存放查询结果的向量
		SQLiteDatabase sld = createOrOpenDatabase();// 得到连接数据库的连接

		try {
			Cursor cur = sld.rawQuery(sql, new String[] {});// 得到结果集

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
			sld.close();// 关闭连接
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}
	
	// 创建表
	public static void createTable(String sql) {
		SQLiteDatabase sld = createOrOpenDatabase();// 连接数据库
		try {
			sld.execSQL(sql);// 执行SQL语句
			//sld.close();// 关闭连接
		} catch (Exception e) {

		}
	}

	
}