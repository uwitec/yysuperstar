package com.kfcreservation.provide;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.kfcreservation.R;
import com.kfcreservation.handler.KFCMenuHandler;

public class MyFoodTypeAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private List<HashMap<String, Object>> mData;
	private ViewHolderT ViewHoldert = null;
	private Context mContext = null;
	
	public class ViewHolderT{
		public Button  tv_type;
	}
	
	public MyFoodTypeAdapter(Context mContext, List<HashMap<String, Object>> list){
		this.mInflater = LayoutInflater.from(mContext);
		this.mData = list;
		this.mContext = mContext;
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
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
				Message msg1;
				msg1 = KFCMenuHandler.setPositionHandler.obtainMessage();
				msg1.arg1 = position;
				KFCMenuHandler.setPositionHandler.sendMessage(msg1);
				
				Message msg2;
				msg2 = KFCMenuHandler.RefreshFoodMenuHandler.obtainMessage();
				msg2.obj=mContext;
				KFCMenuHandler.RefreshFoodMenuHandler.sendMessage(msg2);
				
				Message msg3;
				msg3 = KFCMenuHandler.RefreshFoodTypeListHandler.obtainMessage();
				msg3.obj=mContext;
				KFCMenuHandler.RefreshFoodTypeListHandler.sendMessage(msg3);
			}});
		
		return convertView;
	}
}
