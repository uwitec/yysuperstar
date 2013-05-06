package com.kfcreservation.biz;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Message;
import android.widget.ListAdapter;

import com.kfcreservation.core.ActivityCore;
import com.kfcreservation.dao.impl.FoodsDaoImpl;
import com.kfcreservation.provide.MyMenuListAdapter;

public class FoodsBiz extends FoodsDaoImpl {
	Message msg;
	
	public MyMenuListAdapter getMyMenuListAdapter(Context context, int currentFoodType, int uid, long Serial){
		List<HashMap<String, Object>> foodmenulist = super.getFoodMenuList(context, currentFoodType, uid, Serial);
		
		for (HashMap<String, Object> k : foodmenulist) {
			String imgName = k.get("Img").toString();
			k.remove("Img");
			k.put("Img", ActivityCore.getBitmapFormAssets( context.getAssets(), imgName) );
		}
		return new MyMenuListAdapter(context, foodmenulist);
	}
}
