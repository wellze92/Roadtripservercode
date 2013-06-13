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
	String Username = req.getParameter("us");
	String pass = req.getParameter("ps");
	
	Key k = KeyFactory.createKey("id", Username);
	UserPojo up = Utils.getEntity(k);
	
	if (pass.equals(up.getPassword())){
		 PrintWriter out = resp.getWriter();
		    String title = "Log_in_response";
		    
		    		  out.println(
				                
		    				  "{"+  "\"" + title +"\"" +  ":   { \n" +
		    				"\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "suscuss" +"\"" + "\n"  +
		    						  
				                
				                "\t"+ "\"" + "user" +"\"" + ": { \n" +
				                "\t\t"+  "\"" + "user_name" +"\"" + ":" +  "\"" + up.getName() +"\"" + "\n"  +
				                "\t\t"+  "\"" + "email" +"\""+ ":" +   "\"" +  up.getEmail() +"\"" + "\n"  +
				                "\t\t"+  "\"" + "age" +"\"" +  ":" +  "\"" +  up.getAge() +"\"" + "\n"  +
				                "\t\t"+  "\"" + "gender" +"\"" +  ":" +  "\"" + up.getGender() +"\"" + "\n"+   
				                "\t\t"+  "\"" + "liscence" +"\"" +  ":" + "\"" + up.getLiscence() +"\"" +"\n"+  
				                "\t\t"+  "\"" + "smoker" +"\"" +  ":" +  "\"" + up.getSmoker() +"\"" +"\n"  +
				                "\t\t"+  "\"" + "Address" +"\"" +  ":" + "\"" + up.getAddress() +"\"" +"\n" + 
				                "\t\t"+  "\"" + "phone" +"\"" +  ":" +  "\"" + up.getPhone() +"\"" +  "\n"  +
				                "\t\t"+  "\"" + "music" +"\"" +  ":" + "\"" + up.getMusic() +"\"" +  "\n"  +
				                "\t\t"+  "\"" + "interest" +"\"" + ":" +  "\"" +up.getInterest() +"\"" +  "\n"+  
				                "\t\t"+  "\"" + "about" +"\"" +  ":" + "\"" + up.getAbout() +"\"" +  "\n" +
				                "} \n"+
				                "} }"
				                
				                
				                );
	}
	
	else{
		 PrintWriter out = resp.getWriter();
		    String title = "Log_in_response";
		    
		    		  out.println(
				                
		    				  "{"+  "\"" + title +"\"" +  ":   { \n" +
		    				
		  		    		   "\t"+  "\"" + "status" +"\"" + ":" +  "\"" + "fail		" +"\"" + "\n"  + 
							   "} }"
				                
				                );
		
	}
	  
		    
	}
	
	public void doPost(HttpServletRequest req,
              HttpServletResponse resp)
throws ServletException, IOException {
		  UserPojo pj = new UserPojo();
			pj.setName(req.getParameter("user"));
			pj.setPassword(req.getParameter("pass"));
			pj.setEmail(req.getParameter("email"));
			pj.setAge(req.getParameter("age"));
			pj.setGender(req.getParameter("gen"));
			pj.setLiscence(req.getParameter("lis"));
			pj.setSmoker(req.getParameter("smo"));
			pj.setAddress(req.getParameter("add"));
			pj.setPhone(req.getParameter("phone"));
			pj.setMusic(req.getParameter("music"));
			pj.setInterest(req.getParameter("int"));
			pj.setAbout(req.getParameter("about"));
			
			pj.upData();
			
			
			 PrintWriter out = resp.getWriter();
			    String title = "\"" + "user_response" +"\"" + ": { \n" ;
			    out.println(
			                
			                title +
			                
			                "\t"+ "\"" + "user" +"\"" + ": { \n" +
			                "\t\t"+  "\"" + "user_name" +"\"" +  "\"" + "" +"\"" +  
			                 req.getParameter("user") + "\n" +
			                "  <LI>param2: "
			                + req.getParameter("pass") + "\n" +
			                "  <LI>param3: "
			                + req.getParameter("email") + "\n" +
			                "  <LI>param4: "
			                + req.getParameter("age") + "\n" +
			                 req.getParameter("gen") + "\n" +
			                "  <LI>param5: "
			                + req.getParameter("lis") + "\n" +
			                "  <LI>param6: "
			                + req.getParameter("smo") + "\n" +
			                "  <LI>param7: "
			                + req.getParameter("add") + "\n" +
			                "  <LI>param8: "
			                + req.getParameter("phone") + "\n" +
			                "  <LI>param9: "
			                + req.getParameter("music") + "\n" +
			                "  <LI>param10: "
			                + req.getParameter("int") + "\n" +
			                "  <LI>param11: "
			                + req.getParameter("about") + "\n" +
			                
			                "</UL>\n" +
			                "</BODY></HTML>");
}
}
