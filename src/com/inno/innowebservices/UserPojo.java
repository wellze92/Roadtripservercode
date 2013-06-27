package com.inno.innowebservices;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Entity;


/**
 * All of the detais linked to a user
 * @author Andrew Wells
 *
 */
public class UserPojo {
	
	private String name;		
	private String password;
	private String email;
	private String age;
	private String smoker;
	private String address;
	private String transAuto;
	private String liscence;
	private String phone;
	private String music;
	private String interest;
	private String about;
	private String gender;
	private String transMan;
	private Blob image;
	private String imageType;
	private String passRate;
	private String drivRate;
	private String passCount;
	private String driveCount;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSmoker() {
		return smoker;
	}
	public void setSmoker(String smoker) {
		this.smoker = smoker;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLiscence() {
		return liscence;
	}
	public void setLiscence(String liscence) {
		this.liscence = liscence;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public boolean upData(){
		/**
		 * Uploads it to the database
		 */
Entity user = new Entity("id",name);
		
		user.setProperty("username", name);
		user.setProperty("password",password);
		user.setProperty("email", email);
		user.setProperty("age", age);
		user.setProperty("gender", gender);
		user.setProperty("lis", liscence);
		user.setProperty("smoker", smoker);
		user.setProperty("address", address);
		user.setProperty("transAuto",transAuto);
		user.setProperty("transMan",transMan);
		user.setProperty("phone", phone);
		user.setProperty("music", music);
		user.setProperty("interest", interest);
		user.setProperty("about", about);
		
		user.setProperty("drivRate", drivRate);
		user.setProperty("passRate", passRate);
		user.setProperty("passCount",passCount);
		user.setProperty("driveCount", driveCount);
		
		
		
		Utils.updateEntity(user);
		return true;
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
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public String getPassRate() {
		return passRate;
	}
	public void setPassRate(String passRate) {
		this.passRate = passRate;
	}
	public String getDrivRate() {
		return drivRate;
	}
	public void setDrivRate(String drivRate) {
		this.drivRate = drivRate;
	}
	public String getPassCount() {
		return passCount;
	}
	public void setPassCount(String passCount) {
		this.passCount = passCount;
	}
	public String getDriveCount() {
		return driveCount;
	}
	public void setDriveCount(String driveCount) {
		this.driveCount = driveCount;
	}
}
