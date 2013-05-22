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
	
	//DBHelper���������ݵķ���,���������ݿ�ͬʱִ�и÷����������ݱ�ṹ���Ժ���ִ��
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
						"INSERT INTO \"main\".\"FoodType\" VALUES (1,\"���\");",
						"INSERT INTO \"main\".\"FoodType\" VALUES (2,\"�ȳ�\");",
						"INSERT INTO \"main\".\"FoodType\" VALUES (3,\"�ɼ�\");",
						"INSERT INTO \"main\".\"FoodType\" VALUES (4,\"����\");",
						"INSERT INTO \"main\".\"FoodType\" VALUES (5,\"����\");",
						
						// ʳƷ��
						"CREATE TABLE IF NOT EXISTS \"Foods\" (" +
						"\"_id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
						"\"Name\" text," +
						"\"Price\" real," +
						"\"FoodType\" integer," +
						"\"Img\" text" +
						");",
						
						//�κ�Ʒ���ײ�����Ϊ Ʒ����+t
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"�����ϻ���\",\"10\",\"1\",\"dban01\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"���ﻨ��\",\"10\",\"1\",\"dban02\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"�����ƹ���\",\"8\",\"1\",\"dban03\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"ˮ��ɫ��\",\"10\",\"1\",\"dban04\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"��˸�˿\",\"6\",\"1\",\"dban05\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"������\",\"22\",\"2\",\"chao01\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"���ѳ�����\",\"18\",\"2\",\"chao02\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"�����ļ���\",\"20\",\"2\",\"chao03\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"��������\",\"20\",\"2\",\"chao04\");",
						"INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"������ɫ\",\"15\",\"2\",\"chao05\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"��Ƥ����\",\"25\",\"3\",\"fjian01\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"���������\",\"35\",\"3\",\"fjian02\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"����ӱ���\",\"32\",\"3\",\"fjian03\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"��������\",\"78\",\"3\",\"fjian04\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"��ĩ�ܵ�\",\"16\",\"3\",\"fjian05\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"����ʨ��ͷ\",\"30\",\"4\",\"dzheng01\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"���׷�����\",\"32\",\"4\",\"dzheng02\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"��������\",\"42\",\"4\",\"dzheng03\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"��������\",\"38\",\"4\",\"dzheng04\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"�����౦��\",\"45\",\"4\",\"dzheng05\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"��ȷ��\",\"15\",\"5\",\"ezhu01\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"�����廨��\",\"25\",\"5\",\"ezhu02\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"�������\",\"50\",\"5\",\"ezhu08\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"�ڰ�̫����\",\"16\",\"5\",\"ezhu04\");",
			            "INSERT INTO \"main\".\"Foods\" VALUES (NULL,\"��֭����\",\"12\",\"5\",\"ezhu05\");",
			
			// �û���
			"CREATE TABLE IF NOT EXISTS \"UserInfo\" (" +
			"\"_uid\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"PhoneNum\" VARCHAR(20)," +
			"\"Password\" VARCHAR(20)" +
			");",
			//��ϵ��ʽ�� ���û���_uid����
			"create table UserPhone (_Pid Integer PRIMARY key AUTOINCREMENT,_uid Integer not NULL,PhoneNum text,FOREIGN key(_uid) REfERENCES UserInfo (_uid))",
			
			
			"create table UserAddress(_Aid Integer PRIMARY KEY AUTOINCREMENT,_Uid Integer not NULL,Address text,FOREIGN key(_Uid) REFERENCES UserInfo (_Uid))",
			
			
			// �û�ѡ���ʳ��
			"CREATE TABLE IF NOT EXISTS \"UserFoods\" (" +
			"\"_ufid\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"Userid\" INTEGER," +
			"\"Foodid\" INTEGER," +
			"\"Serial\" INTEGER," +
			"\"Count\" INTEGER," +
			"\"Status\" INTEGER"+
			");",
			
			//������
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
