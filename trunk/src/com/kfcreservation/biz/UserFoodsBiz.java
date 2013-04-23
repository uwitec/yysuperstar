package com.kfcreservation.biz;

import android.content.Context;

import com.kfcreservation.dao.impl.UserFoodsDaoImpl;
import com.kfcreservation.entity.UserFoods;

public class UserFoodsBiz extends UserFoodsDaoImpl {
	
	public boolean AddUserFoods(Context context, UserFoods userfoods){
		
		if( super.getUserFoodsByFoodId(context, userfoods.getFoodid()).size() > 0 ){
			super.updUserFoods(context, userfoods);
			return true;
			
		}else{
			super.setUserFoods(context, userfoods);
			return true;
		}
	}

}
