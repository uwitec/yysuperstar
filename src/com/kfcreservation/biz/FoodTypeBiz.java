package com.kfcreservation.biz;

import android.os.Message;

import com.kfcreservation.dao.impl.FoodTypeDaoImpl;
import com.kfcreservation.handler.FoodTypeHandler;

public class FoodTypeBiz extends FoodTypeDaoImpl  {
	
	Message msg = FoodTypeHandler.handler.obtainMessage();
}
