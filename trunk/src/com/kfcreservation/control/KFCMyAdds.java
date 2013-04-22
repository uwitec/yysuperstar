package com.kfcreservation.control;

import com.kfcreservation.R;
import com.kfcreservation.core.ExitApplication;
import com.kfcreservation.core.MySQLiteHelper;

import android.app.Activity;
import android.os.Bundle;

public class KFCMyAdds extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);
		MySQLiteHelper.getDB(KFCMyAdds.this);
		setContentView(R.layout.myadds);
	}
}