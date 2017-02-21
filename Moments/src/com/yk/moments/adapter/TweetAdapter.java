package com.yk.moments.adapter;

import java.util.List;

import android.content.Context;

import com.yk.moments.R;
import com.yk.moments.Tweet;
import com.yk.moments.R.id;
import com.yk.moments.R.layout;
import com.yk.moments.adapterUtil.AdapterUtil;
import com.yk.moments.adapterUtil.ViewHolderUtil;
import com.yk.moments.view.InnerGridView;
import com.yk.moments.view.InnerListView;

public class TweetAdapter extends AdapterUtil<Tweet>  {
	
	private Context mContext;

	public TweetAdapter(Context context, List<Tweet> datas, int layoutId) {
		super(context, datas, layoutId);
		mContext=context;
	}

	@Override
	public void initShowDate(ViewHolderUtil viewHolder, Tweet t, int position) {
        viewHolder.setImageURI(R.id.sender_avatar, t.getSender().getAvatar());
        viewHolder.setText(R.id.txt_nick, t.getSender().getNick());
        viewHolder.setText(R.id.txt_content, t.getContent());
        ImageAdapter imgadapter=new ImageAdapter(mContext, t.getImages(), R.layout.image_item); 
        InnerGridView gridview=viewHolder.getView(R.id.gv_image);
        gridview.setAdapter(imgadapter);
        CommentAdapter comadapter=new CommentAdapter(mContext, t.getLs_comments(), R.layout.comment_item);
        InnerListView listview=viewHolder.getView(R.id.ls_comment);
        listview.setAdapter(comadapter);
	}

}
