package com.inno.innowebservices;

import com.google.appengine.api.datastore.Entity;


/**
 * 
 * @author Andrew Wells
 * A comment is a string which is attached to a listing with the user id
 *
 */
public class Comment {

	private String id;
	private String user;
	private String comment;
	
	/**
	 * Uploads the comment to the server
	 * @return
	 */
	public boolean upData(){
		//Entity user = new Entity("id",name);
		Entity listing = new Entity("Commentid",id + comment + user); // HACK: Easy way to make unique. Means a user can make more then one comment on the listing 
		
		
		listing.setProperty("ListId", id);
		listing.setProperty("user", user);
		listing.setProperty("comment", comment);
		Utils.updateEntity(listing);
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
