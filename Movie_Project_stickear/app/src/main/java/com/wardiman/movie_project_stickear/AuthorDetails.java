package com.wardiman.movie_project_stickear;

import com.google.gson.annotations.SerializedName;

public class AuthorDetails{

	@SerializedName("avatar_path")
	private String avatarPath;

	@SerializedName("name")
	private String name;

	@SerializedName("rating")
	private double rating;

	@SerializedName("username")
	private String username;

	public void setAvatarPath(String avatarPath){
		this.avatarPath = avatarPath;
	}

	public String getAvatarPath(){
		return avatarPath;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"AuthorDetails{" + 
			"avatar_path = '" + avatarPath + '\'' + 
			",name = '" + name + '\'' + 
			",rating = '" + rating + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}