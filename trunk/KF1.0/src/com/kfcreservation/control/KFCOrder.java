package com.kfcreservation.control;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

import com.kfcreservation.R;
import com.kfcreservation.core.AppData;
import com.kfcreservation.provide.MyOrderAdapter;

public class KFCOrder extends Activity {
	TextView mMoney,mName,mAddress,mPhone,mMsg;
	ListView mList;
	static String money;
	
	//handler方法传送数据
	public static Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			money = msg.obj.toString();
		}
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kfcorder);
		getView();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		getValue();
	}
	
	public void getView(){
		mMoney=(TextView) findViewById(R.id.tv_ordermoney);
		mName=(TextView) findViewById(R.id.tv_ordername);
		mAddress=(TextView) findViewById(R.id.tv_orderaddress);
		mPhone=(TextView) findViewById(R.id.tv_orderphone);
		mList=(ListView) findViewById(R.id.lv_order);
		mMsg=(TextView) findViewById(R.id.tv_msg);
	}
	
	public void getValue(){
		mMoney.setText(money);
		String name = this.getIntent().getExtras().getString("name").toString();
		mName.setText(name);
		String phone = this.getIntent().getExtras().getString("phone").toString();
		mPhone.setText(phone);
		String address = this.getIntent().getExtras().getString("address").toString();
		mAddress.setText(address);
		String msg =this.getIntent().getExtras().getString("msg").toString();
		mMsg.setText(msg);
		mList.setAdapter(new MyOrderAdapter(KFCOrder.this,AppData.UserOrderList));
	}
	
}
