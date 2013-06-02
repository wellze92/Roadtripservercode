package com.inno.innowebservices;

import com.google.appengine.api.datastore.Entity;

public class UserPojo {
	
	private String name;		
	private String password;
	private String email;
	private String age;
	private String smoker;
	private String address;
	private String liscence;
	private String phone;
	private String music;
	private String interest;
	private String about;
	private String gender;
	
	
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
Entity user = new Entity("id",name);
		
		user.setProperty("username", name);
		user.setProperty("password",password);
		user.setProperty("email", email);
		user.setProperty("age", age);
		user.setProperty("gender", gender);
		user.setProperty("lis", liscence);
		user.setProperty("smoker", smoker);
		user.setProperty("address", address);
		user.setProperty("phone", phone);
		user.setProperty("music", music);
		user.setProperty("interest", interest);
		user.setProperty("about", about);
		
		Utils.upadeEntity(user);
		return true;
	}
}
