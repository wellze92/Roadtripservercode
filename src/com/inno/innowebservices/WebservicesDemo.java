package com.inno.innowebservices;

import javax.jws.WebMethod;
import javax.jws.WebService;



import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
@WebService
public class WebservicesDemo {
	@WebMethod
	public boolean createUser(String userId, String username, String password, String email, String age){
		Entity employee = new Entity("Employee",userId);
		employee.setProperty("UserID", userId);
		employee.setProperty("username", username);
		employee.setProperty("password",password);
		employee.setProperty("email", email);
		employee.setProperty("age", age);
		
		System.out.println("USERID" + userId);
		
		Utils.upadeEntity(employee);
		return true;
	}
	
	@WebMethod
	public UserPojo getUser(String name){
		Key k = KeyFactory.createKey("Employee", name);
		UserPojo up = Utils.getEntity(k);
		return up;
	}
}

