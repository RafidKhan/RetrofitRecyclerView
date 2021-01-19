package com.example.myapplication.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResultsItem{

	@SerializedName("current_user_collections")
	private List<Object> currentUserCollections;

	@SerializedName("color")
	private String color;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("description")
	private String description;

	@SerializedName("liked_by_user")
	private boolean likedByUser;

	@SerializedName("urls")
	private Urls urls;

	@SerializedName("width")
	private int width;

	@SerializedName("blur_hash")
	private String blurHash;

	@SerializedName("links")
	private Links links;

	@SerializedName("id")
	private String id;

	@SerializedName("user")
	private User user;

	@SerializedName("height")
	private int height;

	@SerializedName("likes")
	private int likes;

	public List<Object> getCurrentUserCollections(){
		return currentUserCollections;
	}

	public String getColor(){
		return color;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getDescription(){
		return description;
	}

	public boolean isLikedByUser(){
		return likedByUser;
	}

	public Urls getUrls(){
		return urls;
	}

	public int getWidth(){
		return width;
	}

	public String getBlurHash(){
		return blurHash;
	}

	public Links getLinks(){
		return links;
	}

	public String getId(){
		return id;
	}

	public User getUser(){
		return user;
	}

	public int getHeight(){
		return height;
	}

	public int getLikes(){
		return likes;
	}
}