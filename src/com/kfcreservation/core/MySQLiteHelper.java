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
		Vector<Vector<String>> vector = new Vector<Vector<String>>();// �½���Ų�ѯ���������
		try {
			Cursor cur = db.rawQuery(sql, new String[] {});// �õ������

			while (cur.moveToNext())// ���������һ��
			{
				Vector<String> v = new Vector<String>();
				int col = cur.getColumnCount(); // �����������
				for (int i = 0; i < col; i++) {
					v.add(cur.getString(i));
				}
				vector.add(v);
			}
			cur.close();// �رս����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}
	
	//DBHelper���������ݵķ���,���������ݿ�ͬʱִ�и÷����������ݱ��ṹ���Ժ���ִ��
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String sql[] = new String[] 
		{
			// ϵͳ��
			"CREATE TABLE IF NOT EXISTS android_metadata (locale TEXT);",
			"INSERT INTO \"main\".\"android_metadata\" VALUES (\'en_US\');",
				
			// ʳƷ���ͱ�
			"CREATE TABLE IF NOT EXISTS \"FoodType\" (" +
			"\"_fid\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"Name\"" +
			");",
			// ����ʳƷ��������
			"INSERT INTO \"main\".\"FoodType\" VALUES (1,\"��ʳ\");",
			"INSERT INTO \"main\".\"FoodType\" VALUES (2,\"���\");",
			"INSERT INTO \"main\".\"FoodType\" VALUES (3,\"����\");",
			"INSERT INTO \"main\".\"FoodType\" VALUES (4,\"�ײ�\");",
			"INSERT INTO \"main\".\"FoodType\" VALUES (5,\"��Ʒ\");",
			
			// ʳƷ��
			"CREATE TABLE IF NOT EXISTS \"FoodAll\" (" +
			"\"_id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"Name\" text," +
			"\"Price\" text," +
			"\"FoodType\" integer," +
			"\"Img\" text" +
			");",
			
			//�κ�Ʒ���ײ�����Ϊ Ʒ����+t
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"�������ȱ�\",\"15RMB\",\"1\",\"hb\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"��԰�༦��\",\"10RMB\",\"1\",\"ty\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"��������\",\"8RMB\",\"2\",\"hw\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"�����֭\",\"9RMB\",\"3\",\"jz\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"�������ȱ��ײ�\",\"23RMB\",\"4\",\"hbt\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"��У����\",\"6RMB\",\"5\",\"sx\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"����(��)\",\"7.5RMB\",\"5\",\"ff\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"�������ȱ�\",\"15RMB\",\"1\",\"hb\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"�������ȱ�\",\"15RMB\",\"1\",\"hb\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"�������ȱ�\",\"15RMB\",\"1\",\"hb\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"�������ȱ�\",\"15RMB\",\"1\",\"hb\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"�������ȱ�\",\"15RMB\",\"1\",\"hb\");",
			"INSERT INTO \"main\".\"FoodAll\" VALUES (NULL,\"�������ȱ�\",\"15RMB\",\"1\",\"hb\");",
			
			// �û���
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