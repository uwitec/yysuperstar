package com.kfcreservation.dao;

import java.util.HashMap;
import java.util.List;

import android.content.Context;

public interface FoodAllDao {

	public List<HashMap<String,Object>> getFoodAllType(Context context,int type);

	public List<HashMap<String, Object>> getFoodImg(Context context, int type);
}

	