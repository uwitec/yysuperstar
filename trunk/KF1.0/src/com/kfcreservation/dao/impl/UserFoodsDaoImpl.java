package com.kfcreservation.dao.impl;

import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;

import com.kfcreservation.core.AppData;
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
		ContentValues values = userFoods.getContentValues();
		long count = MySQLiteHelper.getDB(context).update(TableName, values, "Foodid=?", new String[] {String.valueOf(userFoods.getFoodid())} );
		return count;
	}

	@Override
	public long delUserFoods(Context context, UserFoods userFoods) {
		long count = MySQLiteHelper.getDB(context).delete(TableName, "Foodid=?", new String[] {String.valueOf(userFoods.getFoodid())} );
		return count;
	}

	@Override
	public List<HashMap<String, Object>> getUserFoodsByFoodId(Context context,
			int fid) {
		String sql = "SELECT * FROM " + TableName + " WHERE Foodid = "+fid+" AND status = 0"+";";
		List<HashMap<String, Object>> lists = MySQLiteHelper.lQuery(sql);
		return lists;
	}

	@Override
	public List<HashMap<String, Object>> getUserFoodsOrder(Context context,
			int uid) {
		String sql = "SELECT a.foodid,b.Name,a.Count,a.Count * b.Price as SumPrice FROM " + TableName + " as a , \"Foods\" as b" + " WHERE a.Userid = "+ uid +" AND a.serial = "+ AppData.serial +" AND a.Foodid = b.\"_id\""+" AND a.status = 0"+";";
		List<HashMap<String, Object>> lists = MySQLiteHelper.lQuery(sql);
		return lists;
	}

	@Override
	public List<HashMap<String, Object>> getUserFoodsCount(Context context, int uid, int FoodType) {
		String sql = "SELECT a.Foodid, a.Count FROM "+TableName+" as a, \"Foods\" as b WHERE a.Userid = "+ uid +" AND a.serial = "+ AppData.serial +" AND b.FoodType = "+ FoodType +" AND a.Foodid = b.\"_id\";";
		return MySQLiteHelper.lQuery(sql);
	}

	@Override
	public List<HashMap<String, Object>> getUserFoodsCountById(Context context,
			int uid, long serial, int fid) {
		String sql = "SELECT Count FROM "+TableName+" WHERE Userid = "+uid+" AND status = 0"+" AND Serial = "+serial+" AND Foodid = "+fid+";";
		return MySQLiteHelper.lQuery(sql);
	}
}
