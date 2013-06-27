package com.inno.innowebservices;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
		    				
		  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "fail" +"\"" + "\n"  + 
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
		  Query q = new Query("id");
		  PreparedQuery p = dstore.prepare(q);
		  
		  for (Entity e: p.asIterable()){
			  if(e.getProperty("username")!= null){
			  if (e.getProperty("username").toString().equals(req.getParameter("user"))){
				  error = true;
			  	   errorname = "User already exist";
			  	   break;}}
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
			pj.setPassRate("0");
			pj.setDrivRate("0");
			pj.setPassCount("0");
			pj.setDriveCount("0");
			
			pj.upData();
			
			
			Properties pro = new Properties();
			Session session = Session.getDefaultInstance(pro, null);
			
			String message = "Dear New User \nWelcome to the RoadTrip application. Please feel free to use this service \nYour user name" +
					"is " + req.getParameter("user") +"\nYour Password is" +req.getParameter("pass") + "\nEnjoy the service\nRegards the road trip team"; 
			
			try{
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("wellze92@gmail.com"));
				msg.addRecipient(Message.RecipientType.TO,new InternetAddress(req.getParameter("email")));
				msg.setSubject("Welcome to Road Trip");
				msg.setText(message);
				Transport.send(msg);
			}
			catch(AddressException e){
				
			}
			catch (MessagingException e) {
				
			}	
			
			 PrintWriter out = resp.getWriter();
			    String title = "Register_response" ;
			    out.println(
			                
			    		  "{"+  "\"" + title +"\"" +  ":   { \n" +
				    				
		  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "sucessful" +"\"" + "\n"  + 
							   "} }"
				                
				                );
		  }
		  else{
			  PrintWriter out = resp.getWriter();
			    String title = "Register_response" ;
			    out.println(
			                
			    		  "{"+  "\"" + title +"\"" +  ":   { \n" +
				    				
		  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "fail " + " "  + "\"" + "\n"  + 
							   "} }"
				                
				                );
		  }
}
	
	public void doPut(HttpServletRequest req,
            HttpServletResponse resp)
throws ServletException, IOException {
		
		String rating ="";
		String count = "";
		String Username = req.getParameter("us");
		Key k = KeyFactory.createKey("id", Username);
		UserPojo up = Utils.getEntity(k);
		String choice = req.getParameter("op");
		if (choice.equals("pass")){
			rating = up.getPassRate();
			count = up.getPassCount();
		}
		else {rating = up.getDrivRate();
		count = up.getDriveCount();
		}
		
		Integer curav = Integer.parseInt(rating);
		Integer curcount = Integer.parseInt(count);
		
		Integer newrate= Integer.parseInt(req.getParameter("rate"));
		int total = curav * curcount;
		total = total + newrate;
		curcount++;
		int newav = total/curcount;
		
		if (choice.equals("pass")){
			up.setPassCount(curcount.toString());
			up.setPassRate("" +newav);
			count = up.getPassCount();
		}
		else {	up.setDriveCount(curcount.toString());
		up.setDrivRate("" +newav);
		}
		
		up.upData();
		
		 PrintWriter out = resp.getWriter();
		    String title = "Rating_Response" ;
		    out.println(
		                
		    		  "{"+  "\"" + title +"\"" +  ":   { \n" +
			    				
	  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "sussess " + " "  + "\"" + "\n"  + 
						   "} }"
			                
			                );
		
	}	
}
