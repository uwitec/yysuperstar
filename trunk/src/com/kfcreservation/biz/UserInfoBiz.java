package com.kfcreservation.biz;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Message;

import com.kfcreservation.core.AppConfig;
import com.kfcreservation.dao.impl.UserInfoDaoImpl;
import com.kfcreservation.entity.UserInfo;
import com.kfcreservation.handler.UserInfoHandler;

public class UserInfoBiz extends UserInfoDaoImpl {

	Message msg = UserInfoHandler.handler.obtainMessage();
	
	public boolean UserLogin(Context context, UserInfo userinfo){
		msg.obj = context;
		
		if( userinfo.getPhoneNum() == null || userinfo.getPhoneNum().equals("") ){
			msg.arg1 = 0;
			UserInfoHandler.handler.sendMessage(msg);
			return false;
		}
		
		if( userinfo.getPassword() == null || userinfo.getPassword().equals("") ){
			msg.arg1 = 1;
			UserInfoHandler.handler.sendMessage(msg);
			return false;
		}
		
		List<HashMap<String, Object>> userPhoneNum = super.getPhoneNum(context, userinfo.getPhoneNum().toString());
		if( userPhoneNum.size() > 0){
			//获取用户信息
			List<HashMap<String, Object>> userInfoList = super.getUserInfo(context, userinfo.getPhoneNum().toString(), userinfo.getPassword().toString());
			//存在该用户，登入成功
			if( userInfoList.size() > 0 ){
				msg.arg1 = 3;
				AppConfig.userid = Integer.valueOf(userInfoList.get(0).get("_uid").toString());
				AppConfig.userPhone = (String) userInfoList.get(0).get("PhoneNum");
				AppConfig.Password = (String) userInfoList.get(0).get("Password");
				UserInfoHandler.handler.sendMessage(msg);
				return true;
			}else {
				msg.arg1 = 3;
				UserInfoHandler.handler.sendMessage(msg);
				return false;
			}
			
		}
		
		//注册用户
		if(super.setUser(context, userinfo) > 0){
			//获取用户信息
			List<HashMap<String, Object>> userInfoList = super.getUserInfo(context, userinfo.getPhoneNum().toString(), userinfo.getPassword().toString());
			//存在该用户，登入成功
			if( userInfoList.size() > 0 ){
				msg.arg1 = 3;
				AppConfig.userid = Integer.valueOf(userInfoList.get(0).get("_uid").toString());
				AppConfig.userPhone = (String) userInfoList.get(0).get("PhoneNum");
				AppConfig.Password = (String) userInfoList.get(0).get("Password");
				UserInfoHandler.handler.sendMessage(msg);
				return true;
			}else {
				msg.arg1 = 3;
				UserInfoHandler.handler.sendMessage(msg);
				return true;
			}
		}else {
			msg.arg1 = 4;
			UserInfoHandler.handler.sendMessage(msg);
			return true;
		}
	}
}
