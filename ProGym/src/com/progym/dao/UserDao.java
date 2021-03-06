package com.progym.dao;

import java.util.List;

import com.progym.model.*;

public interface UserDao {
	
	  void register();
	  
	  User validateUser(User login);

	void addMemberToDatabase(AddMemberObject addMemberObject , User user, String userType);

	public List<MemberStatPVO> getMembersBy(String filter , String zone, String enableDisable);

	void addPackageToDatabase(AddPackageObject addPackageObject , User user);

	List<CPackage> getPackagesByFilter(String filter);

	void addPackageForClientToDatabase(AddClientPackageForm addClientPackageForm , User user);

	Client getClientById(int clientId);

	CPackage getPackageById(int id);

	void createTransaction(PaymentTransaction paymentTransaction, Boolean isAuthorized, User user);
	
	List<PackageDetails> getClientPackagesByClient(Client client);

	List<PaymentDataPVO> getPaymentData(String type, String gender);

	List<CollectionPVO> getCollectionBy(FilterCollectionObject filter);

	CollectionDashboardPVO getDashboardCollection();

	void approveTransaction(String txnId);

	void updateClientAssignedPackage(String u_pkgId, String u_startdate,String u_enddate, String u_fees , User user);

	void deleteClientAssignedPackage(String u_pkgId , User user);

	List<ReferenceVO> getReferenceList();

	List<Notifications> getNotifications(User user);

	void discardNotification(String notiId);

	void submitFemaleAditionalDataForm(FemaleMemberAdditionalDataVO femaleMemberAdditionalDataVO, User u);

	List<FemaleMemberAdditionalDataVO> getFemaleAditionalDataListByClientId(int clientId);

	void updateMemberToDatabase(Client client, User u);

	void deleteClientProfile(String clientid, User user);

	void deletePackage(String pkgid, User user);

	void deleteFemaleClientAdditionalDetails(String id, User u);

	List<MemberStatPVO> getMembersBySearchCriteria(String searchCriteria);

	List<Notifications> getMobileNotifications();

	void sendPendingInvoices();

	void sendInvoice(String txnId, String email);

	String getToggleInvoiceFlag();

	void updateToggleInvoiceFlag(String flag);

	String getReferralName(String cliendId);

	void redeemReferPoints(String clientid);

	void updateProfileActiveFlag(String clientid, String selectflag);

	void triggerEnableDisableProfileBatch();

	void sendFeesReminder(String clientid);
	
	public List<String> getTodaysBirthdays();

	void sendBdayWish(String name);

	void createNewEmail(String emailSubject, String receiver, String image);

	void createNewSms(String smsContent, String receiver);

	String getSmsFlag();

	void updateSmsFlag(String flag);

	void sendReminderToSingleClient(String clientname, String clientid, String daysLeft, String packageName,
			String packageDuration, String pendingFees, String feesPaid, String packageTotalFees);

	void triggerFeesPaymentReminderBatch();

	List<SmsLogs> getSmsLogs();

	void renewPackage(String clientid, User user);

    void updatePhotoInfo(Integer cid, String uploadedImagePath);

    void updateSmsDeliveryStatus(String id);

	List<SmsLogs> getSmsByFilter(String gender);

	BrandImages getImageObjectByBrand(String brandName);
}