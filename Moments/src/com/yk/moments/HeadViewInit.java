package com.yk.moments;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yk.moments.cacheUtil.DiskCacheUtil;
import com.yk.moments.cacheUtil.ImageCache;
import com.yk.moments.httpUtil.ImgUtil;
import com.yk.moments.httpUtil.JsonListUtil;

public class HeadViewInit extends JsonListUtil<Sender>{
	
	private Context mContext;
	private View mHeadView;
	private ImageCache mimageCache;
	private DiskCacheUtil mimageDiskCache;

	public HeadViewInit(Context context,String url,View headview) {
		super(context,url);
		mContext=context;
		mHeadView=headview;
		mimageCache=new ImageCache();
		mimageDiskCache=new DiskCacheUtil(context);
	}

	@Override
	public void initShowData(List<Sender> datas, Class cla) {
		if(datas!=null&&datas.get(0)!=null)
		{
			TextView tweet_holder_nick=(TextView) mHeadView.findViewById(R.id.tweet_holder_nick);
			tweet_holder_nick.setText(datas.get(0).getNick());
			ImageView tweet_holder_top=(ImageView) mHeadView.findViewById(R.id.tweet_holder_top);
			if(datas.get(0).getProfile_image()!=null)
			{
				tweet_holder_top.setTag(datas.get(0).getProfile_image());
				new ImgUtil(datas.get(0).getProfile_image(), tweet_holder_top,mimageCache,mimageDiskCache).start();
			}
			ImageView tweet_holder_avatar=(ImageView) mHeadView.findViewById(R.id.tweet_holder_avatar);
			if(datas.get(0).getAvatar()!=null)
			{
				tweet_holder_avatar.setTag(datas.get(0).getAvatar());
				new ImgUtil(datas.get(0).getAvatar(), tweet_holder_avatar,mimageCache,mimageDiskCache).start();
			}
		}
	}

}
