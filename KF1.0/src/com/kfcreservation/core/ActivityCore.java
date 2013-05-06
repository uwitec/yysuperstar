package com.kfcreservation.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class ActivityCore {

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
