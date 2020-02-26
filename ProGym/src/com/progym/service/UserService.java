package com.progym.service;

import java.util.List;

import com.progym.model.AddMemberObject;
import com.progym.model.AddPackageObject;
import com.progym.model.Client;
import com.progym.model.Login;

public interface UserService {

	Client validateUser(Login login);

	void register(Client client);

	void addMemberToDatabase(AddMemberObject addMemberObject);

	public List<Client> getMembersBy(String filter);

	void addPackageToDatabase(AddPackageObject addPackageObject);

}
