package com.kfcreservation.control;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.kfcreservation.R;
import com.kfcreservation.core.ActivityCore;
import com.kfcreservation.core.ExitApplication;
import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.dao.impl.FoodTypeDaoImpl;

public class KFCMenu extends Activity {

	ListView mLvType;
	ListView mLvAll;
	ImageView mBack;
	// HashMap的key
	String[] from = { "Name" };
	int[] to = { R.id.lists };
	ActivityCore ac = new ActivityCore();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);
		MySQLiteHelper.getDB(KFCMenu.this);
		setContentView(R.layout.menu_main);

		mLvAll = (ListView) findViewById(R.id.lv_All);
		mLvType = (ListView) findViewById(R.id.lv_Type);
		
		

		// FoodType表list操作
		FoodTypeDaoImpl ftd = new FoodTypeDaoImpl();
		final List<HashMap<String, Object>> lists = ftd.getFoodTypeAll(KFCMenu.this);
		SimpleAdapter adapterFoodType = new SimpleAdapter(this, lists,R.layout.menu_lists, from, to);

		mLvType.setAdapter(adapterFoodType);
		
		
		mLvAll.setAdapter(ac.getFoodImageAdapter(KFCMenu.this, 1));
		
		

		// list表点击操作
		mLvType.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mLvAll.setAdapter(ac.getFoodImageAdapter(KFCMenu.this, position+1));
				
			}
		});
		
		
		
		
		
		
	}
}
