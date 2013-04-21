package com.kfcreservation.handler;

import com.kfcreservation.control.KFCLogin;
import com.kfcreservation.control.KFCWaiter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class UserInfoHandler {

	public static Handler handler =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			KFCLogin kfclogin =(KFCLogin)msg.obj;
			switch(msg.arg1){
				case 0:
					kfclogin.showToast("手机号不能为空");
					break;
				case 1:
					kfclogin.showToast("密码不能为空");
					break;
				case 2:
					Intent ia = new Intent();
					ia.setClass(kfclogin, KFCWaiter.class);
					kfclogin.startActivity(ia);
					break;
				case 3:
					Intent ib = new Intent();
					ib.setClass(kfclogin, KFCWaiter.class);
					kfclogin.startActivity(ib);
					break;
			}
		}
		
	};
}
