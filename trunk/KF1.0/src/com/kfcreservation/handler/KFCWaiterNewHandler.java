package com.kfcreservation.handler;

import android.os.Handler;
import android.os.Message;

import com.kfcreservation.control.KFCWaiterNew;

public class KFCWaiterNewHandler {

	public static Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			KFCWaiterNew kfcwaiternew = (KFCWaiterNew) msg.obj;
			kfcwaiternew.isExit = false;
		}
	};
}
