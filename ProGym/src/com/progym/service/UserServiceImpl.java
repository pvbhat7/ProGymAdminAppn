package com.progym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progym.dao.UserDao;
import com.progym.model.AddClientPackageForm;
import com.progym.model.AddMemberObject;
import com.progym.model.AddPackageObject;
import com.progym.model.CPackage;
import com.progym.model.Client;
import com.progym.model.Login;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public Client validateUser(Login login) {
		return userDao.validateUser(login);
	}

	@Override
	public void register(Client client) {
		userDao.register(client);
		
	}

	@Override
	public void addMemberToDatabase(AddMemberObject addMemberObject) {
	
		userDao.addMemberToDatabase(addMemberObject);
		
	}

	@Override
	public List<Client> getMembersBy(String filter) {
		return userDao.getMembersBy(filter);
		
	}

	@Override
	public void addPackageToDatabase(AddPackageObject addPackageObject) {
		userDao.addPackageToDatabase(addPackageObject);
		
	}

	@Override
	public List<CPackage> getPackagesByFilter(String filter) {
		return userDao.getPackagesByFilter(filter);
		
	}

	@Override
	public void addPackageForClientToDatabase(AddClientPackageForm addClientPackageForm) {
		userDao.addPackageForClientToDatabase(addClientPackageForm);
		
	}

	@Override
	public Client getClientById(int clientId) {
		return userDao.getClientById(clientId);
	}

}
