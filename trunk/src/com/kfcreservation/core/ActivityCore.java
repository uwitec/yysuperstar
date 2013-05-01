package com.kfcreservation.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.kfcreservation.dao.impl.PhoneNumDaoImpl;
import com.kfcreservation.dao.impl.UserAddressDaoImpl;

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
