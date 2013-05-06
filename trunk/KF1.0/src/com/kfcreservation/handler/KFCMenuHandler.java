package com.kfcreservation.handler;

import com.kfcreservation.control.KFCMenu;

import android.os.Handler;
import android.os.Message;

public class KFCMenuHandler {
	
	
	public static int position;
	
	public static Handler msg1Handler =new Handler(){

		@Override
		public void handleMessage(Message msg1) {
			// TODO Auto-generated method stub
			super.handleMessage(msg1);
			position =msg1.arg1;
			System.out.println("HH =="+position);
		}
		
	};
	
	public static Handler weiboHandler =new Handler(){

		@Override
		public void handleMessage(Message msg1) {
			// TODO Auto-generated method stub
			super.handleMessage(msg1);
			position =msg1.arg1;
			System.out.println("HH =="+position);
		}
		
	};

	public static Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			KFCMenu kfcmenu =(KFCMenu)msg.obj;
			
			switch(msg.arg1){
			case 0:
			
				kfcmenu.RefreshMenuList(position+1);
				break;
			}
		}
	};
}
