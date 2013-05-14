package com.kfcreservation.provide;

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
import com.kfcreservation.biz.UserFoodsBiz;
import com.kfcreservation.core.AppData;
import com.kfcreservation.dao.UserFoodsDao;
import com.kfcreservation.dao.impl.UserFoodsDaoImpl;
import com.kfcreservation.entity.UserFoods;

public class MyShoppingCarListAdapter extends BaseAdapter {
	HashMap<Integer,View> lmap = new HashMap<Integer,View>();  
	private LayoutInflater mInflater;
	private List<HashMap<String, Object>> mData;
	private Context context = null;
	
	private UserFoods userfoods = new UserFoods();
	private UserFoodsBiz userfoodsbiz = new UserFoodsBiz();	
	
	public final class ViewHolderSC {
		public TextView tv_name;
		public EditText et_count;
		public TextView tv_price;
		public Button bt_cancel;
	}
	
	public MyShoppingCarListAdapter(){	}
	
	public MyShoppingCarListAdapter(Context context, List<HashMap<String,Object>> list){
		this.mInflater = LayoutInflater.from(context);
		this.mData = list;
		//this.mData = AppData.UserOrderList;
		this.context = context;
	}
	
	public void addData(List<HashMap<String,Object>> list) {			
		this.mData = list;
	}
	
	@Override
	public int getCount() {
		//return mData.size();
		return AppData.UserOrderList.size();
	}

	@Override
	public Object getItem(int position) {
		//return mData.get(position);
		return AppData.UserOrderList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int currentPosition = position;
		//final HashMap<String, Object> dataSet = mData.get(currentPosition);
		final HashMap<String, Object> dataSet = AppData.UserOrderList.get(position);
		ViewHolderSC holder = null;
		if( lmap.get(position) == null ){
			convertView = mInflater.inflate(R.layout.shoppingcar_listitem, parent, false);
			holder = new ViewHolderSC();
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.et_count = (EditText) convertView.findViewById(R.id.et_count);
			holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
			holder.bt_cancel = (Button) convertView.findViewById(R.id.bt_cancel);
			
			lmap.put(position, convertView);
			convertView.setTag(holder);
			
			holder.tv_name.setText((String)dataSet.get("Name"));
			holder.et_count.setText((String)dataSet.get("Count"));
			holder.tv_price.setText((String)dataSet.get("SumPrice"));
		} 
		else {  
			convertView = lmap.get(position);  
			holder = (ViewHolderSC)convertView.getTag();  
		}  
		holder.bt_cancel.setTag(dataSet);
		holder.bt_cancel.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				HashMap<String, Object> food = (HashMap<String, Object>) v.getTag();
				List<HashMap<String, Object>> FoodsCountlist = userfoodsbiz.getUserFoodsCountById( context, AppData.userid, AppData.serial, Integer.valueOf(food.get("Foodid").toString()) );
				if(FoodsCountlist.size() > 0){
					userfoods.setCount( Integer.valueOf(FoodsCountlist.get(0).get("Count").toString()));
				}else{
					Toast.makeText(context, "刪除失败", Toast.LENGTH_SHORT).show();
					return;
				}
				
				List<HashMap<String, Object>> Foodsufidlist = userfoodsbiz.getUserFoodsufidByFoodid(context, AppData.userid, AppData.serial, Integer.valueOf(food.get("Foodid").toString()));
				
				if(Foodsufidlist.size() > 0) {
					userfoods.set_ufid( Integer.valueOf(Foodsufidlist.get(0).get("ufid").toString()) );
				}
				else {
					Toast.makeText(context, "刪除失败", Toast.LENGTH_SHORT).show();
					return;
				}
				
				userfoods.setFoodid( Integer.valueOf(food.get("Foodid").toString()) );
				userfoods.setSerial(AppData.serial);
				userfoods.setStatus(1);
				userfoods.setUserid(AppData.userid) ;
				
				if(userfoodsbiz.cancelUserFoods(context, userfoods)){
					Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
				}
				
				updAppDataUserOrderList();
				
			}
		});
		System.out.println("MyShoppingCarListAdapter Name:"+(String)dataSet.get("Name") + " Count:"+(String)dataSet.get("Count") + " SumPrice"+(String)dataSet.get("SumPrice")+" position:"+position);
		return convertView;
	}
	
	//更新购物车
	private void updAppDataUserOrderList() {
		
		System.out.println("updAppDataUserOrderList");
		UserFoodsDao userfoodsdao = new UserFoodsDaoImpl();
		List<HashMap<String, Object>> UserFoodsList = userfoodsdao.getUserFoodsOrder(
				context, AppData.userid);
		AppData.setUserOrderList(UserFoodsList);
	}

//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		//final HashMap<String, Object> dataSet = mData.get(position);
//		final HashMap<String, Object> dataSet = AppData.UserOrderList.get(position);
//		ViewHolderSC holder = null;
//		if (convertView == null) {
//			convertView = mInflater.inflate(R.layout.shoppingcar_listitem, parent, false);
//			holder = new ViewHolderSC();
//			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
//			holder.et_count = (EditText) convertView.findViewById(R.id.et_count);
//			holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
//			holder.bt_cancel = (Button) convertView.findViewById(R.id.bt_cancel);
//			 
//			convertView.setTag(holder);
//
//		}else {
//			holder = (ViewHolderSC)convertView.getTag();
//		}
//		
//		holder.tv_name.setText((String)dataSet.get("Name"));
//		holder.et_count.setText((String)dataSet.get("Count"));
//		holder.tv_price.setText((String)dataSet.get("SumPrice"));
//		
//		holder.bt_cancel.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();
//			}
//		});
//
//		System.out.println("MyShoppingCarListAdapter Name:"+(String)dataSet.get("Name") + " Count:"+(String)dataSet.get("Count") + " SumPrice"+(String)dataSet.get("SumPrice")+" position:"+position);
//		return convertView;
//	}
}
