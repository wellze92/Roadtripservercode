package com.inno.innowebservices;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class ListingRequests extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");

		String listid = req.getParameter("ListId");

		DatastoreService dstore = DatastoreServiceFactory.getDatastoreService();

		Query q = new Query("reqID");
		PreparedQuery p = dstore.prepare(q);

		PrintWriter out = resp.getWriter();
		String title = "GetAllRequests_Response";
		int count = 0;

		for (Entity e: p.asIterable()){
			if (e.getProperty("ListId").toString().equals(listid))
				count = count+1;
		}

		if (count == 0){
			String errorname = "No Requests for this listing.";

			out.println(

					"{"+ "\"" + title +"\"" + ": { \n" +

					"\t"+ "\"" + "status" +"\"" + ":" + "\"" + "fail: " + errorname + " " + "\"" + "\n" +
					"} }"

					);
		}
		else {
			out.println(

					"{"+ "\"" + title +"\"" + ": { \n" +
							"\t\t"+ "\"" + "Count" +"\"" + ":" + "\"" + count +"\"" + ", \n"
					);

			for (Entity e: p.asIterable()){
				if (e.getProperty("ListId").toString().equals(listid)) {

					out.println(

							"\t"+ "\"" + "Request" +"\"" + ": { \n" +
									"\t\t"+ "\"" + "RequestID" +"\"" + ":" + "\"" + e.getProperty("reqID").toString() +"\"" + ", \n" +
									"\t\t"+ "\"" + "User" +"\"" + ":" + "\"" + e.getProperty("user").toString() +"\"" + ", \n" +
									"\t\t"+ "\"" + "Accepted" +"\"" + ":" + "\"" + e.getProperty("accept").toString() +"\"" + ", \n" +
									"\t\t"+ "\"" + "ListingID" +"\""+ ":" + "\"" + e.getProperty("ListId").toString() +"\"" + ", \n" +
									"\t} \n"
							);
				}
			}

			out.println(
					"} \n"
					);
		}
	}

}
