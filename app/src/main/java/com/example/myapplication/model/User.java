package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("profile_image")
	private ProfileImage profileImage;

	@SerializedName("name")
	private String name;

	@SerializedName("twitter_username")
	private String twitterUsername;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("links")
	private Links links;

	@SerializedName("id")
	private String id;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("instagram_username")
	private String instagramUsername;

	@SerializedName("portfolio_url")
	private String portfolioUrl;

	@SerializedName("username")
	private String username;

	public ProfileImage getProfileImage(){
		return profileImage;
	}

	public String getName(){
		return name;
	}

	public String getTwitterUsername(){
		return twitterUsername;
	}

	public String getLastName(){
		return lastName;
	}

	public Links getLinks(){
		return links;
	}

	public String getId(){
		return id;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getInstagramUsername(){
		return instagramUsername;
	}

	public String getPortfolioUrl(){
		return portfolioUrl;
	}

	public String getUsername(){
		return username;
	}
}