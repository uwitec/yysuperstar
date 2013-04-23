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

import com.kfcreservation.control.KFCMenu.ViewHolder;
import com.kfcreservation.dao.impl.FoodsDaoImpl;

public class ActivityCore {

	public FoodListAdapter getFoodListAdapter(Context context, int FoodTypeId,
			ViewHolder holder) {

		ViewHolder holderL = holder;

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

		FoodListAdapter adapter = new FoodListAdapter(context, ls, holderL);

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
