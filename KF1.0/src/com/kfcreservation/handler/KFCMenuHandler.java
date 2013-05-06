package com.kfcreservation.handler;

import android.os.Handler;
import android.os.Message;

import com.kfcreservation.control.KFCMenu;

public class KFCMenuHandler {
	
	public static KFCMenu kfcmenu;
	public static int position;
	
	public static Handler setPositionHandler = new Handler(){

		@Override
		public void handleMessage(Message msg1) {
			super.handleMessage(msg1);
			position = msg1.arg1 + 1;
		}
	};

	public static Handler RefreshFoodTypeListHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			kfcmenu =(KFCMenu)msg.obj;
			
			switch(msg.arg1){
			case 0:
				kfcmenu.RefreshFoodTypeList();
				break;
			}
		}
	};
	
	public static Handler RefreshFoodMenuHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			kfcmenu = (KFCMenu)msg.obj;
			kfcmenu.RefreshFoodMenuList(position);
		}
	};
}
