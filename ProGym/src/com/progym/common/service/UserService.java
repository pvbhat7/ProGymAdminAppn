package com.progym.common.service;

import java.util.List;

import com.progym.common.model.*;

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

	String getReferralName(String cliendId);

	void redeemReferPoints(String clientid);

	void updateProfileActiveFlag(String clientid, String selectflag);

	void triggerEnableDisableProfileBatch();

	void sendFeesReminder(String clientid);

	public List<Client> getTodaysBirthdays();

	void sendBdayWish(String name);

	void createNewEmail(String emailSubject, String receiver, String image);

	void createNewSms(String smsContent, String receiver);

	void sendReminderToSingleClient(String clientname, String clientid, String daysLeft, String packageName,
			String packageDuration, String pendingFees, String feesPaid, String packageTotalFees);

	void triggerFeesPaymentReminderBatch();

	List<SmsLogs> getSmsLogs();

	void renewPackage(String clientid, User user);

    void updatePhotoInfo(Integer cid, String uploadedImagePath);

    void updateSmsDeliveryStatus(String id);

	List<SmsLogs> getSmsByFilter(String gender);

    BrandImages getImageObjectByBrand(String progym);

    List<T_workoutMainType> getWorkoutMainTypeList();

    void addWorkoutObjectToDatabase(String clientId, String workoutDate, String mainWorkoutType);

	List<WorkoutScheduleObject> getWorkoutListByClientId(String cliendId);

    void submitWorkoutSubTypeData(String workoutObjectId, String workoutSubType, String sequence, String maxReps);

    void deleteSubType(String subTypeId);

    void addMainWorkoutToDatabase(String workoutName);

	void addSubWorkoutToDatabase(String mainWorkoutId, String subWorkoutName,Integer sets,Integer reps);

	void deleteMainWorkoutType(Integer id);

	void deleteSubWorkoutType(Integer id);

	List<T_workoutSubType> getWorkoutSubTypeList();

    void syncClientData() throws Exception;

	void reconcileContacts();

    void reconcileWorkoutData();

    List<BloodGroupDetails> getBloodGroupDetails(String bg);

    void setDefaultWorkoutPlan(String clientId, String workoutPlan);

	List<T_workoutMainType> getActiveWorkoutPlansList();

    void updateTSubworkoutType(String subWorkoutId, String serverimagePath);

	void updateTSubWorkoutName(String subWorkoutId, String name , Integer sets , Integer reps);

    List<Merchandise> viewMerchandise();

	List<Supplements> viewSupplements();

	void updateProductToServer(String supplements, String productId, String productName,String productPhotoDesc, String oldPrice, String newPrice, String productPhoto, String aFalse);

    void updateProductPhotoToServer(String category, String productId, String productPhoto);

	void updateProductPhotoDescToServer(String category, String productId, String productPhoto);

    void updateBrandImageToDB(String mobile, String imgCol, String newDbImageName);

    void fetchUserDetailsFromServer(AddMemberObject obj);

    List<OrderModal> getAllOrders(String filter);

    void syncClientUpdatedData();

    void updateOrderStatus(String orderId, String status);

	Boolean isModuleEnabled(String key);

	void updateModuleState(String key, String value);

    void updateModuleDataFromServer();

	isMobileExists initiateActivateProductOperation(String mac, String secret_key);

	boolean getMacActivationStatus();
}
