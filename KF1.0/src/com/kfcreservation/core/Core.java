package com.kfcreservation.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Core {

	// �������
	public static Vector<Vector<String>> combine (Vector<Vector<String>> temp1, Vector<Vector<String>> temp2, Vector<Vector<String>> temp3) {// ��������Vector��ϳ�һ��
		for (int i = 0; i < temp1.size(); i++) {
			Vector<String> v1 = temp1.get(i);
			if (i < temp2.size()) {
				Vector<String> v2 = temp2.get(i);
				// ��V2�����Ԫ�ؼӵ�V1����
				for (int j = 0; j < v2.size(); j++) {
					v1.add(v2.get(j));
				}
			} else {
				// û�й�ϵʱ��ӿ�
				v1.add("");
				v1.add("");
			}
		}
		
		for (int i = 0; i < temp1.size(); i++) {
			Vector<String> v1 = temp1.get(i);
			if (i < temp3.size()) {
				Vector<String> v2 = temp3.get(i);
				// ��V2�����Ԫ�ؼӵ�V1����
				for (int j = 0; j < v2.size(); j++) {
					v1.add(v2.get(j));
				}
			} else {
				// û�й�ϵʱ��ӿ�
				v1.add("");
			}

		}
		return temp1;
	}

	// ��ѯ�����ı���ID�����ֵ
	public static int getInsertId(String name, String tid) {
		int id = 0;
		String sql = "select Max(" + tid + ") from " + name;

		SQLiteDatabase sld = MySQLite.createOrOpenDatabase();

		try {
			Cursor cur = sld.rawQuery(sql, new String[] {});

			// �鿴�����
			if (cur.moveToNext()) {
				id = cur.getInt(0);
			}
			// �رս����,��估����
			cur.close();
			sld.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		id++;
		return id;
	}
	
	public static String insertFields(HashMap<String, Object> map){
		String key = "";
		String values = "";
		
		String s = "";
		
		int curr = 0;
		
		Iterator<?> iter = map.entrySet().iterator(); 
		while (iter.hasNext()) {     
			Map.Entry<?,?> entry = (Map.Entry<?,?>) iter.next();     
			
			key += entry.getKey();
			if( curr < (int) (map.size() - 1) ){
				key += ", ";
			}
			
			values += "\"" + entry.getValue() + "\"";
			if( curr < (int) (map.size() - 1) ){
				values += ", ";
			}
			
			curr++;
		}

		s = "(" + key + ")" + " VALUES " + "(" + values + ")";
		
		return s;
	}
}
