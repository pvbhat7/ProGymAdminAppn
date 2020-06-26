package com.progym.service;

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

public interface UserService {

	User validateUser(User login);

	void register();

	void addMemberToDatabase(AddMemberObject addMemberObject);

	public List<MemberStatPVO> getMembersBy(String filter,String zone);

	void addPackageToDatabase(AddPackageObject addPackageObject);

	List<CPackage> getPackagesByFilter(String string);

	void addPackageForClientToDatabase(AddClientPackageForm addClientPackageForm);

	Client getClientById(int parseInt);

	void createTransaction(PaymentTransaction paymentTransaction, Boolean isAuthorized);

	List<PackageDetails> getClientPackagesByClient(Client client);

	List<PaymentDataPVO> getPaymentData(String string, String gender);

	List<CollectionPVO> getCollectionBy(FilterCollectionObject filter);

	CollectionDashboardPVO getDashboardCollection();

	void approveTransaction(String txnId);

	void updateClintAssignedPackage(String u_pkgId, String u_startdate,String u_enddate, String u_fees);

	void deleteClintAssignedPackage(String u_pkgId);

	List<ReferenceVO> getReferenceList();


	

}
