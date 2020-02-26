package com.progym.dao;

import java.util.List;

import com.progym.model.AddMemberObject;
import com.progym.model.AddPackageObject;
import com.progym.model.Client;
import com.progym.model.Login;

public interface UserDao {
	
	  void register(Client client);
	  
	  Client validateUser(Login login);

	void addMemberToDatabase(AddMemberObject addMemberObject);

	public List<Client> getMembersBy(String filter);

	void addPackageToDatabase(AddPackageObject addPackageObject);
	  
	}
