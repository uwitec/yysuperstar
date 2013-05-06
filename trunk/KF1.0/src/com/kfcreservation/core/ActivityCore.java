package com.kfcreservation.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.kfcreservation.control.KFCMenu;
import com.kfcreservation.dao.impl.FoodsDaoImpl;
import com.kfcreservation.dao.impl.PhoneNumDaoImpl;
import com.kfcreservation.dao.impl.UserAddressDaoImpl;
import com.kfcreservation.dao.impl.UserFoodsDaoImpl;

public class ActivityCore {

	public MyNumAdapter getMyNumAdapter(Context context, int uid) {

		PhoneNumDaoImpl pndi = new PhoneNumDaoImpl();
		List<HashMap<String, Object>> lists = pndi.getAllNumber(context, uid);
		MyNumAdapter adapter = new MyNumAdapter(lists, context);
		return adapter;
	}

	public MyAddressAdapter getMyAddressAdapter(Context context, int uid) {
		UserAddressDaoImpl uadi = new UserAddressDaoImpl();
		List<HashMap<String, Object>> lists = uadi.getAllAddress(context, uid);
		MyAddressAdapter adapter = new MyAddressAdapter(lists, context);
		return adapter;
	}
	
	
	
	
	
	
	public MyMenuListAdapter getFoodListAdapter(Context context, int type) {

		FoodsDaoImpl fad = new FoodsDaoImpl();
		UserFoodsDaoImpl ufd = new UserFoodsDaoImpl();

		List<HashMap<String, Object>> FoodsTypeList = fad.getFoodsType(
				context, type);
		List<HashMap<String, Object>> UserFoodsCountList = ufd
				.getUserFoodsCount(context, AppData.userid, type);

		List<HashMap<String, Object>> MenuList = new ArrayList<HashMap<String, Object>>();

		for (HashMap<String, Object> k : FoodsTypeList) {
			HashMap<String, Object> mp = new HashMap<String, Object>();
			mp.put("id", k.get("_id"));
			mp.put("name", k.get("Name"));
			mp.put("price", k.get("Price"));
			mp.put("imgs", ActivityCore.getBitmapFormAssets(
					context.getAssets(), k.get("Img").toString()));
			for (HashMap<String, Object> j : UserFoodsCountList) {
				if (j.get("Foodid").toString().equals(k.get("_id").toString())) {
					mp.put("count", j.get("Count"));
				}
			}

			if (mp.get("count") == null) {
				mp.put("count", "0");
			}
			MenuList.add(mp);
		}
		MyMenuListAdapter adapter = new MyMenuListAdapter(context, MenuList);

		return adapter;
		
	}

	public static Bitmap getBitmapFormAssets(AssetManager assets, String imageName) {

		InputStream in = null;
		Bitmap bitmap = null;

		try {

			in = assets.open(imageName + ".png");
			bitmap = BitmapFactory.decodeStream(in);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return bitmap;
	}

	public static Bitmap getBitmapFormSdCard(String imageName) {
		String path = Environment.getExternalStorageDirectory()
				+ File.separator + "kfcpng" + File.separator + imageName
				+ ".png";
		Bitmap bitmap = BitmapFactory.decodeFile(path);

		return bitmap;
	}
}
