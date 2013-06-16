package com.inno.innowebservices;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class InnoWebservicesServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
	String Username = req.getParameter("us");
	String pass = req.getParameter("ps");
	
	Key k = KeyFactory.createKey("id", Username);
	UserPojo up = Utils.getEntity(k);
	
	if (pass.equals(up.getPassword())){
		 PrintWriter out = resp.getWriter();
		    String title = "Log_in_response";
		    
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
	
	else{
		 PrintWriter out = resp.getWriter();
		    String title = "Log_in_response";
		    
		    		  out.println(
				                
		    				  "{"+  "\"" + title +"\"" +  ":   { \n" +
		    				
		  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "fail		" +"\"" + "\n"  + 
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
		   * Checking to see if the username already exists in the database
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
