package com.kfcreservation.handler;

import com.kfcreservation.control.KFCMenu;

import android.os.Handler;
import android.os.Message;

public class KFCMenuHandler {

	public static Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			KFCMenu kfcmenu =(KFCMenu)msg.obj;
			switch(msg.arg1){
			case 0:
				kfcmenu.RefreshMenuList();
				break;
			}
		}
	};
}
