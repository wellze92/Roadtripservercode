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
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

/**
 * Servlet that handles HTTP requests to do with the request object.
 * Allows one to create a request and view it.
 * 
 * @author Micah Cinco
 *
 */
@SuppressWarnings("serial")
public class RequestServlet extends HttpServlet {

	/**
	 * Handles GET requests that allows user to view the request.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");

		String reqID = req.getParameter("reqID");

		Key k = KeyFactory.createKey("reqID", reqID);
		Request request = Utils.getRequest(k);

		boolean exist = false;
		String errorname = "Request ID does not exist.";
		DatastoreService dstore = DatastoreServiceFactory.getDatastoreService();

		/**
		 * Checking to see if the listing actually exists in the database
		 */
		Query q = new Query("reqID");
		PreparedQuery p = dstore.prepare(q);

		for (Entity e: p.asIterable()){
			if (e.getProperty("reqID").equals(req.getParameter("reqID"))){
				exist = true;
				break;
			}
		}

		if (exist) {
			PrintWriter out = resp.getWriter();
			String title = "getRequestResponse";

			out.println(

					"{"+ "\"" + title +"\"" + ": { \n" +
							"\t"+ "\"" + "status" +"\"" + ":" + "\"" + "success" +"\"" + ", \n" +


							"\t"+ "\"" + "Request" +"\"" + ": { \n" +
							"\t\t"+ "\"" + "reqID" +"\"" + ":" + "\"" + request.getReqID() +"\"" + ", \n" +
							"\t\t"+ "\"" + "user" +"\"" + ":" + "\"" + request.getUser() +"\"" + ", \n" +
							"\t\t"+ "\"" + "accepted" +"\"" + ":" + "\"" + request.getAccept() +"\"" + ", \n" +
							"\t\t"+ "\"" + "listID" +"\""+ ":" + "\"" + request.getListID() +"\"" + ", \n" +
							"} \n"+
							"} }"
					);
		}
		else {
			PrintWriter out = resp.getWriter();
			String title = "getRequestResponse";

			out.println(

					"{"+ "\"" + title +"\"" + ": { \n" +

					"\t"+ "\"" + "status" +"\"" + ":" + "\"" + "fail: " + errorname + " " + "\"" + "\n" +
					"} }"

					);

		}

	}

	/**
	 * Handles POST requests that allows a user to create a request (if it doesn't already exist),
	 * or accept a request (change accept -> true if user is the listing owner)
	 */
	public void doPost(HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {

		boolean exist = false;
		boolean isOwner = false;
		String errorname= "Unauthorized overwrite.";
		Request r= new Request();
		DatastoreService dstore = DatastoreServiceFactory.getDatastoreService();
		Filter f1 = new FilterPredicate("reqID", FilterOperator.EQUAL, req.getParameter("reqID"));
		Filter f2 = new FilterPredicate("user", FilterOperator.EQUAL, req.getParameter("user"));

		/**
		 * Querying two different datastore tables 
		 */
		Query q1 = new Query("reqID");
		q1.setFilter(f1);
		Iterable<Entity> reqids = dstore.prepare(q1).asIterable();


		Query q2 = new Query("Listid");
		q2.setFilter(f2);
		Iterable<Entity> listUsers = dstore.prepare(q2).asIterable();
		
		String user = "";
		int seats = 0;
		String s = "";
		
		for (Entity e: reqids){
			if (e.getProperty("reqID").toString().equals(req.getParameter("reqID"))) {
				exist = true;
				for(Entity ent: listUsers){
					if (e.getProperty("ListId").equals(ent.getProperty("ListId"))){
						if (req.getParameter("user").equals(ent.getProperty("user"))){
							if (req.getParameter("accept").equals("true")){
								seats = Integer.parseInt(ent.getProperty("seats").toString());
								if (seats < 0){
									errorname = "No more seats available";
									break;
								}
								else if (seats > 0){
									Key k = KeyFactory.createKey("Listid", req.getParameter("ListId"));
									Listing list = Utils.getListing(k);
									
									user = e.getProperty("user").toString();
									isOwner = true;
									--seats; s = ""+seats;
									list.setSeats(s);
									list.upData();
									break;
								}
							}
						}
						else {
							errorname = "Unauthorized access";
							break;
						}
					}
				}
			}
			else if (!e.getProperty("reqID").toString().equals(req.getParameter("reqID"))){
				errorname = "reqID used";
				break;
			}
		}

		if(isOwner){

			r.setAccept((req.getParameter("accept")));
			r.setListID(req.getParameter("ListId"));
			r.setReqID(req.getParameter("reqID"));
			r.setUser(user);
			r.upData();

			PrintWriter out = resp.getWriter();
			String title = "Accept_RequestResponse" ;
			out.println(

					"{"+  "\"" + title + "\"" +  ":   { \n" +

		  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "successful: " +"\"" + "\n"  + 
		  		    		   "} }"

					);
		}
		else if (!exist){
			r.setReqID(req.getParameter("reqID"));
			r.setUser(req.getParameter("user"));
			r.setListID(req.getParameter("ListId"));

			r.upData();


			PrintWriter out = resp.getWriter();
			String title = "Post_RequestResponse" ;
			out.println(

					"{"+  "\"" + title + "\"" +  ":   { \n" +

		  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "successful		" +"\"" + "\n"  + 
		  		    		   "} }"

					);

		}
		else{
			PrintWriter out = resp.getWriter();
			String title = "Post_RequestResponse" ;
			out.println(

					"{"+  "\"" + title +"\"" +  ":   { \n" +

		  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "fail: " + errorname + " "  + "\"" + "\n"  + 
		  		    		   "} }"

					);
		}
	}

}
