package com.kfcreservation.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

import com.kfcreservation.R;
import com.kfcreservation.control.KFCMenu.ViewHolder;
import com.kfcreservation.dao.impl.FoodAllDaoImpl;

public class ActivityCore {
	
//	public SimpleAdapter getFoodImageAdapter(Context context, int FoodTypeId) {
//		
//		String[] f = { "name", "price", "imgs" };
//		int[] t = { R.id.tv_name, R.id.tv_price, R.id.im_img };
//		
//		FoodAllDaoImpl fad = new FoodAllDaoImpl();
//		List<HashMap<String, Object>> imageList = fad.getFoodAllType(context, FoodTypeId);
//
//		List<HashMap<String, Object>> ls = new ArrayList<HashMap<String, Object>>();
//
//		for (HashMap<String, Object> k : imageList) {
//
//			HashMap<String, Object> mp = null;
//			mp = new HashMap<String, Object>();
//			mp.put("name", k.get("Name"));
//			mp.put("price", k.get("Price"));
//			mp.put("imgs", getBitmap(k.get("Img").toString()));
//			ls.add(mp);
//		}
//
//		SimpleAdapter adapter = new SimpleAdapter(context, ls,
//				R.layout.menu_list, f, t);
//		adapter.setViewBinder(new ViewBinder() {
//
//			@Override
//			public boolean setViewValue(View view, Object data,
//					String textRepresentation) {
//				if (view instanceof ImageView && data instanceof Bitmap) {
//					ImageView iv = (ImageView) view;
//					iv.setImageBitmap((Bitmap) data);
//					return true;
//				}
//				return false;
//			}
//		});
//		
//		return adapter;
//	}
	
	public MyAdapter getFoodImageMyAdapter(Context context, int FoodTypeId, ViewHolder holder) {

		ViewHolder holderL = holder;
		
		FoodAllDaoImpl fad = new FoodAllDaoImpl();
		List<HashMap<String, Object>> imageList = fad.getFoodAllType(context, FoodTypeId);

		List<HashMap<String, Object>> ls = new ArrayList<HashMap<String, Object>>();

		for (HashMap<String, Object> k : imageList) {

			HashMap<String, Object> mp = null;
			mp = new HashMap<String, Object>();
			mp.put("name", k.get("Name"));
			mp.put("price", k.get("Price"));
			mp.put("imgs", getBitmap(k.get("Img").toString()));
			ls.add(mp);
		}

		MyAdapter adapter = new MyAdapter(context, ls, holderL);
		
//		SimpleAdapter adapter = new SimpleAdapter(context, ls,
//				R.layout.menu_list, f, t);
//		adapter.setViewBinder(new ViewBinder() {
//
//			@Override
//			public boolean setViewValue(View view, Object data,
//					String textRepresentation) {
//				if (view instanceof ImageView && data instanceof Bitmap) {
//					ImageView iv = (ImageView) view;
//					iv.setImageBitmap((Bitmap) data);
//					return true;
//				}
//				return false;
//			}
//		});
		
		return adapter;
	}
	
	public Bitmap getBitmap(String imageName) {
		String path = Environment.getExternalStorageDirectory()
				+ File.separator + "kfcpng" + File.separator + imageName
				+ ".png";
		Bitmap bitmap = BitmapFactory.decodeFile(path);
		return bitmap;
	}
}
