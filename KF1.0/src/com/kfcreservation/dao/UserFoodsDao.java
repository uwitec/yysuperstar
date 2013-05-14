package com.kfcreservation.dao;

import java.util.HashMap;
import java.util.List;

import android.content.Context;

import com.kfcreservation.entity.UserFoods;

public interface UserFoodsDao {

	public List<HashMap<String, Object>> getUserFoods(Context context, int uid);
	
	public long setUserFoods(Context context, UserFoods userFoods);
	
	public long updUserFoods(Context context, UserFoods userFoods);
	
	public long delUserFoods(Context context, UserFoods userFoods);
	
	public List<HashMap<String, Object>> getUserFoodsByufid(Context context, int ufid);
	
	public List<HashMap<String, Object>> getUserFoodsOrder(Context context, int uid);
	
	public List<HashMap<String, Object>> getUserFoodsCount(Context context, int uid, int FoodType);
	
	public List<HashMap<String, Object>> getUserFoodsCountById(Context context, int uid, long serial, int fid);
	
	public List<HashMap<String, Object>> getUserFoodsufidByFoodid(Context context, int uid, long serial, int fid);
	
	public List<HashMap<String, Object>> getUserFoodsMaxid(Context context);
}
