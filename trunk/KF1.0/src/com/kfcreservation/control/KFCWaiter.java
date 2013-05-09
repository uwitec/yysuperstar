package com.kfcreservation.control;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.kfcreservation.R;
import com.kfcreservation.core.AppData;
import com.kfcreservation.handler.KFCLoginHandler;

@SuppressWarnings("deprecation")
public class KFCWaiter extends Activity {
	private List<View> mViewlist;
	//private Context mContext ;
	private LocalActivityManager mManager ;
	private TabHost mTabHost ;
	private ViewPager mPager ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ExitApplication.getInstance().addActivity(this);
		setContentView(R.layout.waiter_main);

		mManager = new LocalActivityManager(this, true);
		mManager.dispatchCreate(savedInstanceState);

		mTabHost = (TabHost) findViewById(R.id.tabhost);
		mTabHost.setup();
		mTabHost.setup(mManager);

		//mContext = KFCWaiter.this;

		mPager = (ViewPager) findViewById(R.id.viewpager);

		mViewlist = new ArrayList<View>();
		
		Intent iKFCMenu = new Intent(this, KFCMenu.class);
		Intent iKFCShoppingCar = new Intent(this, KFCShoppingCar.class);
		Intent iKFCMyAdds = new Intent(this, KFCMyAdds.class);
		
		mViewlist.add(getView("KFCMenu", iKFCMenu));
		mViewlist.add(getView("KFCShoppingCar", iKFCShoppingCar));
		mViewlist.add(getView("KFCMyAdds", iKFCMyAdds));

		RelativeLayout tabIndicator1 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.waiter_tabwidget, null);
		RelativeLayout tabIndicator2 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.waiter_tabwidget, null);
		RelativeLayout tabIndicator3 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.waiter_tabwidget, null);
		
		ImageView title1 = (ImageView)tabIndicator1.findViewById(R.id.iv_image);
		ImageView title2 = (ImageView)tabIndicator2.findViewById(R.id.iv_image);
		ImageView title3 = (ImageView)tabIndicator3.findViewById(R.id.iv_image);
		
		title1.setImageResource(R.drawable.title_tong);
		title2.setImageResource(R.drawable.title_shop);
		title3.setImageResource(R.drawable.title_adr);
		
		mTabHost.addTab(mTabHost.newTabSpec("Menu").setContent(iKFCMenu).setIndicator(tabIndicator1));
		mTabHost.addTab(mTabHost.newTabSpec("ShoppingCar").setContent(iKFCShoppingCar).setIndicator(tabIndicator2));
		mTabHost.addTab(mTabHost.newTabSpec("MyAdds").setContent(iKFCMyAdds).setIndicator(tabIndicator3));

		mTabHost.setOnTabChangedListener(setontabchangelistener);
		mPager.setAdapter(setpageradapter);
		mPager.setOnPageChangeListener(setonpagechangelistener);	
	}

	private View getView(String id, Intent intent) {
		return mManager.startActivity(id, intent).getDecorView();
	}
	
	OnTabChangeListener setontabchangelistener = new OnTabChangeListener(){
		@Override
		public void onTabChanged(String tabId) {
			
			if ("Menu".equals(tabId)) {
				mPager.setCurrentItem(0);
			}
			if ("ShoppingCar".equals(tabId)) {
				mPager.setCurrentItem(1);
//				try {
//					Thread.currentThread().join(2000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				System.out.println("---tabhost shoppingcar---");
//				
//				Thread subthread = new Thread(){
//					@Override
//					public void run() {
//						try {
//							sleep(3000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				};
//				
//				subthread.start();
//				
//				try {
//					subthread.join();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println("---sleep 3000 done---");
//				KFCShoppingCar kfcshoppingcar = (KFCShoppingCar) AppData.getActivityList("KFCShoppingCar");
//				kfcshoppingcar.setShoppingCarlist();
//				//kfcshoppingcar.ReloadShoppingCarlist();
//				kfcshoppingcar.setTotal();
//				System.out.println("---tabhost shoppingcar rf---");
			}
			if ("MyAdds".equals(tabId)) {
				mPager.setCurrentItem(2);
			}
		}
	};
	
	PagerAdapter setpageradapter = new PagerAdapter() {

		@Override
		public void destroyItem(ViewGroup view, int position, Object arg2) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.removeView(mViewlist.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			ViewPager mViewPager = ((ViewPager) view);
			mViewPager.addView(mViewlist.get(position));
			return mViewlist.get(position);
		}
		
		@Override
		public boolean isViewFromObject(View v, Object o) {
			return v == o;
		}
		
		@Override
		public int getCount() {
			return mViewlist.size();
		}
	};
	
	OnPageChangeListener setonpagechangelistener = new OnPageChangeListener() {
		@Override
		public void onPageSelected(int position) {
			mTabHost.setCurrentTab(position);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	};
}