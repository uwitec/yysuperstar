package com.kfcreservation.handler;

import com.kfcreservation.control.KFCMyAdds;

import android.os.Handler;
import android.os.Message;

public class UserAddressHandler {
	
	public static Handler hd =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			KFCMyAdds adds =(KFCMyAdds)msg.obj;
			switch(msg.arg1){
			case 0:
				adds.getToast("�����");
				System.out.println("+++++++++�����");
				break;
			case 1:
				adds.getToast("��ӳɹ�");
				System.out.println("--------------��ӳɹ�");
				break;
			}
		}
		
	};

}
