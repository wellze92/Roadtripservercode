package com.inno.innowebservices;


import com.google.appengine.api.datastore.Entity;

/**
 * Listing object that represents a Listing and its values. 
 * 
 * @author Micah Cinco
 * @version 1
 *
 */
public class Listing {

	private String id;
	private String user;		
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
	private String image1;
	private String image2;
	private String listingComment;
	
		
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
	
	/**
	 * Method that updates one listing's values
	 */
	public boolean upData(){
		//Entity user = new Entity("id",name);
		Entity listing = new Entity("Listid",id);
		
		listing.setProperty("ListId", id);
		listing.setProperty("user", user);
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
		listing.setProperty("image1", image1);
		listing.setProperty("image2", image2);
		listing.setProperty("listcomment", listingComment);

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
	public String getListingComment() {
		return listingComment;
	}
	public void setListingComment(String listingComment) {
		this.listingComment = listingComment;
	}
	
}
