package com.kfcreservation.dao;

import java.util.HashMap;
import java.util.List;

import android.content.Context;

public interface FoodsDao {

	public List<HashMap<String,Object>> getFoodsType(Context context,int type);

	public List<HashMap<String, Object>> getFoodImg(Context context, int type);
	
	public List<HashMap<String, Object>> getFoodMenuList(Context context, int type, int uid, long Serial);
	
}

	