package com.inno.innowebservices;

import com.google.appengine.api.datastore.Entity;

public class Request {
	
	private String accept = "false";
	private String reqID, user, listID;

	public boolean upData(){
		Entity req = new Entity("reqID",reqID);
		
		req.setProperty("accept", accept);
		req.setProperty("reqID", reqID);
		req.setProperty("user", user);
		req.setProperty("ListId", listID);

		Utils.updateRequest(req);
		return true;
	}
	
	public String getAccept() {
		return accept;
	}
	public void setAccept(String accept) {
		this.accept = accept;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}	
	public String getListID() {
		return listID;
	}
	public void setListID(String listID) {
		this.listID = listID;
	}
	public String getReqID() {
		return reqID;
	}
	public void setReqID(String reqID) {
		this.reqID = reqID;
	}
}
