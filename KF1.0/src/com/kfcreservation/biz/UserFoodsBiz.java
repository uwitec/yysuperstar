package com.kfcreservation.biz;

import android.content.Context;
import android.os.Message;
import com.kfcreservation.dao.impl.UserFoodsDaoImpl;
import com.kfcreservation.entity.UserFoods;
import com.kfcreservation.handler.KFCMenuHandler;


public class UserFoodsBiz extends UserFoodsDaoImpl {

	Message msg;

	public boolean AddUserFoods(Context context, UserFoods userfoods) {
		if( super.getUserFoodsByFoodId(context, userfoods.getFoodid()).size() > 0 ){
			super.updUserFoods(context, userfoods);
			msg = KFCMenuHandler.mHandler.obtainMessage();
			msg.obj = context;
			msg.arg1 = 0;
			
			KFCMenuHandler.mHandler.sendMessage(msg);
			return true;
			
		}else{
			super.setUserFoods(context, userfoods);
			msg = KFCMenuHandler.mHandler.obtainMessage();
			msg.obj = context;
			msg.arg1 = 0;

			KFCMenuHandler.mHandler.sendMessage(msg);
			return true;
		}
	}

}
