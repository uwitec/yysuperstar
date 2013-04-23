package com.kfcreservation.core;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kfcreservation.R;
import com.kfcreservation.control.KFCShoppingCar.ViewHolderSC;

public class OrderListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<HashMap<String, Object>> mData;
	private ViewHolderSC holder = null;
	private Context context = null;
	
	public OrderListAdapter(Context context, List<HashMap<String,Object>> ls, ViewHolderSC holder){
		this.mInflater = LayoutInflater.from(context);
		this.mData = ls;
		this.holder = holder;
		this.context = context;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.shoppingcar_list, null);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.et_count = (EditText) convertView.findViewById(R.id.et_count);
			holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
			holder.bt_cancel = (Button) convertView.findViewById(R.id.bt_cancel);
			 
			convertView.setTag(holder);

		}else {
			holder = (ViewHolderSC)convertView.getTag();
		}
		
		holder.tv_name.setText((String)mData.get(position).get("Name"));
		holder.et_count.setText((String)mData.get(position).get("Count"));
		holder.tv_price.setText((String)mData.get(position).get("Price"));
		
		holder.bt_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "È¡Ïû", Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;
	}
}
