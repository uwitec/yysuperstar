package com.kfcreservation.control;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kfcreservation.R;
import com.kfcreservation.core.ExitApplication;
import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.dao.impl.FoodAllDaoImpl;
import com.kfcreservation.dao.impl.FoodTypeDaoImpl;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;

public class KFCMenu extends Activity {
	
	TextView mTest;
	ListView mLvType;
	ListView mLvAll;
	//HashMap的key
	String [] from={"Name"};
	int [] to = {R.id.lists};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);
		MySQLiteHelper.getDB(KFCMenu.this);
		setContentView(R.layout.menu_main);
		
		mLvAll=(ListView) findViewById(R.id.lv_All);
		mLvType=(ListView) findViewById(R.id.lv_Type);
		
		//FoodType表list操作
		FoodTypeDaoImpl ftd = new FoodTypeDaoImpl();
		final List<HashMap<String,Object>> lists = ftd.getFoodTypeAll(KFCMenu.this);
		SimpleAdapter adapterFoodType = new SimpleAdapter(this, lists, R.layout.menu_lists, from, to);
		
		mLvType.setAdapter(adapterFoodType);
		
		//mLvAll.setAdapter(getAdapter(1));
				
		//list表点击操作
		mLvType.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position){
				case 0:
					mLvAll.setAdapter(getAdapter(1));
					break;
				case 1:
					mLvAll.setAdapter(getAdapter(2));
					break;
				case 2:
					mLvAll.setAdapter(getAdapter(3));
					break;
				case 3:
					mLvAll.setAdapter(getAdapter(4));
					break;
				case 4:
					mLvAll.setAdapter(getAdapter(5));
					break;
				}
			}
		});
	}
	
	public SimpleAdapter getAdapter(int FoodTypeId){
		String [] f ={"name","price","imgs"};
		int [] t ={R.id.tv_name,R.id.tv_price,R.id.im_img};
		FoodAllDaoImpl fad = new FoodAllDaoImpl();
		//List<KFC> kfcs = fb.getbytype(this, idr);
		List<HashMap<String, Object>> imageList = fad.getFoodAllType(this, FoodTypeId);
		
		List<HashMap<String,Object>> ls =new ArrayList<HashMap<String,Object>>();
		
		for(HashMap<String, Object> k:imageList){
			
			HashMap<String,Object> mp = null;
			mp =new HashMap<String,Object>();
			mp.put("name", k.get("Name"));
			mp.put("price", k.get("Price"));
			mp.put("imgs", getBitmap(k.get("Img").toString()));
			ls.add(mp);
		}
		
		SimpleAdapter adapter =new SimpleAdapter(this,ls,R.layout.menu_list,f,t);
		adapter.setViewBinder(new ViewBinder(){

			@Override
			public boolean setViewValue(View view, Object data,
					String textRepresentation) {
				if(view instanceof ImageView &&data instanceof Bitmap){
					ImageView iv =(ImageView)view;
					iv.setImageBitmap((Bitmap)data);
					return true;
				}
				return false;
			}});
		return adapter;
	}
	
	public Bitmap getBitmap(String imageName){
		String path = Environment.getExternalStorageDirectory()+File.separator+"kfcpng"+File.separator+imageName+".png";
		Bitmap bitmap = BitmapFactory.decodeFile(path);
		return bitmap;
	}

}
