package com.kfcreservation.provide;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kfcreservation.R;
import com.kfcreservation.biz.PhoneNumBiz;
import com.kfcreservation.handler.PhoneNumHandler;

@SuppressLint("ResourceAsColor")
public class MyNumAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<HashMap<String, Object>> mData;
	ViewHolderP myViewHolder ;
	private Context mContext = null;

	public MyNumAdapter(List<HashMap<String, Object>> ls, Context mContext) {
		this.mInflater = LayoutInflater.from(mContext);
		this.mData = ls;
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
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public  class ViewHolderP {
		public ImageButton ibtn_det;
		public TextView tv_myphone;
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			
			convertView = mInflater.inflate(R.layout.addphone, null);
			myViewHolder=new ViewHolderP();
			myViewHolder.tv_myphone = (TextView) convertView.findViewById(R.id.tv_showPhone);
			myViewHolder.ibtn_det = (ImageButton) convertView.findViewById(R.id.ibtn_det);
			convertView.setTag(myViewHolder);
		} else {
			
			myViewHolder = (ViewHolderP) convertView.getTag();
		}
//		if(position%2==0){
//			convertView.setBackgroundColor(Color.WHITE);
//		}else{
//			convertView.setBackgroundColor(Color.LTGRAY);
//		}
		myViewHolder.tv_myphone.setText((String) mData.get(position).get("PhoneNum"));
		myViewHolder.ibtn_det.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String ph = (String) mData.get(position).get("PhoneNum");
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
								PhoneNumBiz pnb = new PhoneNumBiz();
								pnb.detPhone(mContext, 1, ph);
								Message msg = PhoneNumHandler.h.obtainMessage();
								msg.obj = mContext;
								msg.arg1 = 1;
								PhoneNumHandler.h.sendMessage(msg);

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
