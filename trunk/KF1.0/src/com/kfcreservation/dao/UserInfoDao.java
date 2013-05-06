package com.kfcreservation.dao;

import java.util.HashMap;
import java.util.List;

import android.content.Context;

import com.kfcreservation.entity.UserInfo;

public interface UserInfoDao {
	
	public List<HashMap<String, Object>> getUserInfo(Context context, String PhoneNum,
			String Password);
	
	public List<HashMap<String, Object>> getUserInfoById(Context context, int Id);
	
	public long setUser(Context context, UserInfo userinfo);
	
	public boolean updUser(Context context, UserInfo userinfo);
	
	public boolean delUser(Context context, int uid);
	
	public List<HashMap<String, Object>> getPhoneNum(Context context, String PhoneNum);

}
