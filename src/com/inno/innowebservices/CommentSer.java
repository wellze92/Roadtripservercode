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

/**
 * Handles and manages the http request to do with comments. It allows the user to make a comment or to get all of the comments
 * related to one listing
 * @author Andrew Wells
 *
 */
public class CommentSer extends HttpServlet {
	
/**
 * Deals with get request and returns all of the comments in a json format
 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		DatastoreService dstore = DatastoreServiceFactory.getDatastoreService();

		/**
		 *Gets all of the comments from the server
		 */
		Query q = new Query("Commentid");
		PreparedQuery p = dstore.prepare(q);
		String comments = "";

	for (Entity e: p.asIterable()){
			if (e.getProperty("ListId").equals(req.getParameter("id"))){
				// adds to the comment array
				comments = comments + "\t\t"+  "\"" + "comment" +"\"" + ":" +  "\"" + e.getProperty("comment") +"\"" + ", \n";
			}
	 
			
		}
		
	comments = comments.substring(0,comments.length()-3) + "\n"; // to keep JSON format
	
		// response to the client after 
			 PrintWriter out = resp.getWriter();
			    String title = "Comment_Get";
			    
			    		  out.println(
					                
			    				  "{"+  "\"" + title +"\"" +  ":   { \n" +
			    				"\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "success" +"\"" + ", \n"  +
			    						  
					                // all comments in the form of an array
					                "\t"+ "\"" + "Comments" +"\"" + ": { \n" +
					                comments +
					          
					                "} \n"+
					                "} }"
					                
					                
					                );
		
		
	}
 /**
  * Adds a comment to the server and links to the listing
  */
	public void doPost(HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		
		Comment pj= new Comment();
		DatastoreService dstore = DatastoreServiceFactory.getDatastoreService();

		/**
		 * Checking to see if the listing id already exists in the database
		 */
		

//		for (Entity e: p.asIterable()){
//			if (e.getProperty("").equals(req.getParameter("user")))
//				error = true;
//			errorname = "ListingID used";
//		}

			pj.setId(req.getParameter("id"));
			pj.setUser(req.getParameter("user"));
			pj.setComment(req.getParameter("comment"));
//			pj.setLongi(req.getParameter("long"));

			pj.upData();


			PrintWriter out = resp.getWriter();
			String title = "Comment_sent" ;
			out.println(

					"{"+  "\"" + title +"\"" +  ":   { \n" +

		  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "successful	" +"\"" + "\n"  + 
		  		    		   "} }"

					);
		
		
		}

}
