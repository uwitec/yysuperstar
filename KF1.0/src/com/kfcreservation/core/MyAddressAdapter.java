package com.kfcreservation.core;

import java.util.HashMap;
import java.util.List;

import com.kfcreservation.R;
import com.kfcreservation.biz.PhoneNumBiz;
import com.kfcreservation.biz.UserAddressBiz;
import com.kfcreservation.handler.PhoneNumHandler;
import com.kfcreservation.handler.UserAddressHandler;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyAddressAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<HashMap<String, Object>> mData;
	private ViewHolderA myViewHolder = null;
	private Context mContext = null;
	
	

	public MyAddressAdapter(List<HashMap<String, Object>> ls,Context mContext) {
		this.mInflater = LayoutInflater.from(mContext);
		this.mData = ls;
		this.mContext = mContext;
	}
	
	
	public  class ViewHolderA {
		public ImageButton ibtn_adet;
		public TextView tv_myAddress;
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

		if (convertView == null) {
			
			convertView = mInflater.inflate(R.layout.addaddress, null);
			myViewHolder=new ViewHolderA();
			myViewHolder.tv_myAddress = (TextView) convertView.findViewById(R.id.tv_showaddress);
			myViewHolder.ibtn_adet = (ImageButton) convertView.findViewById(R.id.ibtn_addet);
			convertView.setTag(myViewHolder);
		} else {
			
			myViewHolder = (ViewHolderA) convertView.getTag();
		}
//		if(position%2==0){
//			convertView.setBackgroundColor(Color.WHITE);
//		}else{
//			convertView.setBackgroundColor(Color.LTGRAY);
//		}
		myViewHolder.tv_myAddress.setText((String) mData.get(position).get("Address"));
		myViewHolder.ibtn_adet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String ad = (String) mData.get(position).get("Address");
				System.out.println(ad);
				System.out.println("选择到了取消按钮");
				AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
				builder.setTitle("提醒");
				builder.setMessage("是否要删除");
				builder.setPositiveButton("删除",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								UserAddressBiz uab =new UserAddressBiz();
								uab.detAddress(mContext, 1, ad);
								Message msg =UserAddressHandler.Ha.obtainMessage();
								msg.obj=mContext;
								msg.arg1=1;
								UserAddressHandler.Ha.sendMessage(msg);

							}
						});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				builder.create().show();
			}
		});
		return convertView;
	}

}
