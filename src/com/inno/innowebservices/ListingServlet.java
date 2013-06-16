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
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class ListingServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");

		String ListingID = req.getParameter("id");
		
		Key k = KeyFactory.createKey("id", ListingID);
		Listing list = Utils.getListing(k);
		
		boolean exist = false;
		String errorname= "";
		DatastoreService dstore = DatastoreServiceFactory.getDatastoreService();

		/**
		 * Checking to see if the listing actually exists in the database
		 */
		Query q = new Query("id");
		PreparedQuery p = dstore.prepare(q);

		for (Entity e: p.asIterable()){
			if (e.getProperty("id").equals(req.getParameter("id")))
				exist = true;
			errorname = "Listing does not exist";
		}
		
		if(exist){
			 PrintWriter out = resp.getWriter();
			    String title = "CreateListing_Response";
			    
			    		  out.println(
					                
			    				  "{"+  "\"" + title +"\"" +  ":   { \n" +
			    				"\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "success" +"\"" + ", \n"  +
			    						  
					                
					                "\t"+ "\"" + "Listing" +"\"" + ": { \n" +
					                "\t\t"+  "\"" + "ID" +"\"" + ":" +  "\"" + list.getId() +"\"" + ", \n"  +
					                "\t\t"+  "\"" + "Owner" +"\"" + ":" +  "\"" + list.getUser() +"\"" + ", \n"  +
					                "\t\t"+  "\"" + "Listing Type" +"\""+ ":" +   "\"" +  list.getListingType() +"\"" + ", \n"  +
					                "\t\t"+  "\"" + "Origin" +"\"" +  ":" +  "\"" +  list.getOrigin() +"\"" + ", \n"  +
					                "\t\t"+  "\"" + "Destination" +"\"" +  ":" +  "\"" + list.getDestination() +"\"" + ", \n"+   
					                "\t\t"+  "\"" + "Date" +"\"" +  ":" + "\"" + list.getDate() +"\"" +", \n"+  
					                "\t\t"+  "\"" + "Seats" +"\"" +  ":" +  "\"" + list.getSeats() +"\"" +", \n"  +
					                "\t\t"+  "\"" + "Car" +"\"" +  ":" + "\"" + list.getCar() +"\"" +", \n" + 
					                "\t\t"+  "\"" + "Price" +"\"" +  ":" + "\"" + list.getPrice() +"\"" +  " \n" +
					                "\t\t"+  "\"" + "TransMissionAuto" +"\"" +  ":" +  "\"" + list.getTransAuto() +"\"" +", \n"  +
					                "\t\t"+  "\"" + "TransMissionManual" +"\"" +  ":" +  "\"" + list.getTransMan() +"\"" +", \n"  +
					                "\t\t"+  "\"" + "Bags" +"\"" +  ":" +  "\"" + list.getBags() +"\"" +  ", \n"  +
					                "\t\t"+  "\"" + "Shared Driving" +"\"" +  ":" + "\"" + list.getSharedDriving() +"\"" +  ", \n"  +
					                "} \n"+
					                "} }"
					                
					                
					                );
		}
		else{
			 PrintWriter out = resp.getWriter();
			    String title = "CreateListing_Response";
			    
			    		  out.println(
					                
			    				  "{"+  "\"" + title +"\"" +  ":   { \n" +
			    				
									"\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "fail " + errorname + " "  + "\"" + "\n"  +  
								   "} }"
					                
					                );
			
		}
	}

	public void doPost(HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		boolean error = false;
		String errorname= "";
		Listing pj= new Listing();
		DatastoreService dstore = DatastoreServiceFactory.getDatastoreService();

		/**
		 * Checking to see if the listing id already exists in the database
		 */
		Query q = new Query("id");
		PreparedQuery p = dstore.prepare(q);

		for (Entity e: p.asIterable()){
			if (e.getProperty("id").equals(req.getParameter("id")))
				error = true;
			errorname = "ListingID used";
		}

		if(!error){

			pj.setUser(req.getParameter("user"));
			pj.setListingType(req.getParameter("listingType"));
			pj.setOrigin(req.getParameter("origin"));
			pj.setDestination(req.getParameter("destination"));
			pj.setDate(req.getParameter("date"));
			pj.setSeats(req.getParameter("seats"));
			pj.setCar(req.getParameter("car"));
			pj.setPrice(req.getParameter("price"));
			pj.setTransAuto(req.getParameter("transAuto"));
			pj.setTransMan(req.getParameter("transMan"));
			pj.setBags(req.getParameter("bags"));
			pj.setSharedDriving(req.getParameter("sharedDriving"));

			pj.upData();


			PrintWriter out = resp.getWriter();
			String title = "Register_listing" ;
			out.println(

					"{"+  "\"" + title +"\"" +  ":   { \n" +

		  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "successful		" +"\"" + "\n"  + 
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
