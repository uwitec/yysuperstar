package com.kfcreservation.control;

import java.util.HashMap;
import java.util.List;

import com.kfcreservation.R;
import com.kfcreservation.biz.PhoneNumBiz;
import com.kfcreservation.core.ExitApplication;
import com.kfcreservation.core.MySQLiteHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class KFCMyAdds extends Activity {

	Button btn_newPhone,btn_newAddress;
	ListView lv_phones,lv_addresses;
	PhoneNumBiz pub =new PhoneNumBiz();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);
		MySQLiteHelper.getDB(KFCMyAdds.this);
		setContentView(R.layout.myadds);
		lv_phones=(ListView)findViewById(R.id.lv_phone);
		lv_addresses=(ListView)findViewById(R.id.lv_addresses);
		btn_newPhone=(Button)findViewById(R.id.btn_newPhone);
		btn_newAddress=(Button)findViewById(R.id.btn_newAddress);
		lv_phones.setAdapter(getAdapter());
		btn_newPhone.setOnClickListener(new btnClick());
		btn_newAddress.setOnClickListener(new btnClick());
	}
	
	public SimpleAdapter getAdapter(){
		String []from ={"PhoneNum"};
		int [] to ={R.id.tv_showPhone};
		
		List<HashMap<String,Object>> lst =pub.getAllNumber(KFCMyAdds.this, 1);
		
		SimpleAdapter adapter =new SimpleAdapter(this,lst,R.layout.addphone,from,to);
		return adapter;
		
	}
	
	public void getToast(String msg){
		Toast.makeText(KFCMyAdds.this, msg, Toast.LENGTH_SHORT).show();
	}
	
	
	class btnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.btn_newPhone:
				getToast("添加联系方式");
				AlertDialog.Builder builder =new AlertDialog.Builder(KFCMyAdds.this);
				LayoutInflater inflater = LayoutInflater.from(KFCMyAdds.this);
				final View view =inflater.inflate(R.layout.dialogphone, null);
				final EditText phone =(EditText)view.findViewById(R.id.et_dphone);
				builder.setView(view);
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String p =phone.getText().toString().trim();
						pub.addPhone(KFCMyAdds.this, 1, p);
						lv_phones.setAdapter(getAdapter());
					}
				});
				builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						
					}
				});
				builder.create().show();

				break;
			case R.id.btn_newAddress:
				getToast("添加地址");
				//这个啊~明天做
				break;
			}
			
		}

	}
}
