package com.kfcreservation.control;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kfcreservation.R;
import com.kfcreservation.biz.PhoneNumBiz;
import com.kfcreservation.biz.UserAddressBiz;
import com.kfcreservation.core.AppData;
import com.kfcreservation.core.ExitApplication;
import com.kfcreservation.core.MySQLiteHelper;

public class KFCMyAdds extends Activity {
	// 声明控件
	private TextView tv_selectphone, tv_selectaddress, mBold;
	private EditText et_receiver,mMsg;
	private Button btn_newPhone, btn_newAddress, mOrder;
	public ListView lv_phone, lv_addresses;
	UserAddressBiz useraddressbiz = new UserAddressBiz();
	PhoneNumBiz phonenumbiz = new PhoneNumBiz();
	public String myphone;
	public String myaddress;
	public String myname;
	public String mymsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);
		MySQLiteHelper.getDB(KFCMyAdds.this);
		setContentView(R.layout.myadds_main);
		getView();
		//中文字体加粗
		TextPaint paint = mBold.getPaint();  
		paint.setFakeBoldText(true);  
	}



	@Override
	protected void onStart() {
		super.onStart();
		lv_phone.setAdapter( phonenumbiz.getMyNumAdapter(KFCMyAdds.this, AppData.userid) );
		lv_addresses.setAdapter(useraddressbiz.getMyAddressAdapter(KFCMyAdds.this, AppData.userid));
		btn_newPhone.setOnClickListener(new btnClick());
		btn_newAddress.setOnClickListener(new btnClick());
		mOrder.setOnClickListener(new btnClick());
		getValues();
	}

	public BaseAdapter getAd() {
		BaseAdapter adapter = phonenumbiz.getMyNumAdapter(KFCMyAdds.this, AppData.userid);
		return adapter;
	}
	public BaseAdapter getDressAdapter(){
		BaseAdapter adapter =useraddressbiz.getMyAddressAdapter(KFCMyAdds.this, AppData.userid);
		return adapter;
	}

	// 两个ListView被选中时，分别显示相应的值
	public void getValues() {
		lv_phone.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				List<HashMap<String, Object>> lst = phonenumbiz.getAllNumber(
						KFCMyAdds.this, AppData.userid);
				HashMap<String, Object> mp = lst.get(position);
				myphone = (String) mp.get("PhoneNum");
				System.out.println(myphone);
				tv_selectphone.setText(myphone);

			}
		});
		lv_addresses.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				List<HashMap<String,Object>> li =useraddressbiz.getAllAddress(KFCMyAdds.this,AppData.userid);
				HashMap<String, Object> mp = li.get(position);
				myaddress = (String) mp.get("Address");
				System.out.println(myaddress);
				tv_selectaddress.setText(myaddress);
				
			}
		});
	}

	// 地址list中的adapter


	// 土司方法
	public void getToast(String msg) {
		Toast.makeText(KFCMyAdds.this, msg, Toast.LENGTH_SHORT).show();
	}

	public void getView() {
		et_receiver =(EditText)findViewById(R.id.et_receiver);
		tv_selectphone = (TextView) findViewById(R.id.tv_selectphone);
		tv_selectaddress = (TextView) findViewById(R.id.tv_selectadress);
		lv_phone = (ListView) findViewById(R.id.lv_phone);
		lv_addresses = (ListView) findViewById(R.id.lv_addresses);
		btn_newPhone = (Button) findViewById(R.id.btn_newPhone);
		btn_newAddress = (Button) findViewById(R.id.btn_newAddress);
		mOrder=(Button) findViewById(R.id.bu_toorder);
		mBold=(TextView) findViewById(R.id.tv_selectadress);
		mMsg=(EditText) findViewById(R.id.et_msg);
	}

	// 两个添加按钮的监听
	class btnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_newPhone:
				getToast("添加联系方式");
				AlertDialog.Builder builder = new AlertDialog.Builder(
						KFCMyAdds.this);
				LayoutInflater inflater = LayoutInflater.from(KFCMyAdds.this);
				final View view = inflater.inflate(
						R.layout.myadds_add_dialogphone, null);
				final EditText phone = (EditText) view
						.findViewById(R.id.et_dphone);
				builder.setView(view);
				builder.setTitle("添加联系电话").setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								String p = phone.getText().toString().trim();
								phonenumbiz.checkAdd(KFCMyAdds.this, AppData.userid, p);
								lv_phone.setAdapter(phonenumbiz.getMyNumAdapter(KFCMyAdds.this, AppData.userid));
							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

							}
						});
				builder.create().show();

				break;
			case R.id.btn_newAddress:
				getToast("添加地址");
				AlertDialog.Builder builder2 = new AlertDialog.Builder(
						KFCMyAdds.this);
				LayoutInflater inflater2 = LayoutInflater.from(KFCMyAdds.this);
				final View view2 = inflater2.inflate(
						R.layout.myadds_add_address, null);
				final Spinner spzong = (Spinner) view2
						.findViewById(R.id.sp_zone);
				final EditText newaddress = (EditText) view2
						.findViewById(R.id.et_daddress);
				builder2.setView(view2);
				builder2.setTitle("添加地址").setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								String zong = spzong.getSelectedItem()
										.toString().trim();
								String road = newaddress.getText().toString()
										.trim();
								String mAdd = zong+road;
								System.out.println(zong + road);
								useraddressbiz.checkAdd(KFCMyAdds.this, AppData.userid, mAdd);
								lv_addresses.setAdapter(useraddressbiz.getMyAddressAdapter(KFCMyAdds.this, AppData.userid));
							
							}
						});
				builder2.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

							}
						});
				builder2.create().show();

				break;
			case R.id.bu_toorder:
				Intent i = new Intent(KFCMyAdds.this,KFCOrder.class);
				myname=et_receiver.getText().toString().trim();
				mymsg=mMsg.getText().toString().trim();
				i.putExtra("name", myname);
				i.putExtra("phone", myphone);
				i.putExtra("address", myaddress);
				i.putExtra("msg", mymsg);
				if(myname.equals("")){
					getToast("请输入收货联系人");
				}else if(myphone==null){
					getToast("请输入手机号码");
				}else if(myaddress==null){
					getToast("请输入送货地址");
				}else{
					startActivity(i);
				}
				
			}

		}

	}
}
