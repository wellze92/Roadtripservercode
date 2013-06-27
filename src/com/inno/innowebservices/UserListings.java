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

/**
 * Servlet that handles HTTP requests that will allow one to view a given user's current listings.
 * 
 * @author Micah Cinco
 *
 */
@SuppressWarnings("serial")
public class UserListings extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");

		String user = req.getParameter("user");

		DatastoreService dstore = DatastoreServiceFactory.getDatastoreService();

		Query q = new Query("Listid");
		PreparedQuery p = dstore.prepare(q);

		PrintWriter out = resp.getWriter();
		String title = "GetAllUserListings_Response";

		int count = 0;

		for (Entity e: p.asIterable()){
			if (e.getProperty("user").toString().equals(user))
				count = count+1;
		}

		if (count == 0){
			String errorname = "This user has no current listings.";

			out.println(

					"{"+ "\"" + title +"\"" + ": { \n" +

					"\t"+ "\"" + "status" +"\"" + ":" + "\"" + "fail: " + errorname + " " + "\"" + "\n" +
					"} }"

					);
		}
		else {
			out.println(

					"{"+ "\"" + title +"\"" + ": { \n" +
							"\t"+ "\"" + "Count" +"\"" + ":" + "\"" + count +"\"" + ", \n"
					);

			for (Entity e: p.asIterable()){
				if (e.getProperty("user").toString().equals(user)) {

					out.println(

							"\t"+ "\"" + "Listing" +"\"" + ": { \n" +
									"\t\t"+ "\"" + "ID" +"\"" + ":" + "\"" + e.getProperty("ListId").toString() +"\"" + ", \n" +
									"\t\t"+ "\"" + "Owner" +"\"" + ":" + "\"" + e.getProperty("user").toString() +"\"" + ", \n" +
									"\t\t"+ "\"" + "Origin" +"\"" + ":" + "\"" + e.getProperty("origin").toString() +"\"" + ", \n" +
									"\t\t"+ "\"" + "Destination" +"\"" + ":" + "\"" + e.getProperty("destination").toString() +"\"" + ", \n"+
									"\t\t"+ "\"" + "Date" +"\"" + ":" + "\"" + e.getProperty("date").toString() +"\"" +", \n"+
									"\t\t"+ "\"" + "Time" +"\"" + ":" + "\"" + e.getProperty("time").toString() +"\"" +", \n"+
									"\t\t"+ "\"" + "Seats" +"\"" + ":" + "\"" + e.getProperty("seats").toString() +"\"" +", \n" +
									"\t\t"+ "\"" + "Car" +"\"" + ":" + "\"" + e.getProperty("carType").toString() +"\"" +", \n" +
									"\t\t"+ "\"" + "Price" +"\"" + ":" + "\"" + e.getProperty("price").toString() +"\"" + " \n" +
									"\t\t"+ "\"" + "TransMissionAuto" +"\"" + ":" + "\"" + e.getProperty("transAuto").toString() +"\"" +", \n" +
									"\t\t"+ "\"" + "TransMissionManual" +"\"" + ":" + "\"" + e.getProperty("transMan").toString() +"\"" +", \n" +
									"\t\t"+ "\"" + "Bags" +"\"" + ":" + "\"" + e.getProperty("bags").toString() +"\"" + ", \n" +
									"\t\t"+ "\"" + "SharedDriving" +"\"" + ":" + "\"" + e.getProperty("sharedDriving").toString() +"\"" + ", \n" +
									"\t\t"+ "\"" + "Image1" +"\"" + ":" + "\"" + e.getProperty("image1").toString() +"\"" + ", \n" +
									"\t\t"+ "\"" + "Image2" +"\"" + ":" + "\"" + e.getProperty("image2").toString() +"\"" + ", \n" +
									"\t\t"+ "\"" + "Comments" +"\"" + ":" + "\"" + e.getProperty("listcomment").toString() +"\"" + "\n" +
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
