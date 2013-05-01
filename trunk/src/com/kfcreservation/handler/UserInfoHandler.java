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
					kfclogin.showToast("�ֻ��Ų���Ϊ��");
					break;
				case 1:
					kfclogin.showToast("���벻��Ϊ��");
					break;
				case 2:
					kfclogin.showToast("�������");
					break;
				case 3:
					//kfclogin.showToast("��¼�ɹ�");
					kfclogin.startActivity(i);
					break;
				case 4:
					kfclogin.showToast("ע��ʧ��");
					break;
			}
		}
		
	};
}
