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
import com.progym.model.MemberStatPVO;
import com.progym.model.Notifications;
import com.progym.model.User;
import com.progym.model.PackageDetails;
import com.progym.model.PaymentDataPVO;
import com.progym.model.PaymentTransaction;
import com.progym.model.ReferenceVO;

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
	public void addMemberToDatabase(AddMemberObject addMemberObject , User user) {
	
		userDao.addMemberToDatabase(addMemberObject , user);
		
	}

	@Override
	public List<MemberStatPVO> getMembersBy(String filter , String zone) {
		return userDao.getMembersBy(filter , zone);
		
	}

	@Override
	public void addPackageToDatabase(AddPackageObject addPackageObject , User user) {
		userDao.addPackageToDatabase(addPackageObject , user);
		
	}

	@Override
	public List<CPackage> getPackagesByFilter(String filter) {
		return userDao.getPackagesByFilter(filter);
		
	}

	@Override
	public void addPackageForClientToDatabase(AddClientPackageForm addClientPackageForm , User user) {
		userDao.addPackageForClientToDatabase(addClientPackageForm , user);
		
	}

	@Override
	public Client getClientById(int clientId) {
		return userDao.getClientById(clientId);
	}

	@Override
	public void createTransaction(PaymentTransaction paymentTransaction , Boolean isAuthorized , User user) {
		userDao.createTransaction(paymentTransaction ,isAuthorized , user);
		
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

	@Override
	public void updateClintAssignedPackage(String u_pkgId, String u_startdate,String u_enddate, String u_fees , User user) {
		userDao.updateClientAssignedPackage(u_pkgId,u_startdate,u_enddate,u_fees ,user);
		
	}
	
	@Override
	public void deleteClintAssignedPackage(String u_pkgId ,User user) {
		userDao.deleteClientAssignedPackage(u_pkgId , user);
	}
	
	@Override
	public List<ReferenceVO> getReferenceList() {
		return userDao.getReferenceList();
	}
	
	@Override
	public List<Notifications> getNotifications() {
	return 	userDao.getNotifications();
	}
	
	@Override
	public void discardNotification(String notiId) {
		userDao.discardNotification(notiId);		
	}
	

}
