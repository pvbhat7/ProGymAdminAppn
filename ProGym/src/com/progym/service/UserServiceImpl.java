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
import com.progym.model.CollectionDashboardPVO;
import com.progym.model.CollectionPVO;
import com.progym.model.FilterCollectionObject;
import com.progym.model.User;
import com.progym.model.PackageDetails;
import com.progym.model.PaymentDataPVO;
import com.progym.model.PaymentTransaction;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public User validateUser(User user) {
		return userDao.validateUser(user);
	}

	@Override
	public void register() {
		userDao.register();
		
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

	@Override
	public void createTransaction(PaymentTransaction paymentTransaction) {
		userDao.createTransaction(paymentTransaction);
		
	}

	@Override
	public List<PackageDetails> getClientPackagesByClient(Client client) {
		
		return userDao.getClientPackagesByClient(client);
	}

	@Override
	public List<PaymentDataPVO> getPaymentData(String type,String gender) {
		return userDao.getPaymentData(type,gender);
	}

	@Override
	public List<CollectionPVO> getCollectionBy(FilterCollectionObject filter) {
		return userDao.getCollectionBy(filter);
	}
	
	@Override
	public CollectionDashboardPVO getDashboardCollection() {
		return userDao.getDashboardCollection();
	}

	@Override
	public void approveTransaction(String txnId) {

		userDao.approveTransaction(txnId);
		
	}
	

}
