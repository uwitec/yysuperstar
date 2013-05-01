package com.kfcreservation.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class MyShoppingCarListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<HashMap<String, Object>> mData;
	private Context context = null;
	
	public final class ViewHolderSC {
		public TextView tv_name;
		public EditText et_count;
		public TextView tv_price;
		public Button bt_cancel;
	}
	
	public MyShoppingCarListAdapter(Context context, List<HashMap<String,Object>> list){
		this.mInflater = LayoutInflater.from(context);
		this.mData = list;
		this.context = context;
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
		final Map dataSet = mData.get(position);
		ViewHolderSC holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.shoppingcar_list, parent, false);
			holder = new ViewHolderSC();
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.et_count = (EditText) convertView.findViewById(R.id.et_count);
			holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
			holder.bt_cancel = (Button) convertView.findViewById(R.id.bt_cancel);
			 
			convertView.setTag(holder);

		}else {
			holder = (ViewHolderSC)convertView.getTag();
		}
		
		holder.tv_name.setText((String)dataSet.get("Name"));
		holder.et_count.setText((String)dataSet.get("Count"));
		holder.tv_price.setText((String)dataSet.get("SumPrice"));
		
		holder.bt_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "È¡Ïû", Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;
	}
}
