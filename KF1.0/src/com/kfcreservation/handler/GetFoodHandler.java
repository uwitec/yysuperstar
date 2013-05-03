package com.kfcreservation.handler;

import android.os.Handler;
import android.os.Message;

import com.kfcreservation.control.KFCMenu;
import com.kfcreservation.core.ActivityCore;

public class GetFoodHandler {
	
	static ActivityCore ac =new ActivityCore();
	static KFCMenu k;
	static int p;
	public static Handler hd =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			 k=(KFCMenu)msg.obj;
			 p =msg.arg1+1;
			 GetAdapte(p);
			
		}
		
	};
	
	public static void GetAdapte(int t){
		k.mLvAll.setAdapter(ac.getFoodListAdapter(k, t));
	}

}
