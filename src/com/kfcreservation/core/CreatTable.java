package com.kfcreservation.core;

public class CreatTable {
	public static void creattable() {
		try {
			String sql[] = new String[] 
			{
				//ϵͳ��
				"CREATE TABLE IF NOT EXISTS android_metadata (locale TEXT);",
				"INSERT INTO \"main\".\"android_metadata\" VALUES (\'en_US\');",
				
//				//sequence��
//				"CREATE TABLE IF NOT EXISTS sqlite_sequence(name,seq);",
//				"INSERT INTO \"main\".\"sqlite_sequence\" VALUES ('Students', 2);",
//				"INSERT INTO \"main\".\"sqlite_sequence\" VALUES ('ClassesType', 2);",
//				"INSERT INTO \"main\".\"sqlite_sequence\" VALUES ('_Classes_old_20130316', 5);",
//				"INSERT INTO \"main\".\"sqlite_sequence\" VALUES ('Classes', 5);",
					
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
			
			// ѭ������SQL��䣬���н���ͳ�ʼ��һЩ���ݲ���
			for (String o : sql) {
				MySQLite.createTable(o);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
