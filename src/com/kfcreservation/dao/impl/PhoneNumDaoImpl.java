package com.kfcreservation.dao.impl;

import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;

import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.dao.PhoneNumDao;

public class PhoneNumDaoImpl implements PhoneNumDao {
	
	private String tabname="UserPhone";

	@Override
	public List<HashMap<String, Object>> getAllNumber(Context context,int uid) {
		String sql ="select * from " + tabname + " where _uid ="+uid+";";
		System.out.println("iiiii"+sql);
		List<HashMap<String, Object>> lists = MySQLiteHelper.lQuery(sql);
		return lists;
	}

	@Override
	public long addPhone(Context context, int _uid, String phonenum) {
		ContentValues values =new ContentValues();
		values.put("_uid",_uid);
		values.put("phonenum", phonenum);
		long count =MySQLiteHelper.getDB(context).insert("UserPhone", null, values);
		return count;
	}

}
