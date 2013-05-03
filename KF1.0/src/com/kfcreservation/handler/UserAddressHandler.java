package com.kfcreservation.handler;

import com.kfcreservation.control.KFCMyAdds;

import android.os.Handler;
import android.os.Message;

public class UserAddressHandler {
	
	
	
	
	
	public static Handler Ha =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			KFCMyAdds mad = (KFCMyAdds) msg.obj;
			if (msg.arg1 == 1) {
				mad.lv_addresses.setAdapter(mad.getDressAdapter());
			}
		}
		
	};
	
	
	public static Handler hd =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			KFCMyAdds adds =(KFCMyAdds)msg.obj;
			switch(msg.arg1){
			case 0:
				adds.getToast("无添加");
				System.out.println("+++++++++无添加");
				break;
			case 1:
				adds.getToast("添加成功");
				System.out.println("--------------添加成功");
				break;
			case 2:
				adds.getToast("请选择您的区域");
				break;
			case 3:
				adds.getToast("请填写正确的路名");
				break;
			case 4:
				adds.getToast("地址不正确");
				break;
			}
		}
		
	};

}
