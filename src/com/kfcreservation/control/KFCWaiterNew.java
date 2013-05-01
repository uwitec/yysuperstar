package com.kfcreservation.control;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.kfcreservation.R;

@SuppressWarnings("deprecation")
public class KFCWaiterNew extends Activity {
	private List<View> mViewlist;
	private LocalActivityManager mManager;
	private TabHost mTabHost;
	private ViewPager mPager;

	public void init() {

		mTabHost = (TabHost) findViewById(R.id.tabhost);
		mTabHost.setup();
		mTabHost.setup(mManager);
		
		mPager = (ViewPager) findViewById(R.id.viewpager);

		mViewlist = new ArrayList<View>();

		Intent kfcmenu = new Intent(this, KFCMenu.class);
		Intent kfcshoppingcar = new Intent(this, KFCShoppingCar.class);
		Intent kfcmyadds = new Intent(this, KFCMyAdds.class);

		mViewlist.add(getView("kfcmenu", kfcmenu));
		mViewlist.add(getView("kfcshoppingcar", kfcshoppingcar));
		mViewlist.add(getView("kfcmyadds", kfcmyadds));

		RelativeLayout tabIndicator1 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		RelativeLayout tabIndicator2 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		RelativeLayout tabIndicator3 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);

		ImageView title1 = (ImageView) tabIndicator1
				.findViewById(R.id.iv_image);
		ImageView title2 = (ImageView) tabIndicator2
				.findViewById(R.id.iv_image);
		ImageView title3 = (ImageView) tabIndicator3
				.findViewById(R.id.iv_image);

		title1.setImageResource(R.drawable.title_tong);
		title2.setImageResource(R.drawable.title_shop);
		title3.setImageResource(R.drawable.title_adr);

		mTabHost.addTab(mTabHost.newTabSpec("kfcmenu").setContent(kfcmenu)
				.setIndicator(tabIndicator1));
		mTabHost.addTab(mTabHost.newTabSpec("kfcshoppingcar")
				.setContent(kfcshoppingcar).setIndicator(tabIndicator2));
		mTabHost.addTab(mTabHost.newTabSpec("kfcmyadds").setContent(kfcmyadds)
				.setIndicator(tabIndicator3));

		mTabHost.setOnTabChangedListener(setontabchangelistener);
		mPager.setAdapter(setpageradapter);
		mPager.setOnPageChangeListener(setonpagechangelistener);
	}

	private View getView(String id, Intent intent) {
		return mManager.startActivity(id, intent).getDecorView();
	}

	OnTabChangeListener setontabchangelistener = new OnTabChangeListener() {
		@Override
		public void onTabChanged(String tabId) {

			if ("kfcmenu".equals(tabId)) {
				mPager.setCurrentItem(0);
			}
			if ("kfcshoppingcar".equals(tabId)) {
				mPager.setCurrentItem(1);
			}
			if ("kfcmyadds".equals(tabId)) {
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waiter_main);
		mManager = new LocalActivityManager(this, true);
		mManager.dispatchCreate(savedInstanceState);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}