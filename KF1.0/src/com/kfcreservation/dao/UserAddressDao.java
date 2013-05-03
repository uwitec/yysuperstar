package com.kfcreservation.dao;

import java.util.HashMap;
import java.util.List;

import android.content.Context;

public interface UserAddressDao {
	
	public List<HashMap<String,Object>> getAllAddress(Context context,int uid);
	
	public long addAddress(Context context,int uid,String address);
	
	public void detAddress(Context context,int uid,String address);

}
