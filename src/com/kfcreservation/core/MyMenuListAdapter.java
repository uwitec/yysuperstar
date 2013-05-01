package com.kfcreservation.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
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

import com.kfcreservation.R;
import com.kfcreservation.biz.UserFoodsBiz;
import com.kfcreservation.control.KFCShoppingCar;
import com.kfcreservation.dao.impl.UserFoodsDaoImpl;
import com.kfcreservation.entity.UserFoods;

public class MyMenuListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<? extends Map<String, ?>> mData;
	private Context context = null;

	public final class ViewHolderM {
		public ImageView im_img;
		public TextView tv_name;
		public TextView tv_price;
		public Button bt_add;
		public TextView tv_num;
		public Button bt_jian;
		
	}
	
	public MyMenuListAdapter(Context context,
			List<HashMap<String, Object>> list) {
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
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Map dataSet = mData.get(position);
		ViewHolderM holder;
		if (convertView == null) {
			// holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.menu_foodlistitem, parent, false);
			holder = new ViewHolderM();
			holder.im_img = (ImageView) convertView.findViewById(R.id.im_img);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_price = (TextView) convertView
					.findViewById(R.id.tv_price);
			holder.bt_add = (Button) convertView.findViewById(R.id.bt_add);
			holder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
			holder.bt_jian = (Button) convertView.findViewById(R.id.bt_jian);

			holder.bt_add.setTag(dataSet);
			holder.bt_jian.setTag(dataSet);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolderM) convertView.getTag();
		}

		holder.im_img.setImageBitmap((Bitmap) dataSet.get("imgs"));
		holder.tv_name.setText((String) dataSet.get("name"));
		holder.tv_price.setText((String) dataSet.get("price"));
		holder.tv_num.setText((String) dataSet.get("count"));

		holder.bt_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				HashMap<String, Object> food = (HashMap<String, Object>) v.getTag();

				UserFoodsBiz ufb = new UserFoodsBiz();
				UserFoods uf = new UserFoods();
				uf.setCount(Integer.valueOf(food.get("count").toString()) + 1);
				uf.setFoodid(Integer.valueOf(food.get("id").toString()));
				uf.setSerial(AppData.serial);
				uf.setStatus(0);
				uf.setUserid(AppData.userid);
				ufb.AddUserFoods(context, uf);
				updAppDataUserOrderList();
				KFCShoppingCar kfcshoppingcar = (KFCShoppingCar) AppData.getActivityList("KFCShoppingCar");
				kfcshoppingcar.RefreshShoppingCarlist();
				Toast.makeText(context, "已加入购物车", Toast.LENGTH_SHORT).show();
			}
		});

		holder.bt_jian.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				HashMap<String, Object> food = (HashMap<String, Object>) v.getTag();
				if (Integer.valueOf(food.get("count").toString()) == 0) {
					return;
				}
				UserFoodsBiz ufb = new UserFoodsBiz();
				UserFoods uf = new UserFoods();
				uf.setCount(Integer.valueOf(food.get("count").toString()) - 1);
				uf.setFoodid(Integer.valueOf(food.get("id").toString()));
				uf.setSerial(AppData.serial);
				uf.setStatus(0);
				uf.setUserid(AppData.userid);
				ufb.AddUserFoods(context, uf);
				updAppDataUserOrderList();
				KFCShoppingCar kfcshoppingcar = (KFCShoppingCar) AppData.getActivityList("KFCShoppingCar");
				kfcshoppingcar.RefreshShoppingCarlist();
				Toast.makeText(context, "已更新购物车", Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;
	}

	private void updAppDataUserOrderList() {
		UserFoodsDaoImpl ufd = new UserFoodsDaoImpl();
		List<HashMap<String, Object>> UserFoodsList = ufd.getUserFoodsOrder(
				context, AppData.userid);
		//AppData.UserOrderList = UserFoodsList;
		AppData.setUserOrderList(UserFoodsList);
	}
}
