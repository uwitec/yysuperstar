package com.kfcreservation.core;

import java.util.HashMap;
import java.util.List;

import com.kfcreservation.R;

import com.kfcreservation.handler.GetFoodHandler;
import com.kfcreservation.handler.KFCMenuHandler;


import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;

public class FoodTypeAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private List<HashMap<String, Object>> mData;
	private ViewHolderT ViewHoldert = null;
	private Context mContext = null;
	private ActivityCore ac =new ActivityCore();
	Message msg;

	
	public FoodTypeAdapter(List<HashMap<String, Object>> ls,Context mContext){
		this.mInflater = LayoutInflater.from(mContext);
		this.mData = ls;
		this.mContext = mContext;
	}
	
	
	public class ViewHolderT{
		public Button  tv_type;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.menu_foodtypeitem, null);
			ViewHoldert =new ViewHolderT();
			ViewHoldert.tv_type=(Button)convertView.findViewById(R.id.lists);
			convertView.setTag(ViewHoldert);
			
		}else{
			ViewHoldert=(ViewHolderT)convertView.getTag();
			
		}
		ViewHoldert.tv_type.setText((String)mData.get(position).get("Name"));
		ViewHoldert.tv_type.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String foodtype =(String)mData.get(position).get("Name");
				msg =GetFoodHandler.hd.obtainMessage();
				Message msg1=KFCMenuHandler.mHandler.obtainMessage();
				msg.arg1=position;
				msg1.arg1=position;
				System.out.println("uu"+position);
				msg.obj=mContext;
				GetFoodHandler.hd.sendMessage(msg);
				KFCMenuHandler.msg1Handler.sendMessage(msg1);
			}});
		
		return convertView;
	}

}
