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
				adds.getToast("�����");
				System.out.println("+++++++++�����");
				break;
			case 1:
				adds.getToast("��ӳɹ�");
				System.out.println("--------------��ӳɹ�");
				break;
			case 2:
				adds.getToast("��ѡ����������");
				break;
			case 3:
				adds.getToast("����д��ȷ��·��");
				break;
			case 4:
				adds.getToast("��ַ����ȷ");
				break;
			}
		}
		
	};

}
