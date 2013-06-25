package com.inno.innowebservices;


import com.google.appengine.api.datastore.Entity;

public class Listing {

	private String id;
	private String user;		
	private String listingType;
	private String origin;
	private String destination;
	private String date;
	private String time;
	private String seats;
	private String car;
	private String price;
	private String transAuto;
	private String transMan;
	private String bags;
	private String sharedDriving;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getListingType() {
		return listingType;
	}
	public void setListingType(String listingType) {
		this.listingType = listingType;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTransAuto() {
		return transAuto;
	}
	public void setTransAuto(String transmission) {
		this.transAuto = transmission;
	}
	public String getTransMan() {
		return transMan;
	}
	public void setTransMan(String transMan) {
		this.transMan = transMan;
	}
	public String getBags() {
		return bags;
	}
	public void setBags(String bags) {
		this.bags = bags;
	}
	public String getSharedDriving() {
		return sharedDriving;
	}
	public void setSharedDriving(String sharedDriving) {
		this.sharedDriving = sharedDriving;
	}
	

	public boolean upData(){
		//Entity user = new Entity("id",name);
		Entity listing = new Entity("Listid",id);
		
		listing.setProperty("ListId", id);
		listing.setProperty("user", user);
		listing.setProperty("listingType", listingType);
		listing.setProperty("origin", origin);
		listing.setProperty("destination", destination);
		listing.setProperty("date", date);
		listing.setProperty("time", time);
		listing.setProperty("seats", seats);
		listing.setProperty("carType", car);
		listing.setProperty("price",price);
		listing.setProperty("transAuto",transAuto);
		listing.setProperty("transMan",transMan);
		listing.setProperty("bags", bags);
		listing.setProperty("sharedDriving", sharedDriving);

		Utils.updateListing(listing);
		return true;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
