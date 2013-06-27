package com.inno.innowebservices;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
/**
 * Gets the user details matched to the code without having to pass a password
 * NOT USED IN FINAL BUILD
 * @author Andrew Wells
 *
 */
public class UserQuery extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
	
	Entity person = null;	
		
	String Username = req.getParameter("us");
	Filter f = new  FilterPredicate("username", FilterOperator.EQUAL, Username);	
	 Query q = new Query("id").setFilter(f);
	 
	 
	 DatastoreService dstore = DatastoreServiceFactory.getDatastoreService();
	 PreparedQuery p = dstore.prepare(q);
	 
	 
	  for (Entity e: p.asIterable()){
		  	person = e;
		  	break;
	  }
	  
	  if (person == null){
		  PrintWriter out = resp.getWriter();
		    String title = "Search_response" ;
		    out.println(
		                
		    		  "{"+  "\"" + title +"\"" +  ":   { \n" +
			    				
	  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "fail " + " "  + "\"" + "\n"  + 
						   "} }"
			                
			                );
	  
	  }
	
	  else{
		  
	  
	Key k = KeyFactory.createKey("id", Username);
	UserPojo up = Utils.getEntity(k);
	
	
	
	
	
		 PrintWriter out = resp.getWriter();
		    String title = "Person_search response";
		    
		    		  out.println(
				                
		    				  "{"+  "\"" + title +"\"" +  ":   { \n" +
		    				"\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "success" +"\"" + ", \n"  +
		    						  
				                
				                "\t"+ "\"" + "user" +"\"" + ": { \n" +
				                "\t\t"+  "\"" + "user_name" +"\"" + ":" +  "\"" + up.getName() +"\"" + ", \n"  +
				                "\t\t"+  "\"" + "email" +"\""+ ":" +   "\"" +  up.getEmail() +"\"" + ", \n"  +
				                "\t\t"+  "\"" + "age" +"\"" +  ":" +  "\"" +  up.getAge() +"\"" + ", \n"  +
				                "\t\t"+  "\"" + "gender" +"\"" +  ":" +  "\"" + up.getGender() +"\"" + ", \n"+   
				                "\t\t"+  "\"" + "licence" +"\"" +  ":" + "\"" + up.getLiscence() +"\"" +", \n"+  
				                "\t\t"+  "\"" + "smoker" +"\"" +  ":" +  "\"" + up.getSmoker() +"\"" +", \n"  +
				                "\t\t"+  "\"" + "TransMissionAuto" +"\"" +  ":" +  "\"" + up.getTransAuto() +"\"" +", \n"  +
				                "\t\t"+  "\"" + "TransMissionManual" +"\"" +  ":" +  "\"" + up.getTransMan() +"\"" +", \n"  +
				                "\t\t"+  "\"" + "Address" +"\"" +  ":" + "\"" + up.getAddress() +"\"" +", \n" + 
				                "\t\t"+  "\"" + "phone" +"\"" +  ":" +  "\"" + up.getPhone() +"\"" +  ", \n"  +
				                "\t\t"+  "\"" + "music" +"\"" +  ":" + "\"" + up.getMusic() +"\"" +  ", \n"  +
				                "\t\t"+  "\"" + "interest" +"\"" + ":" +  "\"" +up.getInterest() +"\"" +  ", \n"+  
				                "\t\t"+  "\"" + "about" +"\"" +  ":" + "\"" + up.getAbout() +"\"" +  " \n" +
				                "} \n"+
				                "} }"
				                
				                
				                );

	  }
		    
	}
	

	
}
