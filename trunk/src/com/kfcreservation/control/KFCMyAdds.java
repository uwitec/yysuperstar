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
import com.kfcreservation.core.ActivityCore;
import com.kfcreservation.core.MySQLiteHelper;

public class KFCMyAdds extends Activity {
	// 声明控件
	private TextView tv_selectphone, tv_selectaddress, mBold,tv_receiver;
	private EditText et_receiver;
	private Button btn_newPhone, btn_newAddress, mOrder;
	public ListView lv_phone, lv_addresses;
	PhoneNumBiz pub = new PhoneNumBiz();
	UserAddressBiz uab = new UserAddressBiz();
	ActivityCore ac = new ActivityCore();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ExitApplication.getInstance().addActivity(this);
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
		lv_phone.setAdapter(ac.getMyNumAdapter(KFCMyAdds.this, 1));
		lv_addresses.setAdapter(ac.getMyAddressAdapter(KFCMyAdds.this, 1));
		btn_newPhone.setOnClickListener(new btnClick());
		btn_newAddress.setOnClickListener(new btnClick());
		mOrder.setOnClickListener(new btnClick());
		getValues();
	}

	public BaseAdapter getAd() {
		BaseAdapter adapter = ac.getMyNumAdapter(KFCMyAdds.this, 1);
		return adapter;
	}
	public BaseAdapter getDressAdapter(){
		BaseAdapter adapter =ac.getMyAddressAdapter(KFCMyAdds.this, 1);
		return adapter;
	}

	// 两个ListView被选中时，分别显示相应的值
	public void getValues() {
		lv_phone.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				List<HashMap<String, Object>> lst = pub.getAllNumber(
						KFCMyAdds.this, 1);
				HashMap<String, Object> mp = lst.get(position);
				String phone = (String) mp.get("PhoneNum");
				System.out.println(phone);
				tv_selectphone.setText(phone);

			}
		});
		lv_addresses.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				List<HashMap<String,Object>> li =uab.getAllAddress(KFCMyAdds.this,1);
				HashMap<String, Object> mp = li.get(position);
				String myaddress = (String) mp.get("Address");
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
		tv_receiver =(TextView)findViewById(R.id.tv_receiver);
		et_receiver =(EditText)findViewById(R.id.et_receiver);
		tv_selectphone = (TextView) findViewById(R.id.tv_selectphone);
		tv_selectaddress = (TextView) findViewById(R.id.tv_selectadress);
		lv_phone = (ListView) findViewById(R.id.lv_phone);
		lv_addresses = (ListView) findViewById(R.id.lv_addresses);
		btn_newPhone = (Button) findViewById(R.id.btn_newPhone);
		btn_newAddress = (Button) findViewById(R.id.btn_newAddress);
		mOrder=(Button) findViewById(R.id.bu_toorder);
		mBold=(TextView) findViewById(R.id.tv_selectadress);
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
								pub.checkAdd(KFCMyAdds.this, 1, p);
								lv_phone.setAdapter(ac.getMyNumAdapter(
										KFCMyAdds.this, 1));
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
								uab.checkAdd(KFCMyAdds.this, 1, mAdd);
								lv_addresses.setAdapter(ac.getMyAddressAdapter(KFCMyAdds.this, 1));
							
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
				startActivity(i);
			}

		}

	}
}
