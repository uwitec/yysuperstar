package com.kfcreservation.control;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kfcreservation.R;
import com.kfcreservation.core.ActivityCore;
import com.kfcreservation.core.AppConfig;
import com.kfcreservation.core.ExitApplication;
import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.core.OrderListAdapter;
import com.kfcreservation.dao.impl.UserFoodsDaoImpl;

public class KFCShoppingCar extends Activity {
	
	ListView orderlist;
	
	TextView tv_subtotal;
	TextView tv_delivery;
	TextView tv_total;
	
	ImageButton ib_clear;
	
	OrderListAdapter orderlistadapter = null;
	
	ActivityCore ac = new ActivityCore();
	
	public ViewHolderSC holder = new ViewHolderSC();

	public final class ViewHolderSC {
		public TextView tv_name;
		public EditText et_count;
		public TextView tv_price;
		public Button bt_cancel;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);
		MySQLiteHelper.getDB(KFCShoppingCar.this);
		setContentView(R.layout.shoppingcar_main);
		
		init();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Toast.makeText(KFCShoppingCar.this, "ÁÐ±íË¢ÐÂ", Toast.LENGTH_SHORT).show();
	}
	
	public void init(){
		
		orderlist = (ListView) findViewById(R.id.orderlist);
		tv_subtotal = (TextView) findViewById(R.id.tv_subtotal);
		tv_delivery = (TextView) findViewById(R.id.tv_delivery);
		tv_total = (TextView) findViewById(R.id.tv_total);
		
		ib_clear = (ImageButton) findViewById(R.id.ib_clear);
		
		UserFoodsDaoImpl ufd = new UserFoodsDaoImpl();
		List<HashMap<String, Object>> UserFoodsList = ufd.getUserFoodsOrder(KFCShoppingCar.this, AppConfig.userid);
		orderlistadapter = new OrderListAdapter(KFCShoppingCar.this, UserFoodsList, holder);
		orderlistadapter.notifyDataSetChanged();
		orderlist.setAdapter(orderlistadapter);
		
		float subtotal = 0.0f;
		float delivery = 0.0f;
		float total = 0.0f;
		
		for(HashMap<String, Object> k : UserFoodsList){
			subtotal += Float.valueOf(k.get("SumPrice").toString());
		}
		
		if( subtotal < 39.0f ){
			delivery = 8.0f;
		}
		
		total = subtotal + delivery;
		
		tv_subtotal.setText(""+subtotal);
		tv_delivery.setText(""+delivery);
		tv_total.setText(""+total);
		
		ib_clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				orderlistadapter.notifyDataSetChanged();
			}
		});
		
	}
}
