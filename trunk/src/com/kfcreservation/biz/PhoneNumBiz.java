package com.kfcreservation.biz;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Message;

import com.kfcreservation.dao.PhoneNumDao;
import com.kfcreservation.dao.impl.PhoneNumDaoImpl;
import com.kfcreservation.handler.PhoneNumHandler;

public class PhoneNumBiz extends PhoneNumDaoImpl implements PhoneNumDao {
	
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
		}else if(0!=super.addPhone(context, _uid, phone)){
			msg =PhoneNumHandler.hd.obtainMessage();
			msg.arg1=1;
			msg.obj=context;
			PhoneNumHandler.hd.sendMessage(msg);
			return true;
		}
		return false;
	}
	
	
	public List<HashMap<String,Object>> getAllNumber(Context context,int uid){
		return super.getAllNumber(context, uid);
	}
	
	public long addPhone(Context context,int _uid,String phonenum){
		return super.addPhone(context, _uid, phonenum);
	}
}


