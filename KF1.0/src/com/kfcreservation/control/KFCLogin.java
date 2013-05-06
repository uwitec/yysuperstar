package com.kfcreservation.control;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kfcreservation.R;
import com.kfcreservation.biz.UserInfoBiz;
import com.kfcreservation.core.AccessTokenKeeper;
import com.kfcreservation.core.MySQLiteHelper;
import com.kfcreservation.entity.UserInfo;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.api.StatusesAPI;
import com.weibo.sdk.android.net.RequestListener;

public class KFCLogin extends Activity {

	private EditText et_phonenum;
	private EditText et_password;
	private CheckBox cb_rm;
	private ImageButton ib_next;
	private ImageButton ib_weibo;

	private Weibo mWeibo;
	private static final String CONSUMER_KEY = "3469188190";
	private static final String REDIRECT_URL = "http://www.sina.com";
	public static Oauth2AccessToken accessToken;
	public static final String TAG = "sinasdk";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ExitApplication.getInstance().addActivity(this);
		MySQLiteHelper.getDB(KFCLogin.this);
		setContentView(R.layout.login_main);
		mWeibo = Weibo.getInstance(CONSUMER_KEY, REDIRECT_URL);
		cb_rm = (CheckBox) findViewById(R.id.cb_rem);
		ib_next = (ImageButton) findViewById(R.id.ib_next);
		ib_weibo = (ImageButton) findViewById(R.id.ib_weibo);

		et_phonenum = (EditText) findViewById(R.id.et_phonenum);
		et_password = (EditText) findViewById(R.id.et_password);

		String PhoneNum = "15921203291";
		String Password = "56113214";

		et_phonenum.setText(PhoneNum);
		et_password.setText(Password);

		ib_weibo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mWeibo.authorize(KFCLogin.this, new AuthDialogListener());
			}
		});

		ib_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				UserInfo userinfo = new UserInfo();

				String PhoneNum = et_phonenum.getText().toString();
				String Password = et_password.getText().toString();

				userinfo.setPhoneNum(PhoneNum);
				userinfo.setPassword(Password);

				UserInfoBiz userinfobiz = new UserInfoBiz();
				userinfobiz.UserLogin(KFCLogin.this, userinfo);

				// finish();
			}
		});
	}
	
	class myRequestListener implements RequestListener{

		@Override
		public void onComplete(String arg0) {
			//Toast.makeText(getApplicationContext(),"分享成功", Toast.LENGTH_SHORT).show();
			showToast("分享成功");
		}

		@Override
		public void onError(WeiboException arg0) {
			//Toast.makeText(getApplicationContext(),"WeiboException", Toast.LENGTH_SHORT).show();
			showToast("WeiboException");
			
		}

		@Override
		public void onIOException(IOException arg0) {
			//Toast.makeText(getApplicationContext(),"IOException", Toast.LENGTH_SHORT).show();
			showToast("WeiboException");
		}
		
	}
	
//	RequestListener requestlistener = new RequestListener(){
//		
//		@Override
//		public void onComplete(String arg0) {
//		}
//
//		@Override
//		public void onError(WeiboException arg0) {
//		}
//
//		@Override
//		public void onIOException(IOException arg0) {
//		}
//	};

	public void showToast(String str) {
		Toast.makeText(KFCLogin.this, str, Toast.LENGTH_SHORT).show();
	}
	
	class AuthDialogListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            String token = values.getString("access_token");
            String expires_in = values.getString("expires_in");
            KFCLogin.accessToken = new Oauth2AccessToken(token, expires_in);
            if (KFCLogin.accessToken.isSessionValid()) {
                AccessTokenKeeper.keepAccessToken(KFCLogin.this,
                        accessToken);

                StatusesAPI api = new StatusesAPI(KFCLogin.accessToken);
				api.update("我在用yysuperstart外卖宝，非常好用，下载地址：", "90.00", "90.00", new myRequestListener());
            }
        }

        @Override
        public void onError(WeiboDialogError e) {
            Toast.makeText(getApplicationContext(),
                    "Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(getApplicationContext(), "Auth cancel",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(getApplicationContext(),
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }

    }
}
