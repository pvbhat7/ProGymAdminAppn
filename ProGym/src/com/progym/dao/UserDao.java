package com.progym.dao;

import java.util.List;

import com.progym.model.AddClientPackageForm;
import com.progym.model.AddMemberObject;
import com.progym.model.AddPackageObject;
import com.progym.model.CPackage;
import com.progym.model.Client;
import com.progym.model.CollectionDashboardPVO;
import com.progym.model.CollectionPVO;
import com.progym.model.FilterCollectionObject;
import com.progym.model.MemberStatPVO;
import com.progym.model.User;
import com.progym.model.PackageDetails;
import com.progym.model.PaymentDataPVO;
import com.progym.model.PaymentTransaction;
import com.progym.model.ReferenceVO;

public interface UserDao {
	
	  void register();
	  
	  User validateUser(User login);

	void addMemberToDatabase(AddMemberObject addMemberObject);

	public List<MemberStatPVO> getMembersBy(String filter , String zone);

	void addPackageToDatabase(AddPackageObject addPackageObject);

	List<CPackage> getPackagesByFilter(String filter);

	void addPackageForClientToDatabase(AddClientPackageForm addClientPackageForm);

	Client getClientById(int clientId);

	CPackage getPackageById(int id);

	void createTransaction(PaymentTransaction paymentTransaction, Boolean isAuthorized);
	
	List<PackageDetails> getClientPackagesByClient(Client client);

	List<PaymentDataPVO> getPaymentData(String type, String gender);

	List<CollectionPVO> getCollectionBy(FilterCollectionObject filter);

	CollectionDashboardPVO getDashboardCollection();

	void approveTransaction(String txnId);

	void updateClientAssignedPackage(String u_pkgId, String u_startdate,String u_enddate, String u_fees);

	void deleteClientAssignedPackage(String u_pkgId);

	List<ReferenceVO> getReferenceList();
	  
	}
