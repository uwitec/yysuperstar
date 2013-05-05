package com.kfcreservation.biz;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Message;

import com.kfcreservation.core.AppData;
import com.kfcreservation.dao.impl.UserInfoDaoImpl;
import com.kfcreservation.entity.UserInfo;
import com.kfcreservation.handler.KFCLoginHandler;

public class UserInfoBiz extends UserInfoDaoImpl {

	Message msg;

	public boolean UserLogin(Context context, UserInfo userinfo) {
		msg = KFCLoginHandler.LoginHandler.obtainMessage();
		msg.obj = context;
		
		if (userinfo.getPhoneNum() == null || userinfo.getPhoneNum().equals("")) {
			msg.arg1 = 0;
			KFCLoginHandler.LoginHandler.sendMessage(msg);
			return false;
		}

		if (userinfo.getPassword() == null || userinfo.getPassword().equals("")) {
			msg.arg1 = 1;
			KFCLoginHandler.LoginHandler.sendMessage(msg);
			return false;
		}

		List<HashMap<String, Object>> userPhoneNum = super.getPhoneNum(context,
				userinfo.getPhoneNum().toString());
		
		if(userPhoneNum.size() == 0){
			super.setUser(context, userinfo); //如果没有该号码的记录，注册为新用户
		}
		
		// 获取用户信息
		List<HashMap<String, Object>> userInfoList = super.getUserInfo(
				context, userinfo.getPhoneNum().toString(), userinfo
						.getPassword().toString());
		// 存在该用户，登入成功
		if (userInfoList.size() > 0) {
			AppData.userid = Integer.valueOf(userInfoList.get(0)
					.get("_uid").toString());
			AppData.userPhone = (String) userInfoList.get(0)
					.get("PhoneNum");
			AppData.Password = (String) userInfoList.get(0).get("Password");
			AppData.serial = System.currentTimeMillis();
			msg.arg1 = 3;
			KFCLoginHandler.LoginHandler.sendMessage(msg);
			return true;
		} else {
			msg.arg1 = 4;
			KFCLoginHandler.LoginHandler.sendMessage(msg);
			return false;
		}
	}
}
