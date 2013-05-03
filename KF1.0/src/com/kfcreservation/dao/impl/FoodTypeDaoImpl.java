package com.kfcreservation.dao.impl;

import java.util.HashMap;
import java.util.List;

import android.content.Context;

import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.dao.FoodTypeDao;

public class FoodTypeDaoImpl implements FoodTypeDao {

	private String TableName = "FoodType";
	
	@Override
	public List<HashMap<String, Object>> getFoodTypeAll(Context context) {
		String sql = "select * from " + TableName + ";";
		List<HashMap<String, Object>> lists = MySQLiteHelper.lQuery(sql);
		return lists;
	}
}
