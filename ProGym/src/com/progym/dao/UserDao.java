package com.progym.dao;

import com.progym.model.AddMemberObject;
import com.progym.model.Client;
import com.progym.model.Login;

public interface UserDao {
	
	  void register(Client client);
	  
	  Client validateUser(Login login);

	void addMemberToDatabase(AddMemberObject addMemberObject);
	  
	}
