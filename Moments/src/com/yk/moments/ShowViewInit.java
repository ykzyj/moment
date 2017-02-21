package com.yk.moments;

import java.util.ArrayList;
import java.util.List;

import com.yk.moments.adapter.TweetAdapter;
import com.yk.moments.httpUtil.JsonListUtil;
import com.yk.moments.view.ScrollLoadListview;
import com.yk.moments.view.ScrollLoadListview.IScrollLoadListListener;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;

public class ShowViewInit extends JsonListUtil<Tweet> implements IScrollLoadListListener {
	
	private TweetAdapter momentsAdapter;
	private Context mContext;
	private ScrollLoadListview mListView;
	private SwipeRefreshLayout mlayout;
	private List<Tweet> mData=new ArrayList<Tweet>() ;
	List<Tweet> noNullOfData;
	private int index=0;
	private int offset=5;

	public ShowViewInit(Context context,String url,ScrollLoadListview listView,SwipeRefreshLayout layout) {
		super(context,url);
		mContext=context;
		mListView=listView;
		
		momentsAdapter=new TweetAdapter(mContext,mData , R.layout.tweet_item);
		mListView.setAdapter(momentsAdapter);
		mlayout=layout;
		mListView.setILoadListListener(this);
	}

	@Override
	public void initShowData(List<Tweet> datas, Class cla) {
		noNullOfData=new ArrayList<Tweet>();
		for(int i=0;i<datas.size();i++)
		{
			if((datas.get(i).getContent()!=null&&
					!"".equals(datas.get(i).getContent()))||
					(datas.get(i).getImages()!=null&&
							datas.get(i).getImages().size()!=0))
			{
				noNullOfData.add(datas.get(i));
			}
		}
		//momentsAdapter=new TweetAdapter(mContext, noNullOfData, R.layout.tweet_item);
		mData.clear();
		initShowData();
		//mData.addAll(ShowData);
		momentsAdapter.notifyDataSetChanged();
		mlayout.setRefreshing(false);
	}

    private void initShowData() {
        List<Tweet> ShowData=new ArrayList<Tweet>() ;
        int t_index=index;
        for(int i=index;(i<t_index+offset)&&(i<noNullOfData.size());i++)
        {
            ShowData.add(noNullOfData.get(i));
            index++;
        }
        mData.addAll(ShowData);
    }

    @Override
    public void onLoad() {
        //mData.addAll(ShowData);
        initShowData();
        momentsAdapter.notifyDataSetChanged();
    }

    public void initOriginal()
    {
    	/*mData.clear();
    	List<Tweet> ShowData=new ArrayList<Tweet>() ;
        for(int i=0;(i<offset)&&(i<noNullOfData.size());i++)
        {
            ShowData.add(noNullOfData.get(i));
        }
        index=0;
        mData.addAll(ShowData);*/
    	for(int i=noNullOfData.size()-1;i>=offset;i--)
    	{
    		if(i<mData.size())
    		{
        		mData.remove(i);
        		index--;
    		}
    	}
        momentsAdapter.notifyDataSetChanged();
    }
}
