package com.progym.dao;

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

public interface UserDao {
	
	  void register();
	  
	  User validateUser(User login);

	void addMemberToDatabase(AddMemberObject addMemberObject , User user, String userType);

	public List<MemberStatPVO> getMembersBy(String filter , String zone);

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

	List<Notifications> getNotifications();

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
	  
	}
