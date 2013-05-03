package com.kfcreservation.control;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.kfcreservation.R;
import com.kfcreservation.core.AppData;
import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.core.MyShoppingCarListAdapter;

public class KFCShoppingCar extends Activity {

	ListView orderlist;
	TextView tv_subtotal_num;
	TextView tv_delivery_num;
	TextView tv_total_num;
	Button ib_clear;
	Message msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ExitApplication.getInstance().addActivity(this);
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
		init();
		RefreshShoppingCarlist();
		RefreshTotal();
	}

	public void init() {
		ib_clear.setOnClickListener(set_ib_clear_onclicklistener);
	}

	public void RefreshShoppingCarlist() {
		orderlist.setAdapter(new MyShoppingCarListAdapter(KFCShoppingCar.this,
				AppData.UserOrderList));
	}

	public void RefreshTotal() {
		float subtotal = 0.0f;
		float delivery = 0.0f;
		float total = 0.0f;

		for (HashMap<String, Object> k : AppData.UserOrderList) {
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

	OnClickListener set_ib_clear_onclicklistener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			RefreshShoppingCarlist();
			RefreshTotal();
		}
	};
}
