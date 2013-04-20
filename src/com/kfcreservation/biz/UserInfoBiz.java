package com.kfcreservation.biz;

import android.content.Context;
import android.os.Message;

import com.kfcreservation.dao.impl.UserInfoDaoImpl;
import com.kfcreservation.entity.UserInfo;
import com.kfcreservation.handler.UserInfoHandler;

public class UserInfoBiz extends UserInfoDaoImpl {

	Message msg = UserInfoHandler.handler.obtainMessage();
	
	public boolean CheckUser(Context context, UserInfo userinfo){
		msg.obj=context;
		if( userinfo.getPhoneNum() == null || userinfo.getPhoneNum().equals("") ){
			msg.arg1 = 0;
			UserInfoHandler.handler.sendMessage(msg);
			return false;
		}else if( userinfo.getPassword() == null || userinfo.getPassword().equals("") ){
			msg.arg1 = 1;
			UserInfoHandler.handler.sendMessage(msg);
			return false;
		}else if( super.getUser(context, userinfo.getPhoneNum().toString(), userinfo.getPassword().toString()).size() > 0 ){
			msg.arg1 = 2;
			UserInfoHandler.handler.sendMessage(msg);
			return true;
		}else{
			if( super.setUser(context, userinfo) > 0 ){
				msg.arg1 = 3;
				UserInfoHandler.handler.sendMessage(msg);
				return true;
			}
		}
		return false;
	}
}
