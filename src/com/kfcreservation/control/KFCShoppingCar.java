package com.kfcreservation.control;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.kfcreservation.R;
import com.kfcreservation.core.ActivityCore;
import com.kfcreservation.core.AppConfig;
import com.kfcreservation.core.ExitApplication;
import com.kfcreservation.core.MySQLiteHelper;

public class KFCShoppingCar extends Activity {
	
	ListView orderlist;
	
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
		
		orderlist = (ListView) findViewById(R.id.orderlist);
		
		orderlist.setAdapter(ac.getOrderListAdapter(KFCShoppingCar.this, AppConfig.userid, holder));	
	}
}
