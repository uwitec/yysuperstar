package com.kfcreservation.control;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.kfcreservation.R;
import com.kfcreservation.core.ExitApplication;
import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.dao.impl.FoodTypeDaoImpl;

public class KFCLogin extends Activity {

	EditText et_phonenum;
	CheckBox cb_rm;
	ImageButton bt_next;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);
		MySQLiteHelper.getDB(KFCLogin.this);
		setContentView(R.layout.loginpage);
		
		cb_rm = (CheckBox) findViewById(R.id.cb_rm);
		bt_next = (ImageButton) findViewById(R.id.bt_next);
		
		bt_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FoodTypeDaoImpl ftd = new FoodTypeDaoImpl();
				ftd.getFoodTypeAll(KFCLogin.this);
				//Intent i = new Intent(KFCLogin.this,KFCWaiter.class);
				//startActivity(i);
			}
		});
	}
}
