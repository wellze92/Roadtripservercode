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
			up.setId(e.getProperty("UserId").toString());
			up.setName(e.getProperty("username").toString());
			up.setPassword(e.getProperty("password").toString());
			up.setEmail(e.getProperty("email").toString());
			up.setAge(e.getProperty("age").toString());
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return up;
	}
	
}
