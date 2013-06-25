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

public class RequestServlet extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");

		String reqID = req.getParameter("reqid");

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

	public void doPost(HttpServletRequest req,
			HttpServletResponse resp)
					throws ServletException, IOException {


		Request pj= new Request();
		DatastoreService dstore = DatastoreServiceFactory.getDatastoreService();

		/**
		 * Checking to see if the listing id already exists in the database
		 */


		//		for (Entity e: p.asIterable()){
		//			if (e.getProperty("").equals(req.getParameter("user")))
		//				error = true;
		//			errorname = "ListingID used";
		//		}

		pj.setReqID(req.getParameter("reqID"));
		pj.setUser(req.getParameter("user"));
		pj.setAccept(req.getParameter("accept"));
		pj.setListID(req.getParameter("ListId"));
		//			pj.setLongi(req.getParameter("long"));

		pj.upData();


		PrintWriter out = resp.getWriter();
		String title = "RequestPost_Response" ;
		out.println(

				"{"+  "\"" + title +"\"" +  ":   { \n" +

		  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "successful		" +"\"" + "\n"  + 
		  		    		   "} }"

				);


	}

}
