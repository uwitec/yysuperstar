package com.kfcreservation.biz;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Message;

import com.kfcreservation.dao.UserAddressDao;
import com.kfcreservation.dao.impl.UserAddressDaoImpl;
import com.kfcreservation.handler.UserAddressHandler;

public class UserAddressBiz extends UserAddressDaoImpl implements
		UserAddressDao {
	
		Message msg =UserAddressHandler.hd.obtainMessage();
	
	
		public boolean checkAdd(Context context,int uid,String address){
			msg.obj=context;
			if(address==null&&address.equals("")){
				msg.arg1=0;
				UserAddressHandler.hd.sendMessage(msg);
				return false;
			}else if(0!=super.addAddress(context, uid, address)){
				msg.arg1=1;
				UserAddressHandler.hd.sendMessage(msg);
				return true;
			}
			return false;
		}
	
	
	
	public List<HashMap<String,Object>> getAllAddress(Context context,int uid){
		return super.getAllAddress(context, uid);
	}
	
	public long addAddress(Context context,int uid,String address){
		return super.addAddress(context, uid, address);
	}

}
