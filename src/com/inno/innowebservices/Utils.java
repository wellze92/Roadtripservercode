package com.inno.innowebservices;



import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

public class Utils {

	private static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();	
	public static void upadeEntity (Entity e){
		ds.put(e);
	}
	public static UserPojo getEntity(Key id){
		UserPojo up = new UserPojo();
		try {
			Entity e = ds.get(id);
			
			up.setName(e.getProperty("username").toString());
			up.setPassword(e.getProperty("password").toString());
			up.setEmail(e.getProperty("email").toString());
			up.setAge(e.getProperty("age").toString());
			up.setGender(e.getProperty("gender").toString());
			up.setSmoker(e.getProperty("smoker").toString());
			up.setAddress(e.getProperty("address").toString());
			up.setLiscence(e.getProperty("lis").toString());
			up.setPhone(e.getProperty("phone").toString());
			up.setMusic(e.getProperty("music").toString());
			up.setInterest(e.getProperty("interest").toString());
			up.setAbout(e.getProperty("about").toString());
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return up;
	}
	
}
