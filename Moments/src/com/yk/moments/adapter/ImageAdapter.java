package com.yk.moments.adapter;

import java.util.List;

import android.content.Context;

import com.yk.moments.Image;
import com.yk.moments.R;
import com.yk.moments.R.id;
import com.yk.moments.adapterUtil.AdapterUtil;
import com.yk.moments.adapterUtil.ViewHolderUtil;

public class ImageAdapter extends AdapterUtil<Image> {

	public ImageAdapter(Context context, List<Image> datas, int layoutId) {
		super(context, datas, layoutId);
	}

	@Override
	public void initShowDate(ViewHolderUtil viewHolder, Image t, int position) {
		viewHolder.setImageURI(R.id.tweet_image, t.getUrl());
	}

}
