package com.yk.moments.adapter;

import java.util.List;

import android.content.Context;

import com.yk.moments.R;
import com.yk.moments.Tweet;
import com.yk.moments.R.id;
import com.yk.moments.adapterUtil.AdapterUtil;
import com.yk.moments.adapterUtil.ViewHolderUtil;

public class CommentAdapter extends AdapterUtil<Tweet> {

	public CommentAdapter(Context context, List<Tweet> datas, int layoutId) {
		super(context, datas, layoutId);
	}

	@Override
	public void initShowDate(ViewHolderUtil viewHolder, Tweet t, int position) {
		viewHolder.setText(R.id.com_sender, t.getSender().getNick());
		viewHolder.setText(R.id.com_content, t.getContent());
	}

}
