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
	
	public List<HashMap<String, Object>> getUserFoodsByFoodId(Context context, int fid);
	
	public List<HashMap<String, Object>> getUserFoodsOrder(Context context, int uid);
	
	public List<HashMap<String, Object>> getUserFoodsCount(Context context, int uid, int FoodType);
}
