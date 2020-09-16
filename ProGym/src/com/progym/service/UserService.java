package com.progym.service;

import java.util.List;

import com.progym.model.AddClientPackageForm;
import com.progym.model.AddMemberObject;
import com.progym.model.AddPackageObject;
import com.progym.model.CPackage;
import com.progym.model.Client;
import com.progym.model.CollectionDashboardPVO;
import com.progym.model.CollectionPVO;
import com.progym.model.FemaleMemberAdditionalDataVO;
import com.progym.model.FilterCollectionObject;
import com.progym.model.MemberStatPVO;
import com.progym.model.Notifications;
import com.progym.model.User;
import com.progym.model.PackageDetails;
import com.progym.model.PaymentDataPVO;
import com.progym.model.PaymentTransaction;
import com.progym.model.ReferenceVO;

public interface UserService {

	User validateUser(User login);

	void register();

	void addMemberToDatabase(AddMemberObject addMemberObject , User user , String userType);

	public List<MemberStatPVO> getMembersBy(String filter,String zone, String enableDisable);

	void addPackageToDatabase(AddPackageObject addPackageObject ,  User user);

	List<CPackage> getPackagesByFilter(String string);

	void addPackageForClientToDatabase(AddClientPackageForm addClientPackageForm , User user);

	Client getClientById(int parseInt);

	void createTransaction(PaymentTransaction paymentTransaction, Boolean isAuthorized , User user);

	List<PackageDetails> getClientPackagesByClient(Client client);

	List<PaymentDataPVO> getPaymentData(String string, String gender);

	List<CollectionPVO> getCollectionBy(FilterCollectionObject filter);

	CollectionDashboardPVO getDashboardCollection();

	void approveTransaction(String txnId);

	void updateClintAssignedPackage(String u_pkgId, String u_startdate,String u_enddate, String u_fees , User user);

	void deleteClintAssignedPackage(String u_pkgId , User user);

	List<ReferenceVO> getReferenceList();

	List<Notifications> getNotifications(User user);

	void discardNotification(String notiId);

	void submitFemaleAditionalDataForm(FemaleMemberAdditionalDataVO femaleMemberAdditionalDataVO, User u);

	List<FemaleMemberAdditionalDataVO> getFemaleAditionalDataListByClientId(int clientId);

	void updateMemberToDatabase(Client client, User u);

	void deleteClientProfile(String clientid, User user);

	void deletePackage(String pkgid, User user);

	void deleteFemaleClientAdditionalDetails(String id, User u);

	public List<MemberStatPVO> getMembersBySearchCriteria(String searchCriteria);

	List<Notifications> getMobileNotifications();

	void sendPendingInvoices();

	void sendInvoice(String txnId, String email);

	void updateToggleInvoiceFlag(String flag);

	String getToggleInvoiceFlag();

	String getReferralName(String cliendId);

	void redeemReferPoints(String clientid);

	void updateProfileActiveFlag(String clientid, String selectflag);

	void triggerEnableDisableProfileBatch();

	void sendFeesReminder(String clientid);

	public List<String> getTodaysBirthdays();

	void sendBdayWish(String name);

	void createNewEmail(String emailSubject, String receiver);

	void createNewSms(String smsContent, String receiver);

	String getSmsFlag();

	void updateSmsFlag(String flag);

	void sendReminderToSingleClient(String clientname, String clientid, String daysLeft, String packageName,
			String packageDuration, String pendingFees, String feesPaid, String packageTotalFees);

	void triggerFeesPaymentReminderBatch();
	

}
