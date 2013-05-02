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
				adds.getToast("无添加");
				break;
			case 2:
				adds.getToast("无添加");
				break;
			case 3:
				adds.getToast("号码长度不符");
				break;
			case 1:
				adds.getToast("添加成功");
				break;
			case 4:
				adds.getToast("号码已经存在");
				break;
			}
		}

	};

}
