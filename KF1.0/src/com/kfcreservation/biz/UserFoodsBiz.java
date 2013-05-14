package com.kfcreservation.biz;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Message;

import com.kfcreservation.dao.impl.UserFoodsDaoImpl;
import com.kfcreservation.entity.UserFoods;
import com.kfcreservation.handler.KFCMenuHandler;
import com.kfcreservation.handler.KFCShoppingCarHandler;


public class UserFoodsBiz extends UserFoodsDaoImpl {

	Message msg;

	public boolean AddUserFoods(Context context, UserFoods userfoods) {
		if( super.getUserFoodsByufid(context, userfoods.get_ufid()).size() > 0 ){
			System.out.println("���м�¼������count");
			super.updUserFoods(context, userfoods);
			msg = KFCMenuHandler.ReloadFoodMenuHandler.obtainMessage();
			msg.obj = context;
			msg.arg1 = 0;
			KFCMenuHandler.ReloadFoodMenuHandler.sendMessage(msg);
			return true;
			
		}else{
			System.out.println("������¼");
			super.setUserFoods(context, userfoods);
			msg = KFCMenuHandler.ReloadFoodMenuHandler.obtainMessage();
			msg.obj = context;
			msg.arg1 = 0;
			KFCMenuHandler.ReloadFoodMenuHandler.sendMessage(msg);
			return true;
		}
	}
	
	public List<HashMap<String, Object>> getUserFoodsCountById(Context context, int uid, long serial, int fid){
		return super.getUserFoodsCountById(context, uid, serial, fid);
	}
	
	public List<HashMap<String, Object>> getUserFoodsufidByFoodid(Context context, int uid, long serial, int fid){
		return super.getUserFoodsufidByFoodid(context, uid, serial, fid);
	}
	
	public List<HashMap<String, Object>> getUserFoodsMaxid(Context context){
		return super.getUserFoodsMaxid(context);
	}
	
	public boolean cancelUserFoods(Context context, UserFoods userfoods){
		if( super.getUserFoodsByufid(context, userfoods.get_ufid()).size() > 0 ){
			super.updUserFoods(context, userfoods);
			System.out.println("cancelUserFoods ɾ���ɹ�");
			msg = KFCShoppingCarHandler.RefreshShoppingCarHandler.obtainMessage();
			msg.obj = context;
			KFCShoppingCarHandler.RefreshShoppingCarHandler.sendMessage(msg);
			return true;
			
		}else{
			System.out.println("cancelUserFoods �޴����ݣ�ɾ��ʧ��");
			msg = KFCShoppingCarHandler.RefreshShoppingCarHandler.obtainMessage();
			msg.obj = context;
			KFCShoppingCarHandler.RefreshShoppingCarHandler.sendMessage(msg);
			return true;
		}
	}
}
