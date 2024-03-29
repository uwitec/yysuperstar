package com.kfcreservation.handler;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.kfcreservation.control.KFCLogin;
import com.kfcreservation.control.KFCWaiter;

public class KFCLoginHandler {
	
	public static Handler LoginHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			KFCLogin kfclogin = (KFCLogin) msg.obj;
			
			switch (msg.arg1) {
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
				kfclogin.showToast("登录成功");
				Intent i = new Intent();
				i.setClass(kfclogin, KFCWaiter.class);
				kfclogin.startActivity(i);
				break;
			case 4:
				kfclogin.showToast("注册失败");
				break;
			}
		}
	};
	
	public static Handler WeiboHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			KFCLogin kfclogin = (KFCLogin) msg.obj;
			
			switch (msg.arg1) {
			case 0:
				kfclogin.showToast("分享成功");
				break;
			case 1:
				kfclogin.showToast("WeiboException");
				break;
			case 2:
				kfclogin.showToast("IOException");
				break;
			}
		}
	};
}
