package com.kfcreservation.provide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.kfcreservation.core.AppData;
import com.kfcreservation.dao.impl.UserFoodsDaoImpl;
import com.kfcreservation.entity.UserFoods;
@SuppressWarnings("unchecked")
public class MyMenuListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<? extends Map<String, ?>> mData;
	private Context context = null;
	
	private UserFoods userfoods = new UserFoods();

	private UserFoodsBiz userfoodsbiz = new UserFoodsBiz();
	
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
	
	public void addData(List<HashMap<String, Object>> list){
		this.mData = list;
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
		final Map<String, ?> dataSet = mData.get(position);
		ViewHolderM holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.menu_foodlistitem, parent, false);
			holder = new ViewHolderM();
			holder.im_img = (ImageView) convertView.findViewById(R.id.im_img);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
			holder.bt_add = (Button) convertView.findViewById(R.id.bt_add);
			holder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
			holder.bt_jian = (Button) convertView.findViewById(R.id.bt_jian);
			holder.bt_add.setTag(dataSet);
			holder.bt_jian.setTag(dataSet);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolderM) convertView.getTag();
		}

		holder.im_img.setImageBitmap((Bitmap) dataSet.get("Img"));
		holder.tv_name.setText((String) dataSet.get("Name"));
		holder.tv_price.setText((String) dataSet.get("Price"));
		holder.tv_num.setText((String) dataSet.get("Count"));

		holder.bt_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				HashMap<String, Object> food = (HashMap<String, Object>) v.getTag();
				List<HashMap<String, Object>> FoodsCountlist = userfoodsbiz.getUserFoodsCountById( context, AppData.userid, AppData.serial, Integer.valueOf(food.get("_id").toString()) );
				if(FoodsCountlist.size() > 0){
					userfoods.setCount( Integer.valueOf(FoodsCountlist.get(0).get("Count").toString()) + 1);
				}else{
					userfoods.setCount(1);
				}
				
				List<HashMap<String, Object>> Foodsufidlist = userfoodsbiz.getUserFoodsufidByFoodid(context, AppData.userid, AppData.serial, Integer.valueOf(food.get("_id").toString()));
				
				if(Foodsufidlist.size() > 0) {
					userfoods.set_ufid( Integer.valueOf(Foodsufidlist.get(0).get("ufid").toString()) );
				}
				else {
					List<HashMap<String, Object>> Maxidlist = userfoodsbiz.getUserFoodsMaxid(context);
					if(Maxidlist.get(0).get("ufid") != null ) {
						userfoods.set_ufid( Integer.valueOf(Maxidlist.get(0).get("ufid").toString()) + 1 );
					}
				}
				
				userfoods.setFoodid( Integer.valueOf(food.get("_id").toString()) );
				userfoods.setSerial(AppData.serial);
				userfoods.setStatus(0);
				userfoods.setUserid(AppData.userid) ;
				
				userfoodsbiz.AddUserFoods(context, userfoods);
				
				updAppDataUserOrderList();
				KFCShoppingCar kfcshoppingcar = (KFCShoppingCar) AppData.getActivityList("KFCShoppingCar");
				kfcshoppingcar.setShoppingCarlist();
				kfcshoppingcar.ReloadShoppingCarlist();
				kfcshoppingcar.setTotal();
				Toast.makeText(context, "已加入购物车", Toast.LENGTH_SHORT).show();
				
				System.out.println("已加入购物车");
			}
		});

		holder.bt_jian.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View v) {
				
			}
		});
		System.out.println("MyMenuListAdapter Name:"+(String) dataSet.get("Name") + " position:"+position);
		return convertView;
	}

	//更新购物车
	private void updAppDataUserOrderList() {
		
		System.out.println("updAppDataUserOrderList");
		UserFoodsDaoImpl ufd = new UserFoodsDaoImpl();
		List<HashMap<String, Object>> UserFoodsList = ufd.getUserFoodsOrder(
				context, AppData.userid);
		AppData.setUserOrderList(UserFoodsList);
	}
}
