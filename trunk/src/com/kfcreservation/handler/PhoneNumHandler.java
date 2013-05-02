package com.kfcreservation.handler;

import android.os.Handler;
import android.os.Message;

import com.kfcreservation.control.KFCMyAdds;

public class PhoneNumHandler {

	public static Handler h = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			KFCMyAdds mad = (KFCMyAdds) msg.obj;
			if (msg.arg1 == 1) {
				mad.lv_phone.setAdapter(mad.getAd());
			}
		}

	};

	public static Handler hd = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			KFCMyAdds adds = (KFCMyAdds) msg.obj;
			switch (msg.arg1) {
			case 0:
				adds.getToast("�����");
				break;
			case 2:
				adds.getToast("�����");
				break;
			case 3:
				adds.getToast("���볤�Ȳ���");
				break;
			case 1:
				adds.getToast("��ӳɹ�");
				break;
			case 4:
				adds.getToast("�����Ѿ�����");
				break;
			}
		}

	};

}
