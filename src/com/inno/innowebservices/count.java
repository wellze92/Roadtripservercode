package com.inno.innowebservices;

import java.io.IOException;
import java.io.PrintWriter;

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

public class count extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");

	//	String comment = req.getParameter("id");
		
		//Key k = KeyFactory.createKey("ListId", comment);
		//Comment list = Utils.getComment(k);
		
		
		DatastoreService dstore = DatastoreServiceFactory.getDatastoreService();

		/**
		 * Checking to see if the listing actually exists in the database
		 */
		Query q = new Query("Listid");
		PreparedQuery p = dstore.prepare(q);
		String comments = "";

		int count = 0;
	for (Entity e: p.asIterable()){
			count++;
	 
			
		}
	String c = 	new String ("" + count);
	
	
			 PrintWriter out = resp.getWriter();
			    String title = "listing_num";
			    
			    		  out.println(
					                
			    				  "{"+  "\"" + title +"\"" +  ":   { \n" +
			    				"\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "success" +"\"" + ", \n"  +
			    						  
					                
					                "\t"+ "\"" + "Count" +"\"" + ": { \n" +
					                "\t\t"+  "\"" + "count" +"\"" + ": "    + c +
					                "\n" +
					          
					                " } \n"+
					                "} }"
					                
					                
					                );
		
		
	}


}
