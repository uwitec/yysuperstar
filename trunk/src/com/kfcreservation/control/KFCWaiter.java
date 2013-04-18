package com.kfcreservation.control;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.kfcreservation.R;
import com.kfcreservation.core.ExitApplication;

@SuppressWarnings("deprecation")
public class KFCWaiter extends Activity {
	List<View> mlistView;
	Context mContext = null;
	LocalActivityManager mManager = null;
	TabHost mTabHost = null;
	private ViewPager mPager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);
		setContentView(R.layout.waiter);

		mManager = new LocalActivityManager(this, true);
		mManager.dispatchCreate(savedInstanceState);

		mTabHost = (TabHost) findViewById(R.id.tabhost);
		mTabHost.setup();
		mTabHost.setup(mManager);

		mContext = KFCWaiter.this;

		mPager = (ViewPager) findViewById(R.id.viewpager);

		mlistView = new ArrayList<View>();

		Intent iKFCMenu = new Intent(this, KFCMenu.class);
		Intent iKFCShoppingCar = new Intent(this, KFCShoppingCar.class);
		Intent iKFCMyAdds = new Intent(this, KFCMyAdds.class);
		
		mlistView.add(getView("KFCMenu", iKFCMenu));
		mlistView.add(getView("KFCShoppingCar", iKFCShoppingCar));
		mlistView.add(getView("KFCMyAdds", iKFCMyAdds));

		RelativeLayout tabIndicator1 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		TextView tab1 = (TextView) tabIndicator1.findViewById(R.id.tv_text);
		tab1.setText("KFC菜单");
		RelativeLayout tabIndicator2 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		TextView tab2 = (TextView) tabIndicator2.findViewById(R.id.tv_text);
		tab2.setText("我的KFC");
		RelativeLayout tabIndicator3 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		TextView tab3 = (TextView) tabIndicator3.findViewById(R.id.tv_text);
		tab3.setText("我的地址");

		mTabHost.addTab(mTabHost.newTabSpec("Menu").setContent(iKFCMenu).
				setIndicator(tabIndicator1));
		
		mTabHost.addTab(mTabHost.newTabSpec("ShoppingCar").setContent(iKFCShoppingCar)
				.setIndicator(tabIndicator2));
		
		mTabHost.addTab(mTabHost.newTabSpec("MyAdds").setContent(iKFCMyAdds)
				.setIndicator(tabIndicator3));

		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				if ("Menu".equals(tabId)) {
					mPager.setCurrentItem(0);
				}
				if ("ShoppingCar".equals(tabId)) {
					mPager.setCurrentItem(1);
				}
				if ("MyAdds".equals(tabId)) {
					mPager.setCurrentItem(2);
				}
			}
		});
		mPager.setAdapter(new PagerAdapter() {
			
			private List<View> list = mlistView;
			
			@Override
			public void destroyItem(ViewGroup view, int position, Object arg2) {
				ViewPager pViewPager = ((ViewPager) view);
				pViewPager.removeView(list.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup view, int position) {
				ViewPager mViewPager = ((ViewPager) view);
				mViewPager.addView(list.get(position));
				return list.get(position);
			}
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				return list.size();
			}
		});

		mPager.setOnPageChangeListener(new OnPageChangeListener() {
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
		});
	}

	private View getView(String id, Intent intent) {
		Log.d("KFC", "getView() called! id = " + id);
		return mManager.startActivity(id, intent).getDecorView();
	}
	
//	private class MyPageAdapter extends PagerAdapter {
//
//		private List<View> list;
//
//		private MyPageAdapter(List<View> list) {
//			this.list = list;
//		}
//
//		@Override
//		public void destroyItem(ViewGroup view, int position, Object arg2) {
//			ViewPager pViewPager = ((ViewPager) view);
//			pViewPager.removeView(list.get(position));
//		}
//
//		@Override
//		public Object instantiateItem(ViewGroup view, int position) {
//			ViewPager mViewPager = ((ViewPager) view);
//			mViewPager.addView(list.get(position));
//			return list.get(position);
//		}
//
//		@Override
//		public void restoreState(Parcelable arg0, ClassLoader arg1) {
//		}
//
//		@Override
//		public Parcelable saveState() {
//			return null;
//		}
//
//		@Override
//		public int getCount() {
//			return list.size();
//		}
//
//		@Override
//		public boolean isViewFromObject(View arg0, Object arg1) {
//			return arg0 == arg1;
//		}
//
//	}

}