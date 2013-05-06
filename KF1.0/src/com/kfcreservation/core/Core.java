package com.kfcreservation.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Core {

	// 组合向量
	public static Vector<Vector<String>> combine (Vector<Vector<String>> temp1, Vector<Vector<String>> temp2, Vector<Vector<String>> temp3) {// 将这三个Vector组合成一个
		for (int i = 0; i < temp1.size(); i++) {
			Vector<String> v1 = temp1.get(i);
			if (i < temp2.size()) {
				Vector<String> v2 = temp2.get(i);
				// 将V2里面的元素加到V1里面
				for (int j = 0; j < v2.size(); j++) {
					v1.add(v2.get(j));
				}
			} else {
				// 没有关系时添加空
				v1.add("");
				v1.add("");
			}
		}
		
		for (int i = 0; i < temp1.size(); i++) {
			Vector<String> v1 = temp1.get(i);
			if (i < temp3.size()) {
				Vector<String> v2 = temp3.get(i);
				// 将V2里面的元素加到V1里面
				for (int j = 0; j < v2.size(); j++) {
					v1.add(v2.get(j));
				}
			} else {
				// 没有关系时添加空
				v1.add("");
			}

		}
		return temp1;
	}

	// 查询其插入的表项ID的最大值
	public static int getInsertId(String name, String tid) {
		int id = 0;
		String sql = "select Max(" + tid + ") from " + name;

		SQLiteDatabase sld = MySQLite.createOrOpenDatabase();

		try {
			Cursor cur = sld.rawQuery(sql, new String[] {});

			// 查看结果集
			if (cur.moveToNext()) {
				id = cur.getInt(0);
			}
			// 关闭结果集,语句及连接
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
