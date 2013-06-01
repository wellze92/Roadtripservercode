package com.inno.innowebservices;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class InnoWebservicesServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		 PrintWriter out = resp.getWriter();
		    String title = "Reading Three Request Parameters";
		    out.println(
		                "<BODY>\n" +
		                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
		                "<UL>\n" +
		                "  <LI>param1: "
		                + req.getParameter("user") + "\n" +
		                "  <LI>param2: "
		                + req.getParameter("origin") + "\n" +
		                "  <LI>param3: "
		                + req.getParameter("dest") + "\n" +
		                "</UL>\n" +
		                "</BODY></HTML>");
	}
	
	  public void doPost(HttpServletRequest request,
              HttpServletResponse response)
throws ServletException, IOException {
doGet(request, response);
}
}
