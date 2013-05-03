package com.kfcreservation.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.kfcreservation.R;
import com.kfcreservation.core.ActivityCore;
import com.kfcreservation.core.AppData;
import com.kfcreservation.core.FoodTypeAdapter;
import com.kfcreservation.core.MyMenuListAdapter;
import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.dao.impl.FoodTypeDaoImpl;
import com.kfcreservation.dao.impl.FoodsDaoImpl;
import com.kfcreservation.dao.impl.UserFoodsDaoImpl;

public class KFCMenu extends Activity {

	public ListView mLvType;
	public ListView mLvAll;
	ImageView mBack;
	// HashMap的key
	String[] from = { "Name" };
	int[] to = { R.id.lists };
	ActivityCore ac = new ActivityCore();

	int currentFoodType = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ExitApplication.getInstance().addActivity(this);
		MySQLiteHelper.getDB(KFCMenu.this);
		setContentView(R.layout.menu_main);
		showImgToast();
		getView();
		
		FoodTypeDaoImpl ftd = new FoodTypeDaoImpl();
		final List<HashMap<String, Object>> lists = ftd.getFoodTypeAll(KFCMenu.this);
		FoodTypeAdapter a =new FoodTypeAdapter(lists,KFCMenu.this);
		mLvType.setAdapter(a);
		mLvAll.setAdapter(ac.getFoodListAdapter(KFCMenu.this, currentFoodType));
	}
	
	private void getView(){
		mLvAll = (ListView) findViewById(R.id.lv_All);
		mLvType = (ListView) findViewById(R.id.lv_Type);
	}
	
	private void showImgToast(){

		// toast 提示
		Toast t = Toast.makeText(KFCMenu.this, R.drawable.tips,
				Toast.LENGTH_SHORT);
		// 创建一个imageView
		ImageView image = new ImageView(KFCMenu.this);
		image.setImageResource(R.drawable.tips);
		// 创建一个linearlayout
		LinearLayout ll = new LinearLayout(KFCMenu.this);
		ll.addView(image);
		t.setView(ll);
		t.show();
	}
//
//	public void init() {
//		// FoodType表list操作
//		FoodTypeDaoImpl ftd = new FoodTypeDaoImpl();
//		final List<HashMap<String, Object>> lists = ftd
//				.getFoodTypeAll(KFCMenu.this);
//		SimpleAdapter adapterFoodType = new SimpleAdapter(this, lists,
//				R.layout.menu_foodtypeitem, from, to);
//
//		mLvType.setAdapter(adapterFoodType);
//
//		// list表点击操作
//		mLvType.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//
//				currentFoodType = position + 1;
//				MyMenuListAdapter mymenulistadapter = new MyMenuListAdapter(
//						KFCMenu.this, getMenuList(currentFoodType));
//				mLvAll.setAdapter(mymenulistadapter);
//			}
//		});
//	}

//	public List<HashMap<String, Object>> getMenuList(int FoodType) {
//		FoodsDaoImpl fad = new FoodsDaoImpl();
//		UserFoodsDaoImpl ufd = new UserFoodsDaoImpl();
//
//		List<HashMap<String, Object>> FoodsTypeList = fad.getFoodsType(
//				KFCMenu.this, FoodType);
//		List<HashMap<String, Object>> UserFoodsCountList = ufd
//				.getUserFoodsCount(KFCMenu.this, AppData.userid, FoodType);
//
//		List<HashMap<String, Object>> MenuList = new ArrayList<HashMap<String, Object>>();
//
//		for (HashMap<String, Object> k : FoodsTypeList) {
//			HashMap<String, Object> mp = new HashMap<String, Object>();
//			mp.put("id", k.get("_id"));
//			mp.put("name", k.get("Name"));
//			mp.put("price", k.get("Price"));
//			mp.put("imgs", ActivityCore.getBitmapFormAssets(
//					KFCMenu.this.getAssets(), k.get("Img").toString()));
//			for (HashMap<String, Object> j : UserFoodsCountList) {
//				if (j.get("Foodid").toString().equals(k.get("_id").toString())) {
//					mp.put("count", j.get("Count"));
//				}
//			}
//
//			if (mp.get("count") == null) {
//				mp.put("count", "0");
//			}
//			MenuList.add(mp);
//		}
//
//		return MenuList;
//	}

	public void RefreshMenuList(int position) {
//		MyMenuListAdapter mymenulistadapter = new MyMenuListAdapter(KFCMenu.this,
//				getMenuList(currentFoodType));
		mLvAll.setAdapter(ac.getFoodListAdapter(KFCMenu.this, position));
	}
}
