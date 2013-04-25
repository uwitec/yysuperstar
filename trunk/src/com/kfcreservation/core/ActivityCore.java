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

import com.kfcreservation.control.KFCMenu.ViewHolderM;
import com.kfcreservation.control.KFCMyAdds.ViewHolderP;
import com.kfcreservation.dao.impl.FoodsDaoImpl;
import com.kfcreservation.dao.impl.PhoneNumDaoImpl;

public class ActivityCore {

	public MyNumAdapter getMyNumAdapter(Context context,int uid,ViewHolderP holder){

		ViewHolderP holderp =holder;
		PhoneNumDaoImpl pndi =new PhoneNumDaoImpl();
		List<HashMap<String,Object>> lists =pndi.getAllNumber(context, uid);
		MyNumAdapter adapter =new MyNumAdapter(lists,holderp,context);
		return adapter;
	}

//	public OrderListAdapter getOrderListAdapter(Context context, int uid,
//			ViewHolderSC holder){
//		
//		ViewHolderSC holdersc = holder;
//
//		UserFoodsDaoImpl ufd = new UserFoodsDaoImpl();
//		List<HashMap<String, Object>> UserFoodsList = ufd.getUserFoodsOrder(context, uid);
//
//		OrderListAdapter adapter = new OrderListAdapter(context, UserFoodsList, holdersc);
//
//		return adapter;
//		
//	}
	
	public FoodListAdapter getFoodListAdapter(Context context, int FoodTypeId,
			ViewHolderM holder) {

		ViewHolderM holderm = holder;

		FoodsDaoImpl fad = new FoodsDaoImpl();
		List<HashMap<String, Object>> imageList = fad.getFoodsType(context,
				FoodTypeId);

		List<HashMap<String, Object>> ls = new ArrayList<HashMap<String, Object>>();

		for (HashMap<String, Object> k : imageList) {

			HashMap<String, Object> mp = null;
			mp = new HashMap<String, Object>();
			mp.put("id", k.get("_id"));
			mp.put("name", k.get("Name"));
			mp.put("price", k.get("Price"));
			mp.put("imgs",
					getBitmapFormAssets(context.getAssets(), k.get("Img")
							.toString()));
			ls.add(mp);
		}

		FoodListAdapter adapter = new FoodListAdapter(context, ls, holderm);

		return adapter;
	}

	public Bitmap getBitmapFormAssets(AssetManager assets, String imageName) {

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

	public Bitmap getBitmapFormSdCard(String imageName) {
		String path = Environment.getExternalStorageDirectory()
				+ File.separator + "kfcpng" + File.separator + imageName
				+ ".png";
		Bitmap bitmap = BitmapFactory.decodeFile(path);

		return bitmap;
	}
}
