package com.progym.service;

import java.util.List;

import com.progym.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progym.dao.UserDao;

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
	public void addMemberToDatabase(AddMemberObject addMemberObject , User user , String userType) {
	
		userDao.addMemberToDatabase(addMemberObject , user , userType);
		
	}

	@Override
	public List<MemberStatPVO> getMembersBy(String filter , String zone , String enableDisable) {
		return userDao.getMembersBy(filter , zone , enableDisable);
		
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
	public List<Notifications> getNotifications(User user) {
	return 	userDao.getNotifications(user);
	}
	
	@Override
	public void discardNotification(String notiId) {
		userDao.discardNotification(notiId);		
	}
	
	@Override
	public void submitFemaleAditionalDataForm(FemaleMemberAdditionalDataVO femaleMemberAdditionalDataVO, User u) {
		userDao.submitFemaleAditionalDataForm(femaleMemberAdditionalDataVO, u);		
	}
	
	@Override
	public List<FemaleMemberAdditionalDataVO> getFemaleAditionalDataListByClientId(int clientId) {
		return userDao.getFemaleAditionalDataListByClientId(clientId);
	}
	
	@Override
	public void updateMemberToDatabase(Client client, User u) {
		userDao.updateMemberToDatabase(client , u);		
	}
	
	@Override
	public void deleteClientProfile(String clientid, User user) {
		userDao.deleteClientProfile(clientid , user);
	}
	
	@Override
	public void deletePackage(String pkgid, User user) {
		userDao.deletePackage(pkgid , user);		
	}
	
	@Override
	public void deleteFemaleClientAdditionalDetails(String id, User u) {
		userDao.deleteFemaleClientAdditionalDetails(id,u);
	}
	
	@Override
	public List<MemberStatPVO> getMembersBySearchCriteria(String searchCriteria) {
		return userDao.getMembersBySearchCriteria(searchCriteria);
	}
	
	@Override
	public List<Notifications> getMobileNotifications() {
		return userDao.getMobileNotifications();
	}
	
	@Override
	public void sendPendingInvoices() {
		userDao.sendPendingInvoices();
	}

	@Override
	public void sendInvoice(String txnId,String email) {
		userDao.sendInvoice(txnId,email);
	}
	
	@Override
	public String getToggleInvoiceFlag() {
		return userDao.getToggleInvoiceFlag();
	}
	
	@Override
	public void updateToggleInvoiceFlag(String flag) {
		userDao.updateToggleInvoiceFlag(flag);
	}

	@Override
	public String getReferralName(String cliendId) {
		return userDao.getReferralName(cliendId);
	}

	@Override
	public void redeemReferPoints(String clientid) {
		userDao.redeemReferPoints(clientid);		
	}
	
	@Override
	public void updateProfileActiveFlag(String clientid, String selectflag) {
		userDao.updateProfileActiveFlag(clientid,selectflag);
	}
	
	@Override
	public void triggerEnableDisableProfileBatch() {
		userDao.triggerEnableDisableProfileBatch();
	}
	
	@Override
	public void sendFeesReminder(String clientid) {
		userDao.sendFeesReminder(clientid);
	}

	@Override
	public List<String> getTodaysBirthdays() {
		return userDao.getTodaysBirthdays();
	}

	@Override
	public void sendBdayWish(String name) {
		userDao.sendBdayWish(name);	
	}
	
	@Override
	public void createNewEmail(String emailSubject, String receiver,String image) {
		userDao.createNewEmail(emailSubject , receiver,image);		
	}
	
	@Override
	public void createNewSms(String smsContent, String receiver) {
		userDao.createNewSms(smsContent , receiver);
	}
	
	@Override
	public String getSmsFlag() {
		return userDao.getSmsFlag();
	}
	
	@Override
	public void updateSmsFlag(String flag) {
		userDao.updateSmsFlag(flag);
	}
	
	@Override
	public void sendReminderToSingleClient(String clientname, String clientid, String daysLeft, String packageName,
			String packageDuration, String pendingFees, String feesPaid, String packageTotalFees) {
		userDao.sendReminderToSingleClient(clientname,clientid,daysLeft,packageName,packageDuration,pendingFees,feesPaid,packageTotalFees);
	}
	
	@Override
	public void triggerFeesPaymentReminderBatch() {
		userDao.triggerFeesPaymentReminderBatch();	
	}
	
	@Override
	public List<SmsLogs> getSmsLogs() {
		return userDao.getSmsLogs();
	}
	
	@Override
	public void renewPackage(String clientid , User user) {
		userDao.renewPackage(clientid , user);
	}

	@Override
	public void updatePhotoInfo(Integer cid, String uploadedImagePath) {
		userDao.updatePhotoInfo(cid,uploadedImagePath);
	}

	@Override
	public void updateSmsDeliveryStatus(String id) {
		userDao.updateSmsDeliveryStatus(id);
	}

	@Override
	public List<SmsLogs> getSmsByFilter(String gender) {
		return userDao.getSmsByFilter(gender);
	}

	@Override
	public BrandImages getImageObjectByBrand(String brandName) {
		return userDao.getImageObjectByBrand(brandName);
	}
}
