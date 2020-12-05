package com.wardiman.movie_project_stickear.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.wardiman.movie_project_stickear.model.ItemReviews;

public class ResponseReviews {

	@SerializedName("id")
	private int id;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<ItemReviews> results;

	@SerializedName("total_results")
	private int totalResults;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setResults(List<ItemReviews> results){
		this.results = results;
	}

	public List<ItemReviews> getResults(){
		return results;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"id = '" + id + '\'' + 
			",page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}
}