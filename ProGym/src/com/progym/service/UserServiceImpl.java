package com.progym.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.progym.dao.UserDao;
import com.progym.model.Client;
import com.progym.model.Login;


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

}
