package com.kfcreservation.control;


import java.text.DecimalFormat;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.kfcreservation.R;
import com.kfcreservation.Zxing.Demo.CaptureActivity;
import com.kfcreservation.core.AppData;
import com.kfcreservation.core.ExitApplication;
import com.kfcreservation.entity.Order;
import com.kfcreservation.provide.MyOrderAdapter;

public class KFCOrder extends Activity {
	static TextView mMoney;
	TextView mName;
	TextView mAddress;
	TextView mPhone;
	TextView mMsg;
	ListView mList;
	Button btn_2d,btn_ordersure;
	static String money;
	String name;
	String address;
	String phone;
	String msg;
	static double discount=0.0;
	Order o =new Order();
	//final ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);    

	//handler方法传送数据
	public static Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			money = msg.obj.toString();
		}
	};
	
	public static Handler disHandler =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			discount =Double.parseDouble(msg.obj.toString());
			System.out.println("disccount"+discount);
			getMoney();
		}
		
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kfcorder);
		ExitApplication.getInstance().addActivity(this);
		getView();
		mList.setAdapter(new MyOrderAdapter(KFCOrder.this,AppData.UserOrderList));
		btn_ordersure.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ExitApplication.getInstance().exit();
			}});
		btn_2d.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it =new Intent();
				it.putExtra("name", name);
				it.putExtra("phone", phone);
				it.putExtra("address", address);
				it.putExtra("msg", msg);
				it.setClass(KFCOrder.this,CaptureActivity.class );
				startActivity(it);
			}});
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("程序OnStart");
		//getValueFromObject();
		getValue();
		getMoney();
	}
	
	public void getView(){
		mMoney=(TextView) findViewById(R.id.tv_ordermoney);
		mName=(TextView) findViewById(R.id.tv_ordername);
		mAddress=(TextView) findViewById(R.id.tv_orderaddress);
		mPhone=(TextView) findViewById(R.id.tv_orderphone);
		mList=(ListView) findViewById(R.id.lv_order);
		mMsg=(TextView) findViewById(R.id.tv_msg);
		btn_2d=(Button)findViewById(R.id.btn_2d);
		btn_ordersure=(Button)findViewById(R.id.bu_ordersure);
	}
	
	public void getStringFromIntent(){
		 name = this.getIntent().getExtras().getString("name").toString();
		 phone = this.getIntent().getExtras().getString("phone").toString();
		 address = this.getIntent().getExtras().getString("address").toString();
		 msg =this.getIntent().getExtras().getString("msg").toString();
	}
	
	public void getValue(){
//		mMoney.setText(money);
		getStringFromIntent();
		// name = this.getIntent().getExtras().getString("name").toString();
		o.setName(name);
		mName.setText(name);
		// phone = this.getIntent().getExtras().getString("phone").toString();
		o.setPhone(phone);
		mPhone.setText(phone);
		// address = this.getIntent().getExtras().getString("address").toString();
		o.setAddress(address);
		mAddress.setText(address);
		// msg =this.getIntent().getExtras().getString("msg").toString();
		o.setMsg(msg);
		mMsg.setText(msg);
		//mList.setAdapter(new MyOrderAdapter(KFCOrder.this,AppData.UserOrderList));
		//System.out.println("====================="+o.toString());
	}
	
	public static void getMoney(){
		if(discount==0.0){
			System.out.println(discount+"ccccccccccccccccccccccccc");
		mMoney.setText(money);
		}else{
			System.out.println(discount+"这是新的Discount");
			double m =Double.parseDouble(money);
			double total =m*discount;
			DecimalFormat df = new DecimalFormat( "0.0 ");   
			String nweTotal=df.format(total);
			mMoney.setTextColor(Color.BLUE);
			mMoney.setText(nweTotal+"");
		}
	}
	
	public void getValueFromObject(){
		System.out.println("程序进入了~~~~~~~~~~~~~~~~~~");
	 mName.setText(o.getName().toString().trim());
	 mPhone.setText(o.getPhone().toString().trim());
	 mAddress.setText(o.getAddress().toString().trim());
	 mMsg.setText(o.getMsg().toString().trim());
	 
	}
	
}
