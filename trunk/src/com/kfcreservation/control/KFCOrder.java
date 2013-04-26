package com.kfcreservation.control;

import com.kfcreservation.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class KFCOrder extends Activity {
	static TextView mMoney;
	String money="0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kfcorder);
		mMoney=(TextView) findViewById(R.id.tv_ordermoney);
		mMoney.setText(money);

		
	}
}
