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
		String sql=" select _id,Name,Img,Price from " + TableName + " where FoodType = " + type + ";";
		return MySQLiteHelper.lQuery(sql);
	}

	@Override
	public List<HashMap<String, Object>> getFoodImg(Context context,int type) {
		String sql=" select Img from " + TableName + " where FoodType = " + type + ";";
		return MySQLiteHelper.lQuery(sql);
		
	}

	@Override
	public List<HashMap<String, Object>> getFoodMenuList(Context context,
			int type, int uid ,long Serial) {
		String sql="select a.\"_id\", a.Name, a.Price, a.FoodType, a.Img as Img, ifnull(b.Count,0) as Count from Foods as a LEFT JOIN UserFoods as b on a.\"_id\" = b.Foodid AND b.Userid = "+uid+" AND b.Serial = "+Serial+" AND b.status = 0"+" WHERE a.FoodType = "+type+";";
		return MySQLiteHelper.lQuery(sql);
	}
}
