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
	
		Message msg ;
	
	
		@SuppressWarnings("null")
		public boolean checkAdd(Context context,int uid,String address){
			
			if(address==null&&address.equals("")){
				msg =UserAddressHandler.hd.obtainMessage();
				msg.arg1=0;
				msg.obj=context;
				UserAddressHandler.hd.sendMessage(msg);
				return false;
			}else if(-1==address.indexOf('区')){
				msg =UserAddressHandler.hd.obtainMessage();
				msg.arg1=2;//未选中区
				msg.obj=context;
				UserAddressHandler.hd.sendMessage(msg);
				return false;
			}else if(-1==address.indexOf('路')){
				msg =UserAddressHandler.hd.obtainMessage();
				msg.arg1=3;//未填写路
				msg.obj=context;
				UserAddressHandler.hd.sendMessage(msg);
				return false;
			}else if(address.length()<6){
				msg =UserAddressHandler.hd.obtainMessage();
				msg.arg1=4;//未填写路
				msg.obj=context;
				UserAddressHandler.hd.sendMessage(msg);
				return false;
			}else if(0!=super.addAddress(context, uid, address)){
				msg =UserAddressHandler.hd.obtainMessage();
				msg.arg1=1;
				msg.obj=context;
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
