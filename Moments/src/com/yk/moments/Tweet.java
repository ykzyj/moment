package com.yk.moments;

import java.util.ArrayList;
import java.util.List;

public class Tweet {
	private String content;
	private List<Image> images=new ArrayList<>();
	private Sender sender=new Sender();
	private List<Tweet> comments=new ArrayList<>();;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public Sender getSender() {
		return sender;
	}
	public void setSender(Sender sender) {
		this.sender = sender;
	}
	public List<Tweet> getLs_comments() {
		return comments;
	}
	public void setLs_comments(List<Tweet> ls_comments) {
		this.comments = ls_comments;
	}
}
