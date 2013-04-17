package com.kfcreservation.core;

import java.util.Vector;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MySQLite {

	// �������ݿ�
	public static SQLiteDatabase createOrOpenDatabase()
	{
		SQLiteDatabase sld = null;
		try {
			// ���Ӳ��������ݿ⣬����������򴴽�
			sld = SQLiteDatabase.openDatabase("/data/data/com.rollbook/databases/mydb", null, SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.CREATE_IF_NECESSARY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sld;// ���ظ�����
	}

	public static boolean insert(String sql)// ��������
	{
		SQLiteDatabase sld = createOrOpenDatabase();// �������ݿ�
		try {
			sld.execSQL(sql);

			sld.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static Vector<Vector<String>> query(String sql)// ��ѯ
	{
		Vector<Vector<String>> vector = new Vector<Vector<String>>();// �½���Ų�ѯ���������
		SQLiteDatabase sld = createOrOpenDatabase();// �õ��������ݿ������

		try {
			Cursor cur = sld.rawQuery(sql, new String[] {});// �õ������

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
			sld.close();// �ر�����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}
	
	// ������
	public static void createTable(String sql) {
		SQLiteDatabase sld = createOrOpenDatabase();// �������ݿ�
		try {
			sld.execSQL(sql);// ִ��SQL���
			//sld.close();// �ر�����
		} catch (Exception e) {

		}
	}

	
}