package com.example.myapplication.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SearchPhotoModel{

	@SerializedName("total")
	private int total;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<ResultsItem> results;

	public int getTotal(){
		return total;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<ResultsItem> getResults(){
		return results;
	}
}