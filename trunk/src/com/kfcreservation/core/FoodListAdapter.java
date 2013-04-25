package com.kfcreservation.core;

import java.util.HashMap;
import java.util.List;

import com.kfcreservation.R;
import com.kfcreservation.biz.UserFoodsBiz;
import com.kfcreservation.control.KFCMenu.ViewHolderM;
import com.kfcreservation.entity.UserFoods;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodListAdapter extends BaseAdapter{

	private LayoutInflater mInflater;
	private List<HashMap<String, Object>> mData;
	private ViewHolderM holder = null;
	private Context context = null;
	
	public FoodListAdapter(Context context, List<HashMap<String,Object>> ls, ViewHolderM holder){
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
			//holder = new ViewHolder();  
			convertView = mInflater.inflate(R.layout.menu_list, null);
			holder.im_img = (ImageView)convertView.findViewById(R.id.im_img);
			holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
			holder.tv_price = (TextView)convertView.findViewById(R.id.tv_price);
			holder.bu_add = (Button)convertView.findViewById(R.id.bt_add);
			holder.tv_num = (TextView)convertView.findViewById(R.id.tv_num);
			holder.bu_jian = (Button)convertView.findViewById(R.id.bt_jian);
			
			holder.bu_add.setTag((String)mData.get(position).get("id"));
			holder.bu_jian.setTag((String)mData.get(position).get("id"));
			 
			convertView.setTag(holder);

		}else {
			holder = (ViewHolderM)convertView.getTag();
		}
		
		holder.im_img.setImageBitmap((Bitmap) mData.get(position).get("imgs"));
		holder.tv_name.setText((String)mData.get(position).get("name"));
		holder.tv_price.setText((String)mData.get(position).get("price"));
		holder.tv_num.setText("0");
		
		holder.bu_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String title = (String)v.getTag();
				Toast.makeText(context, title + "+1", Toast.LENGTH_SHORT).show();
				holder.tv_num.setText( String.valueOf((Integer.valueOf(holder.tv_num.getText().toString()) + 1)) );
				UserFoodsBiz ufb = new UserFoodsBiz();
				UserFoods uf = new UserFoods();
				uf.setCount(Integer.valueOf(holder.tv_num.getText().toString()) );
				uf.setFoodid( Integer.valueOf(v.getTag().toString()) );
				uf.setStatus(0);
				uf.setUserid(AppConfig.userid);
				ufb.AddUserFoods(context, uf);
			}
		});
		
		holder.bu_jian.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String title = (String)v.getTag();
				Toast.makeText(context, "jian"+title, Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;
	}
}
