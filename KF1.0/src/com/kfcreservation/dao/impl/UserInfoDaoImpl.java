package com.kfcreservation.dao.impl;

import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;

import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.dao.UserInfoDao;
import com.kfcreservation.entity.UserInfo;

public class UserInfoDaoImpl implements UserInfoDao {

	private String TableName = "UserInfo";
	
	@Override
	public List<HashMap<String, Object>> getUserInfo(Context context, String PhoneNum, String Password) {
		String sql = "SELECT * FROM " + TableName + " WHERE PhoneNum = \""+ PhoneNum +"\" AND Password = \""+Password+"\";";
		List<HashMap<String, Object>> lists = MySQLiteHelper.lQuery(sql);
		return lists;
	}

	@Override
	public List<HashMap<String, Object>> getUserInfoById(Context context, int uid) {
		String sql = "SELECT * FROM " + TableName + " WHERE _uid = " + uid + ";";
		List<HashMap<String, Object>> lists = MySQLiteHelper.lQuery(sql);
		return lists;
	}

	@Override
	public long setUser(Context context, UserInfo userinfo) {
		ContentValues values = userinfo.getContentValues();
		long count = MySQLiteHelper.getDB(context).insert(TableName, null, values);
		return count;
	}

	@Override
	public boolean updUser(Context context, UserInfo userinfo) {
		return false;
	}

	@Override
	public boolean delUser(Context context, int uid) {
		return false;
	}

	@Override
	public List<HashMap<String, Object>> getPhoneNum(Context context, String PhoneNum) {
		String sql = "SELECT * FROM " + TableName + " WHERE PhoneNum = \""+ PhoneNum +"\";";
		List<HashMap<String, Object>> lists = MySQLiteHelper.lQuery(sql);
		return lists;
	}

}
