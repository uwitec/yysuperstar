package com.kfcreservation.control;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.kfcreservation.R;
import com.kfcreservation.core.AppData;
import com.kfcreservation.core.ExitApplication;
import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.dao.UserFoodsDao;
import com.kfcreservation.dao.impl.UserFoodsDaoImpl;
import com.kfcreservation.handler.KFCShoppingCarHandler;
import com.kfcreservation.provide.MyShoppingCarListAdapter;

public class KFCShoppingCar extends Activity {

	ListView orderlist;
	TextView tv_subtotal_num;
	TextView tv_delivery_num;
	TextView tv_total_num;
	Button ib_clear;
	Message msg;
	
	MyShoppingCarListAdapter myshoppingcarlistadapter;
	
	UserFoodsDao userfoodsdao = new UserFoodsDaoImpl();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 ExitApplication.getInstance().addActivity(this);
		AppData.setActivityList("KFCShoppingCar", (KFCShoppingCar)KFCShoppingCar.this);
		MySQLiteHelper.getDB(KFCShoppingCar.this);

		setContentView(R.layout.shoppingcar_main);

		orderlist = (ListView) findViewById(R.id.orderlist);
		tv_subtotal_num = (TextView) findViewById(R.id.tv_subtotal_num);
		tv_delivery_num = (TextView) findViewById(R.id.tv_delivery_num);
		tv_total_num = (TextView) findViewById(R.id.tv_total_num);
		ib_clear = (Button) findViewById(R.id.ib_clear);

	}

	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("----KFCShoppingCar onStart----");
		init();
		setShoppingCarlist();
		setTotal();
//		Thread subthread = new Thread(){
//			Message msg1;
//	
//			@Override
//			public void run() {
////				msg = KFCShoppingCarHandler.RefreshShoppingCarHandler.obtainMessage();
////				msg.obj = KFCShoppingCar.this;
//				Looper.prepare();
//				while(true){
//					try {
//						sleep(10000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					
//					msg1 = KFCShoppingCarHandler.RefreshShoppingCarHandler.obtainMessage();
//					msg1.obj = KFCShoppingCar.this;
//					KFCShoppingCarHandler.RefreshShoppingCarHandler.sendMessage(msg1);
//					Looper.loop();
//					System.out.println("----KFCShoppingCar Thread subthread----");
//				}
//			}
//		};
//	
//		subthread.start();
	}

	public void init() {
		ib_clear.setOnClickListener(set_ib_clear_onclicklistener);
	}

	public void setShoppingCarlist() {
		System.out.println("----setShoppingCarlist----" + " ThreadName:" +Thread.currentThread().getName());
		List<HashMap<String, Object>> UserFoodsList = userfoodsdao.getUserFoodsOrder(KFCShoppingCar.this, AppData.userid);
		myshoppingcarlistadapter = new MyShoppingCarListAdapter(KFCShoppingCar.this,UserFoodsList);
		myshoppingcarlistadapter.addData(UserFoodsList);
		orderlist.setAdapter(myshoppingcarlistadapter);
	}
	
	public void ReloadShoppingCarlist() {
		System.out.println("----ReloadShoppingCarlist----");
		List<HashMap<String, Object>> UserFoodsList = userfoodsdao.getUserFoodsOrder(KFCShoppingCar.this, AppData.userid);
		myshoppingcarlistadapter = new MyShoppingCarListAdapter(KFCShoppingCar.this,UserFoodsList);
		myshoppingcarlistadapter.addData(UserFoodsList);
		myshoppingcarlistadapter.notifyDataSetChanged();
		orderlist.setAdapter(myshoppingcarlistadapter);
	}

	public void setTotal(){
		float subtotal = 0.0f;
		float delivery = 0.0f;
		float total = 0.0f;
		List<HashMap<String, Object>> UserFoodsList = userfoodsdao.getUserFoodsOrder(KFCShoppingCar.this, AppData.userid);
		for (HashMap<String, Object> k : UserFoodsList) {
			subtotal += Float.valueOf(k.get("SumPrice").toString());
		}

		if (subtotal < 39.0f) {
			delivery = 8.0f;
		}

		total = subtotal + delivery;

		tv_subtotal_num.setText("" + subtotal);
		tv_delivery_num.setText("" + delivery);
		tv_total_num.setText("" + total);
		
		//handler ´«Êý¾Ý
		msg = KFCOrder.handler.obtainMessage();
		msg.obj=total;
		KFCOrder.handler.sendMessage(msg);
	}
	
	public void RefreshTotal() {
		
	}

	OnClickListener set_ib_clear_onclicklistener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			setShoppingCarlist();
			setTotal();
		}
	};
}
