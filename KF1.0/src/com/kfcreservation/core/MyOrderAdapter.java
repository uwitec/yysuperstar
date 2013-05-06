package com.kfcreservation.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kfcreservation.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyOrderAdapter extends BaseAdapter{
	
	private LayoutInflater mInflater;
	private List<HashMap<String, Object>> mData;
	private Context context = null;
	
	public class ViewHolder{
		public TextView mPrice;
		public TextView mName;
		public TextView mCount;
	}
	
	public MyOrderAdapter (Context context,List<HashMap<String,Object>>list){
		this.mInflater=LayoutInflater.from(context);
		this.mData=list;
		this.context=context;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Map map = mData.get(position);
		ViewHolder h;
		if(convertView==null){
			convertView = mInflater.inflate(R.layout.kfcorder_list,parent,false);
			h = new ViewHolder();
			h.mCount=(TextView) convertView.findViewById(R.id.order_count);
			h.mName=(TextView) convertView.findViewById(R.id.order_name);
			h.mPrice=(TextView) convertView.findViewById(R.id.order_price);
			convertView.setTag(h);
		}else{
			h=(ViewHolder) convertView.getTag();
		}
		h.mName.setText((String)map.get("Name"));
		h.mCount.setText((String)map.get("Count"));
		h.mPrice.setText((String)map.get("SumPrice"));
		
		return convertView;
	}

}
