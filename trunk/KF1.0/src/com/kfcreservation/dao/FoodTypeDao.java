package com.kfcreservation.dao;

import java.util.HashMap;
import java.util.List;

import android.content.Context;

public interface FoodTypeDao {

	public List<HashMap<String, Object>> getFoodTypeAll(Context context);
}
