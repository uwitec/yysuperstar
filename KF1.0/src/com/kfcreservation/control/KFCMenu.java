package com.kfcreservation.control;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.kfcreservation.R;
import com.kfcreservation.biz.FoodTypeBiz;
import com.kfcreservation.biz.FoodsBiz;
import com.kfcreservation.core.AppData;
import com.kfcreservation.core.MySQLiteHelper;

public class KFCMenu extends Activity {

	public ListView mLvFoodType;
	public ListView mLvFoodMenu;
	ImageView mBack;
	// HashMap的key
	String[] from = { "Name" };
	int[] to = { R.id.lists };
	public FoodsBiz foodsbiz = new FoodsBiz();
	public FoodTypeBiz foodtypebiz = new FoodTypeBiz();

	int currentFoodType = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ExitApplication.getInstance().addActivity(this);
		MySQLiteHelper.getDB(KFCMenu.this);
		setContentView(R.layout.menu_main);
		showImgToast();
		getView();
		
		RefreshFoodMenuList(currentFoodType);
		RefreshFoodTypeList();
	}
	
	private void getView(){
		mLvFoodMenu = (ListView) findViewById(R.id.lv_All);
		mLvFoodType = (ListView) findViewById(R.id.lv_Type);
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

	public void RefreshFoodMenuList(int currentFoodType) {
		mLvFoodMenu.setAdapter(foodsbiz.getMyMenuListAdapter(KFCMenu.this, currentFoodType, AppData.userid, AppData.serial));
	}
	
	public void RefreshFoodTypeList() {
		mLvFoodType.setAdapter(foodtypebiz.getMyFoodTypeAdapter(KFCMenu.this));
	}
}
