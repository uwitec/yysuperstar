package com.kfcreservation.biz;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Message;

import com.kfcreservation.dao.impl.FoodTypeDaoImpl;
import com.kfcreservation.provide.MyFoodTypeAdapter;

public class FoodTypeBiz extends FoodTypeDaoImpl  {
	
	Message msg;
	
	public MyFoodTypeAdapter getMyFoodTypeAdapter(Context context){
		List<HashMap<String, Object>> foodtypelist = super.getFoodTypeAll(context);
		return new MyFoodTypeAdapter(context,foodtypelist);
	}
}
