package com.kfcreservation.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kfcreservation.entity.FoodType;
import com.kfcreservation.R;
import com.kfcreservation.core.ExitApplication;
import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.dao.impl.FoodTypeDaoImpl;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class KFCMenu extends Activity {
	
	TextView mTest;
	ListView mLvType;
	ListView mLvAll;
	//HashMapµÄkey
	String [] from={"Name"};
	int [] to = {R.id.lists};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);
		setContentView(R.layout.menu_main);
		
		MySQLiteHelper.getDB(KFCMenu.this);
		
		FoodTypeDaoImpl ftd = new FoodTypeDaoImpl();
		List<HashMap<String,Object>> lists = ftd.getFoodTypeAll(KFCMenu.this);
		
		SimpleAdapter adapter = new SimpleAdapter(this, lists, R.layout.menu_lists, from, to);
		
		
		mLvType=(ListView) findViewById(R.id.lv_Type);
		
		mLvType.setAdapter(adapter);

		mTest=(TextView) findViewById(R.id.test);
		mTest.setText("  .");
		
	}

}
