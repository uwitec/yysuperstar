package com.kfcreservation.handler;

import android.os.Handler;
import android.os.Message;

import com.kfcreservation.control.KFCMenu;

public class UserFoodsHandler {

	public static Handler hd = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			KFCMenu kfcmenu = (KFCMenu) msg.obj;
			switch (msg.arg1) {
			case 0:
				kfcmenu.RefreshFoodsList();
				break;
			}
		}

	};
}
