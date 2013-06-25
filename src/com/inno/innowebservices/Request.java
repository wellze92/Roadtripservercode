package com.inno.innowebservices;

import com.google.appengine.api.datastore.Entity;

public class Request {
	
	private String accept, id, user, listID= "";

	public String getAccept() {
		return accept;
	}
	public void setAccept(String accept) {
		this.accept = accept;
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
	public boolean upData(){
		Entity req = new Entity("reqID",id);
		
		req.setProperty("accept", accept);
		req.setProperty("id", id);
		req.setProperty("user", user);

		Utils.updateRequest(req);
		return true;
	}
	@Override
	public String toString() {
		return "Request [Accepted=" + accept + ", reqID=" + id + ", User=" + user
				+ ", ListingID=" + listID + "]";
	}
	public String getListID() {
		return listID;
	}
	public void setListID(String listID) {
		this.listID = listID;
	}
}
