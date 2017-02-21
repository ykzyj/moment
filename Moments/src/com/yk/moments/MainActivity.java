package com.yk.moments;

import com.yk.moments.view.ScrollLoadListview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

	private static final int REFRESH_COMPLETE = 0X110;
	private  ScrollLoadListview mListView;
	private  ShowViewInit mShowViewInit;
	private  HeadViewInit mHeadViewInit;
	private SwipeRefreshLayout mSwipeLayout;  
	private Handler mHandler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			mSwipeLayout.setRefreshing(false);
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_tweets);
		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);  
		mSwipeLayout.setOnRefreshListener(this);  
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,  
                android.R.color.holo_orange_light, android.R.color.holo_red_light);  
        mSwipeLayout.setRefreshing(true);
		mListView=(ScrollLoadListview) findViewById(R.id.ls_tweet);
		
		mListView.setAdapter(null);
		View HeaderView=LayoutInflater.from(MainActivity.this).inflate(R.layout.list_top, null);
		mListView.addHeaderView(HeaderView);
		mShowViewInit=new ShowViewInit(MainActivity.this, "http://thoughtworks-ios.herokuapp.com/user/jsmith/tweets", mListView,mSwipeLayout);
		mShowViewInit.start();
		mHeadViewInit=new HeadViewInit(MainActivity.this, "http://thoughtworks-ios.herokuapp.com/user/jsmith", HeaderView);
		mHeadViewInit.start();
	}
	@Override
	public void onRefresh() {
		mShowViewInit.initOriginal();
		mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 1500);
	}

}
