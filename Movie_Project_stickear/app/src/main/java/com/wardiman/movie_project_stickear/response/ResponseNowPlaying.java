package com.wardiman.movie_project_stickear.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.wardiman.movie_project_stickear.Dates;
import com.wardiman.movie_project_stickear.model.ItemNowPlaying;

public class ResponseNowPlaying {

	@SerializedName("dates")
	private Dates dates;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<ItemNowPlaying> results;

	@SerializedName("total_results")
	private int totalResults;

	public Dates getDates(){
		return dates;
	}

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<ItemNowPlaying> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}
}