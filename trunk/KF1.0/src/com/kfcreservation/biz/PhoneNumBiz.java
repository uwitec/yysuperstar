package com.kfcreservation.biz;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Message;

import com.kfcreservation.dao.impl.PhoneNumDaoImpl;
import com.kfcreservation.handler.PhoneNumHandler;
import com.kfcreservation.provide.MyNumAdapter;

public class PhoneNumBiz extends PhoneNumDaoImpl{
	
	Message msg ;
	
	@SuppressWarnings("null")
	public boolean checkAdd(Context context,int _uid,String phone){
		
		if(phone==null&& phone.length()<=0){
			msg =PhoneNumHandler.hd.obtainMessage();
			msg.obj=context;
			msg.arg1=0;
			PhoneNumHandler.hd.sendMessage(msg);
			return false;
		}else if(phone.length()!=11&&phone.length()!=8){
			msg =PhoneNumHandler.hd.obtainMessage();
			msg.arg1=3;
			msg.obj=context;
			PhoneNumHandler.hd.sendMessage(msg);
			return false;
		}else if(getByNum(context, _uid, phone).size()==1){
			msg =PhoneNumHandler.hd.obtainMessage();
			msg.arg1=4;
			msg.obj=context;
			PhoneNumHandler.hd.sendMessage(msg);
			return false;
		}else if(0!=super.addPhone(context, _uid, phone)){
			msg =PhoneNumHandler.hd.obtainMessage();
			msg.arg1=1;
			msg.obj=context;
			PhoneNumHandler.hd.sendMessage(msg);
			return true;
		}
		return false;
	}
	
	public MyNumAdapter getMyNumAdapter(Context context, int uid){
		List<HashMap<String, Object>> allnumberlist = super.getAllNumber(context, uid);
		return new MyNumAdapter(allnumberlist, context);
	}
}


