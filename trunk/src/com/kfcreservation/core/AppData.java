package com.kfcreservation.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;

public class AppData {
	
	public static int userid = 0;
	public static String userPhone = "";
	public static String Password = "";
	public static long serial = 0;
	
	public static List<HashMap<String, Object>> UserOrderList = new ArrayList<HashMap<String, Object>>();
	public static HashMap<String, Object> ActivityList = new HashMap<String, Object>();
	
	public static void setUserOrderList(List<HashMap<String, Object>> List){
		AppData.UserOrderList = List;
	}
	
	public static void setActivityList(String Key, Activity activity){
		ActivityList.put(Key, activity);
	}
	
	public static Activity getActivityList(String activityName){
		return (Activity)ActivityList.get(activityName);
	}
}
