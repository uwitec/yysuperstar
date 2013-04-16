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
	
	//�����������ݿ�
	public MySQLiteHelper(Context context){
		this(context,DBNAME,null,VERSION);
	}
	/*
	 * context������
	 * name���ݿ�����
	 * factoryΪCursor����
	 * version�汾
	 */
	public MySQLiteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	//���һ��SqlIteDatabase����,ͨ������ģʽ�������ݿ����
	public static SQLiteDatabase getDB(Context context){
		if(null==db)
			db = new MySQLiteHelper(context).getWritableDatabase();
		return db;
	}
	
	public static boolean insert(String sql)// ��������
	{
		//SQLiteDatabase sld = createOrOpenDatabase();// �������ݿ�
		//MySQLiteHelper.getDB(context);
		
		try {
			db.execSQL(sql);

			//db.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static Vector<Vector<String>> query(String sql)// ��ѯ
	{
		Vector<Vector<String>> vector = new Vector<Vector<String>>();// �½���Ų�ѯ���������
		//SQLiteDatabase sld = createOrOpenDatabase();// �õ��������ݿ������
		//MySQLiteHelper.getDB(context);

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
			//db.close();// �ر�����
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
			//ϵͳ��
			"CREATE TABLE IF NOT EXISTS android_metadata (locale TEXT);",
			"INSERT INTO \"main\".\"android_metadata\" VALUES (\'en_US\');",
				
			// �����༶��
			"CREATE TABLE IF NOT EXISTS \"Classes\" (" +
			"\"_cid\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"className\"  TEXT NOT NULL DEFAULT δ�����༶," +
			"\"type\"  INTEGER," +
			"\"icon\"  INTEGER NOT NULL DEFAULT 0," +
			"\"createDate\"  TEXT," +
			"CONSTRAINT \"tp\" FOREIGN KEY (\"type\") REFERENCES \"ClassesType\" (\"_tid\")" +
			");",
			
			"INSERT INTO \"main\".\"Classes\" VALUES (1, 'Android 1��', 1, 0, 20130129);",
			"INSERT INTO \"main\".\"Classes\" VALUES (2, 'Android 2��', 1, 0, 20130129);",
			
			// ����רҵ��
			"CREATE TABLE IF NOT EXISTS \"ClassesType\" (" +
			"\"_tid\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
			"\"classesType\"  TEXT" +
			");",
			
			"INSERT INTO \"main\".\"ClassesType\" VALUES (1, 'Android');",
			"INSERT INTO \"main\".\"ClassesType\" VALUES (2, 'IOS');",
			
			// ѧ����
			"CREATE TABLE IF NOT EXISTS \"Students\" (" +
			"\"_sid\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			"\"name\"  TEXT NOT NULL," +
			"\"classesId\"  INTEGER NOT NULL," +
			"\"joindate\"  TEXT NOT NULL," +
			"CONSTRAINT \"cid\" FOREIGN KEY (\"classesId\") REFERENCES \"Classes\" (\"_cid\")" +
			");",
			
			"INSERT INTO \"main\".\"Students\" VALUES (1, '�����', 2, 20121228);",
			"INSERT INTO \"main\".\"Students\" VALUES (2, '���ʽ�', 2, 20130107);",
		};
		
		for (String o : sql) {
			//MySQLiteHelper.getDB(context).;
			db.execSQL(o);
		}
	}

	//�������ݰ汾���������ķ���
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
}
