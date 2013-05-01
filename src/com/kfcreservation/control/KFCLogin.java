package com.kfcreservation.control;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kfcreservation.R;
import com.kfcreservation.biz.UserInfoBiz;
import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.entity.UserInfo;

public class KFCLogin extends Activity {

	EditText et_phonenum;
	EditText et_password;
	CheckBox cb_rm;
	ImageButton bt_next;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ExitApplication.getInstance().addActivity(this);
		MySQLiteHelper.getDB(KFCLogin.this);
		setContentView(R.layout.login_main);
		cb_rm = (CheckBox) findViewById(R.id.cb_rem);
		bt_next = (ImageButton) findViewById(R.id.bt_next);
		
		et_phonenum = (EditText) findViewById(R.id.et_phonenum);
		et_password = (EditText) findViewById(R.id.et_password);
		
		String PhoneNum = "15921203291";
		String Password = "56113214";
		
		et_phonenum.setText(PhoneNum);
		et_password.setText(Password);
		
		bt_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				UserInfo userinfo = new UserInfo();
				
				String PhoneNum = et_phonenum.getText().toString();
				String Password = et_password.getText().toString();
				
				userinfo.setPhoneNum(PhoneNum);
				userinfo.setPassword(Password);
				
				UserInfoBiz userinfobiz = new UserInfoBiz();
				userinfobiz.UserLogin(KFCLogin.this, userinfo);

				finish();
			}
		});
	}
	
	public void showToast(String str){
		Toast.makeText(KFCLogin.this, str, Toast.LENGTH_SHORT).show();
	}
}
