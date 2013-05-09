package com.kfcreservation.handler;

import android.os.Handler;
import android.os.Message;

import com.kfcreservation.control.KFCShoppingCar;

public class KFCShoppingCarHandler {

	public static Handler RefreshShoppingCarHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			KFCShoppingCar kfcshoppingcar =(KFCShoppingCar)msg.obj;
			kfcshoppingcar.setShoppingCarlist();
			kfcshoppingcar.setTotal();
		}
	};
}
