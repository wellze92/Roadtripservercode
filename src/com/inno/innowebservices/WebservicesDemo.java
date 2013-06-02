package com.inno.innowebservices;

import javax.jws.WebMethod;
import javax.jws.WebService;



import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
@WebService
public class WebservicesDemo {
	@WebMethod
	public boolean createUser(String userId, String username, String password, String email, String age, String gender, String smoker,
			String address, String phone, String music, String interest, String about){
		Entity user = new Entity("Employee",username);
		
		user.setProperty("username", username);
		user.setProperty("password",password);
		user.setProperty("email", email);
		user.setProperty("age", age);
		user.setProperty("gender", gender);
		user.setProperty("smoker", smoker);
		user.setProperty("address", address);
		user.setProperty("phone", phone);
		user.setProperty("music", music);
		user.setProperty("interest", interest);
		user.setProperty("about", about);
		
		
		System.out.println("USERID" + userId);
		
		Utils.upadeEntity(user);
		return true;
	}
	
	@WebMethod
	public UserPojo getUser(String name){
		Key k = KeyFactory.createKey("Employee", name);
		UserPojo up = Utils.getEntity(k);
		return up;
	}
}

