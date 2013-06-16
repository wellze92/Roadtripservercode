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
	
	public void doPost(HttpServletRequest req,
              HttpServletResponse resp)
throws ServletException, IOException {
		boolean error = false;
		String errorname= "";
		  UserPojo pj = new UserPojo();
		  DatastoreService dstore = DatastoreServiceFactory.getDatastoreService();
		  
		  /**
		   * Checking to see if the username already exsist in the databse
		   */
		  Query q = new Query("name");
		  PreparedQuery p = dstore.prepare(q);
		  
		  for (Entity e: p.asIterable()){
			  if (e.getProperty("username").equals(req.getParameter("user")))
				  error = true;
			  	   errorname = "User already exist";
		  }
		  
		  if(!error){
		  
			pj.setName(req.getParameter("user"));
			pj.setPassword(req.getParameter("pass"));
			pj.setEmail(req.getParameter("email"));
			pj.setAge(req.getParameter("age"));
			pj.setGender(req.getParameter("gen"));
			pj.setLiscence(req.getParameter("lis"));
			pj.setSmoker(req.getParameter("smo"));
			pj.setTransAuto(req.getParameter("transAuto"));
			pj.setTransMan(req.getParameter("transMan"));
			pj.setAddress(req.getParameter("add"));
			pj.setPhone(req.getParameter("phone"));
			pj.setMusic(req.getParameter("music"));
			pj.setInterest(req.getParameter("int"));
			pj.setAbout(req.getParameter("about"));
			
			pj.upData();
			
			
			 PrintWriter out = resp.getWriter();
			    String title = "Register_response" ;
			    out.println(
			                
			    		  "{"+  "\"" + title +"\"" +  ":   { \n" +
				    				
		  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "sucessful		" +"\"" + "\n"  + 
							   "} }"
				                
				                );
		  }
		  else{
			  PrintWriter out = resp.getWriter();
			    String title = "Register_response" ;
			    out.println(
			                
			    		  "{"+  "\"" + title +"\"" +  ":   { \n" +
				    				
		  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "fail " + errorname + " "  + "\"" + "\n"  + 
							   "} }"
				                
				                );
		  }
}
	
}
