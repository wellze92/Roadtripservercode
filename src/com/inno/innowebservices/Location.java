package com.inno.innowebservices;

import com.google.appengine.api.datastore.Entity;

public class Location {
	private String id;
	private String user;		
	
	private String longi;
	private String lata;

	
	

	public boolean upData(){
		//Entity user = new Entity("id",name);
		Entity loc = new Entity("CurListid",id);
		
		loc.setProperty("ListId", id);
		loc.setProperty("user", user);
		loc.setProperty("longi", longi);
		loc.setProperty("lata", lata);
		
		Utils.updateLocation(loc);
		return true;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLongi() {
		return longi;
	}
	public void setLongi(String longi) {
		this.longi = longi;
	}
	public String getLata() {
		return lata;
	}
	public void setLata(String lata) {
		this.lata = lata;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

}
