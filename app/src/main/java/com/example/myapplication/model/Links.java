package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("self")
	private String self;

	@SerializedName("html")
	private String html;

	@SerializedName("photos")
	private String photos;

	@SerializedName("likes")
	private String likes;

	@SerializedName("download")
	private String download;

	public String getSelf(){
		return self;
	}

	public String getHtml(){
		return html;
	}

	public String getPhotos(){
		return photos;
	}

	public String getLikes(){
		return likes;
	}

	public String getDownload(){
		return download;
	}
}