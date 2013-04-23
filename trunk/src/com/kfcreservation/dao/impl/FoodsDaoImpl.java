package com.kfcreservation.dao.impl;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.dao.FoodsDao;

public class FoodsDaoImpl implements FoodsDao {
	
	String TableName = "Foods" ;

	@Override
	public List<HashMap<String, Object>> getFoodsType(Context context,int type) {
		String sql=" select _id,Name,Img,Price from " + TableName + " where FoodType = " + type ;
		List<HashMap<String,Object>> list = MySQLiteHelper.lQuery(sql);
		return list;
	}

	@Override
	public List<HashMap<String, Object>> getFoodImg(Context context,int type) {
		String sql=" select Img from " + TableName + " where FoodType = " + type ;
		List<HashMap<String,Object>> list = MySQLiteHelper.lQuery(sql);
		return list;
		
	}
}
