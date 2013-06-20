package com.inno.innowebservices;

import com.google.appengine.api.datastore.Entity;

public class Comment {

	private String id;
	private String user;
	private String comment;
	
	
	public boolean upData(){
		//Entity user = new Entity("id",name);
		Entity listing = new Entity("Commentid",id);
		
		listing.setProperty("ListId", id);
		listing.setProperty("user", user);
		listing.setProperty("comment", comment);
		return true;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}	
	
	
	
}
