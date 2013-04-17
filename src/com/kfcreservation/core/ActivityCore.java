package com.kfcreservation.core;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.util.Log;
import android.view.View;

@SuppressWarnings("deprecation")
public class ActivityCore extends Activity {

	@SuppressWarnings("unused")
	public static View getView(LocalActivityManager mManager, String id, Intent intent) {
		Log.d("KFC", "getView() called! id = " + id);
		return mManager.startActivity(id, intent).getDecorView();
	}
}
