package com.inno.innowebservices;

import com.google.appengine.api.datastore.Entity;

public class Request {
	
	private String accept, decline, id = "";

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getDecline() {
		return decline;
	}

	public void setDecline(String decline) {
		this.decline = decline;
	}
	
	public boolean upData(){
		Entity req = new Entity("ReqID",id);
		
		req.setProperty("accept", accept);
		req.setProperty("decline", decline);

		Utils.updateRequest(req);
		return true;
	}
}
