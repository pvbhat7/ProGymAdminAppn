package com.progym.dao;

import com.progym.model.Client;
import com.progym.model.Login;

public interface UserDao {
	
	  void register(Client client);
	  
	  Client validateUser(Login login);
	  
	}
