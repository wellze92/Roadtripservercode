package com.inno.innowebservices;



import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
/**Adds information into the database and
 * Gets the information from the database and turns it into the object
 * @author Andrew Wells, Micah Cinco
 *
 */
public class Utils {


	private static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	private static DatastoreService list = DatastoreServiceFactory.getDatastoreService();
	
	// puts the information in the database
	public static void updateEntity (Entity e){
		ds.put(e);
	}
	// gets a user
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
			up.setTransAuto(e.getProperty("transAuto").toString());
			up.setTransMan(e.getProperty("transMan").toString());
			up.setLiscence(e.getProperty("lis").toString());
			up.setPhone(e.getProperty("phone").toString());
			up.setMusic(e.getProperty("music").toString());
			up.setInterest(e.getProperty("interest").toString());
			up.setAbout(e.getProperty("about").toString());
			up.setPassRate(e.getProperty("passRate").toString());
			up.setDrivRate(e.getProperty("drivRate").toString());
			up.setDriveCount(e.getProperty("driveCount").toString());
			up.setPassCount(e.getProperty("passCount").toString());
			
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return up;
	}
	
	public static void updateListing (Entity e){
		list.put(e);
	}
	
	/**
	 * Returns the specified Listing
	 * @param id
	 * @return
	 */
	public static Listing getListing(Key id){
		Listing up = new Listing();
		try {
			Entity e = list.get(id);
			
			//up.setId(id.getName());
			up.setId(e.getProperty("ListId").toString());
			up.setUser(e.getProperty("user").toString());
			up.setOrigin(e.getProperty("origin").toString());
			up.setDestination(e.getProperty("destination").toString());
			up.setTime(e.getProperty("time").toString());
			up.setDate(e.getProperty("date").toString());
			up.setSeats(e.getProperty("seats").toString());
			up.setCar(e.getProperty("carType").toString());
			up.setPrice(e.getProperty("price").toString());
			up.setTransAuto(e.getProperty("transAuto").toString());
			up.setTransMan(e.getProperty("transMan").toString());
			up.setBags(e.getProperty("bags").toString());
			up.setSharedDriving(e.getProperty("sharedDriving").toString());
			up.setImage1(e.getProperty("image1").toString());
			up.setImage2(e.getProperty("image2").toString());
			up.setListingComment(e.getProperty("listcomment").toString());
						
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return up;
	}
	
	// gets a location
	public static Location getLocation(Key id){
		Location up = new Location();
		try {
			Entity e = ds.get(id);
			
			//up.setId(e.getProperty("id").toString());
			up.setUser(e.getProperty("user").toString());
			up.setId(e.getProperty("ListId").toString());
			up.setLata(e.getProperty("longi").toString());
			up.setLongi(e.getProperty("lata").toString());
			
						
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return up;
	}
	
	public static void updateLocation (Entity e){
		list.put(e);
	}
	
	public static void updateRequest (Entity e){
		list.put(e);
	}
	
	/**
	 * Returns the specified Request.
	 * @param id
	 * @return
	 */
	public static Request getRequest(Key id){
		Request up = new Request();
		try {
			Entity e = list.get(id);
			
			up.setListID(e.getProperty("ListId").toString());
			up.setAccept(e.getProperty("accept").toString());
			up.setUser(e.getProperty("user").toString());
			up.setReqID(e.getProperty("reqID").toString());
						
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return up;
	}
	
	// gets a comment
	public static Comment getComment(Key id){
		Comment up = new Comment();
		try {
			Entity e = ds.get(id);
			
			up.setId(e.getProperty("ListId").toString());
			up.setUser(e.getProperty("user").toString());
			up.setComment(e.getProperty("comment").toString());
	
	
			
}
		catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return up;
	}
	}
