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

public class ListingServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
	
	
	
	
		 PrintWriter out = resp.getWriter();
		    
		    		  out.println(
				                
		    				"Hello World"
				                
				                );

	

	  
		    
	}
	
	public void doPost(HttpServletRequest req,
              HttpServletResponse resp)
throws ServletException, IOException {

		 PrintWriter out = resp.getWriter();
		    
		    		  out.println(
				                
		    				"Hello World"
				                
				                );

}
	
}
