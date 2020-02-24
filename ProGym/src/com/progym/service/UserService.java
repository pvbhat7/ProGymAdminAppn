package com.progym.service;

import com.progym.model.Client;
import com.progym.model.Login;

public interface UserService {

	Client validateUser(Login login);

	void register(Client client);

}
