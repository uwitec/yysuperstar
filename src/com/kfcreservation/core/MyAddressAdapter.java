package com.kfcreservation.core;

import java.util.HashMap;
import java.util.List;

import com.kfcreservation.R;
import com.kfcreservation.control.KFCMyAdds.ViewHolderP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAddressAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<HashMap<String, Object>> mData;
	private ViewHolderP myViewHolder = null;
	private Context mContext = null;
	
	

	public MyAddressAdapter(List<HashMap<String, Object>> ls,
			ViewHolderP myViewHolder, Context mContext) {
		this.mInflater = LayoutInflater.from(mContext);
		this.mData = ls;
		this.myViewHolder = myViewHolder;
		this.mContext = mContext;
	}
	
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView==null){
			convertView=mInflater.inflate(R.layout.addaddress, null);
		}
		return null;
	}

}
