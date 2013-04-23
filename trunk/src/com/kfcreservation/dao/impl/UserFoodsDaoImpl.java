package com.kfcreservation.dao.impl;

import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;

import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.dao.UserFoodsDao;
import com.kfcreservation.entity.UserFoods;

public class UserFoodsDaoImpl implements UserFoodsDao{

	private String TableName = "UserFoods";
	
	@Override
	public List<HashMap<String, Object>> getUserFoods(Context context, int uid) {
		String sql = "SELECT * FROM " + TableName + " WHERE _ufid = \""+ uid +"\";";
		List<HashMap<String, Object>> lists = MySQLiteHelper.lQuery(sql);
		return lists;
	}

	@Override
	public long setUserFoods(Context context, UserFoods userFoods) {
		ContentValues values = userFoods.getContentValues();
		long count = MySQLiteHelper.getDB(context).insert(TableName, null, values);
		return count;
	}

	@Override
	public long updUserFoods(Context context, UserFoods userFoods) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long delUserFoods(Context context, UserFoods userFoods) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<HashMap<String, Object>> getUserFoodsByFoodId(Context context,
			int fid) {
		String sql = "SELECT * FROM " + TableName + " WHERE Foodid = \""+ fid +"\";";
		List<HashMap<String, Object>> lists = MySQLiteHelper.lQuery(sql);
		return lists;
	}

}
