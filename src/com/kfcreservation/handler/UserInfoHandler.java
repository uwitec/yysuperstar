package com.kfcreservation.handler;

import com.kfcreservation.control.KFCLogin;
import com.kfcreservation.control.KFCWaiter;
import com.kfcreservation.control.KFCWaiterNew;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class UserInfoHandler {

	public static Handler handler =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			KFCLogin kfclogin =(KFCLogin)msg.obj;
			Intent i = new Intent();
			i.setClass(kfclogin, KFCWaiterNew.class);
			switch(msg.arg1){
				case 0:
					kfclogin.showToast("手机号不能为空");
					break;
				case 1:
					kfclogin.showToast("密码不能为空");
					break;
				case 2:
					kfclogin.showToast("密码错误");
					break;
				case 3:
					//kfclogin.showToast("登录成功");
					kfclogin.startActivity(i);
					break;
				case 4:
					kfclogin.showToast("注册失败");
					break;
			}
		}
		
	};
}
