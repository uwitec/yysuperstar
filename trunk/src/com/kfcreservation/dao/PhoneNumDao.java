package com.kfcreservation.dao;

import java.util.HashMap;
import java.util.List;

import android.content.Context;

public interface PhoneNumDao {
	
	public List<HashMap<String,Object>> getAllNumber(Context context,int uid);
	
	public long addPhone(Context context,int _uid,String phonenum);
	
	public void detPhone(Context context,int _uid,String phonenum);

}
