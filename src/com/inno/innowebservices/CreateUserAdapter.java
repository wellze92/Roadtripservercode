package com.inno.innowebservices;

import com.inno.innowebservices.jaxws.CreateUser;
import com.inno.innowebservices.jaxws.CreateUserResponse;
import com.inno.innowebservices.jaxws.GetUser;
import com.inno.innowebservices.jaxws.GetUserResponse;

public class CreateUserAdapter {
	WebservicesDemo wd = new WebservicesDemo();
	
public CreateUserResponse createUser(CreateUser request){
	String id = request.getArg0();
	String name = request.getArg1();
	String password = request.getArg2();
	String email = request.getArg3();
	String date = request.getArg4();
	boolean b = wd.createUser(id, name, password, email, date);
	CreateUserResponse res = new CreateUserResponse();
	res.setReturn(b);
	return res;
}


public GetUserResponse geruser(GetUser request){
	String kind = request.getArg0();
	UserPojo up = wd.getUser(kind);
	GetUserResponse res = new GetUserResponse();
	res.setReturn(up);
	return res;
}
}
