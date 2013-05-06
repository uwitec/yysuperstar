package com.kfcreservation.dao.impl;

import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;

import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.dao.UserAddressDao;

public class UserAddressDaoImpl implements UserAddressDao {
	private String tbname="UserAddress";

	@Override
	public List<HashMap<String, Object>> getAllAddress(Context context, int Uid) {
		String sql ="select * from " + tbname + " where _uid ="+Uid+";";
		List<HashMap<String, Object>> lists = MySQLiteHelper.lQuery(sql);
		return lists;
		
	}

	@Override
	public long addAddress(Context context, int uid, String address) {
		ContentValues values =new ContentValues();
		values.put("_uid",uid);
		values.put("Address", address);
		long count =MySQLiteHelper.getDB(context).insert("UserAddress", null, values);
		return count;
	}

	@Override
	public void detAddress(Context context, int uid, String address) {
		// TODO Auto-generated method stub
		String sql ="DELETE FROM "+tbname+" where _Uid ="+uid+" and Address = '"+address+"';";
		System.out.println(sql);
		MySQLiteHelper.getDB(context).execSQL(sql);
		System.out.println("É¾³ý³É¹¦");
	}

}
