package com.progym.api;

public class ServerApi {

    public static final String TAVROS_SERVER = "http://tavrostechinfo.com/PROGYM/progym_online/api/";
    public static final String TEST_SERVER = "http://tavrostechinfo.com/PROGYM/test_api/api/";
    public static final String LOCALHOST_SERVER = "http://localhost/progym_online/api/";

    public static String SERVER = TAVROS_SERVER;

    public static final String GET_TOKEN_BY_MOBILE_LIST = SERVER + "fcmToken/getByMobileList.php?mobile=";

    public static final String CREATE_DIET_TEMPLATE_API = SERVER + "dietplantemplate/create.php";
    public static final String SINGLE_READ_DIET_TEMPLATE_API = SERVER + "dietplantemplate/single_read.php?id=";
    public static final String UPDATE_DIET_TEMPLATE_API = SERVER + "dietplantemplate/update.php";
    public static final String GET_ALL_DIET_TEMPLATE_EXT_CODES = SERVER + "dietplantemplate/getAllExtCodes.php";
    public static final String GET_ALL_DEFAULT_DIET_TEMPLATES = SERVER + "dietplantemplate/getDefaultDietTemplates.php";
    public static final String GET_CLIENT_PREVIOUS_DIET_PLAN_TEMPLATES = SERVER + "dietplantemplate/getClientPreviousTemplates.php?";



    public static final String CREATE_DIET_PLAN_API = SERVER + "dietplanobjecttable/create.php";
    public static final String SINGLE_READ_DIET_PLAN_API = SERVER + "dietplanobjecttable/single_read.php?id=";
    public static final String SINGLE_READ_DIET_PLAN_BY_CID_AND_DATE_API = SERVER + "dietplanobjecttable/single_readByCidAndDate.php?";
    public static final String UPDATE_DIET_PLAN_API = SERVER + "dietplanobjecttable/update.php";
    public static final String GET_ALL_DIET_OBJECT_EXT_CODES = SERVER + "dietplanobjecttable/getAllExtCodes.php";
    public static final String GET_ALL_DIET_OBJECT_BY_TEMPLATE_ID = SERVER + "dietplanobjecttable/getAllByTemplateId.php?dptid=";


    public static final String CREATE_CLIENT_API = SERVER + "client/create.php";
    public static final String UPDATE_CLIENT_API = SERVER + "client/update.php";
    public static final String GET_CLIENT_UPDATED_SYNC_RECORDS = SERVER + "client/getClientSyncRecords.php";
    public static final String SINGLE_READ_CLIENT_API = SERVER + "client/single_read.php?id=";
    public static final String GET_ALL_CLIENT_EXTERNAL_CODES_API = SERVER + "client/getAllExtCodes.php";
    public static final String GET_IS_USER_MOBILE_EXISTS = SERVER + "/isUserMobileExists.php/?mobile=";
    public static final String UPDATE_EXT_CODE_ON_SERVER = SERVER + "client/updateExtCode.php/?";



    public static final String CREATE_T_MAIN_WORKOUT_TYPE_API = SERVER + "t_workoutmaintype/create.php";
    public static final String SINGLE_READ_T_MAIN_WORKOUT_TYPE_API = SERVER + "t_workoutmaintype/single_read.php?id=";
    public static final String UPDATE_T_MAIN_WORKOUT_TYPE_API = SERVER + "t_workoutmaintype/update.php";
    public static final String GET_ALL_T_MAIN_WORKOUT_TYPE_API = SERVER + "t_workoutmaintype/getAll.php";

    public static final String CREATE_T_SUB_WORKOUT_TYPE_API = SERVER + "t_workoutsubtype/create.php";
    public static final String SINGLE_READ_T_SUB_WORKOUT_TYPE_API = SERVER + "t_workoutsubtype/single_read.php?id=";
    public static final String UPDATE_T_SUB_WORKOUT_TYPE_API = SERVER + "t_workoutsubtype/update.php";
    public static final String GET_ALL_T_SUB_WORKOUT_TYPE_BY_MAIN_TYPE_ID = SERVER + "t_workoutsubtype/getAllByMainTypeId.php?mtid=";
    public static final String GET_ALL_T_SUB_WORKOUT_TYPE = SERVER + "t_workoutsubtype/getAll.php";

    public static final String CREATE_WORKOUT_SCHEDULE_OBJECT_API = SERVER + "workoutScheduleObject/create.php";
    public static final String SINGLE_READ_WORKOUT_SCHEDULE_OBJECT_API = SERVER + "workoutScheduleObject/single_read.php?id=";
    public static final String UPDATE_WORKOUT_SCHEDULE_OBJECT_API = SERVER + "workoutScheduleObject/update.php";
    public static final String GET_ALL_WORKOUT_SCHEDULE_OBJECT_EXTERNAL_CODES_API = SERVER + "workoutScheduleObject/getAllExtCodes.php";
    public static final String GET_ALL_WORKOUT_SCHEDULE_OBJECT_BY_CLIENT_ID = SERVER + "workoutScheduleObject/getAllByClientId.php?cid=";
    public static final String GET_BY_CID_AND_DATE = SERVER + "workoutScheduleObject/byClientExtCodeAndDate.php?";

    public static final String CREATE_WORKOUT_SUB_TYPE_API = SERVER + "workoutSubType/create.php";
    public static final String SINGLE_READ_WORKOUT_SUB_TYPE_API = SERVER + "workoutSubType/single_read.php?id=";
    public static final String UPDATE_WORKOUT_SUB_TYPE_API = SERVER + "workoutSubType/update.php";
    public static final String GET_ALL_WORKOUT_SUB_TYPE_BY_WOS_ID = SERVER + "workoutSubType/getSubWorkoutPlansByExtCode.php?externalCode=";

    public static final String GET_SUB_WORKOUT_PLANS_BY_EXT_CODE = SERVER + "workoutSubType/getSubWorkoutPlansByExtCode.php?externalCode=";
    public static final String GET_ALL_WORKOUT_SUB_TYPE_EXTERNAL_CODES_API = SERVER + "workoutSubType/getAllExtCodes.php";

    public static final String GET_ALL_SUPPLEMENTS = SERVER + "supplements/getAllSupplements.php";
    public static final String GET_ALL_MERCHANDISE = SERVER + "merchandise/getAllMerchandise.php";
    public static final String UPDATE_SUPPLEMENTS = SERVER + "supplements/update.php";
    public static final String UPDATE_MERCHANDISE = SERVER + "merchandise/update.php";
    public static final String UPDATE_PHOTO_SUPPLEMENTS = SERVER + "supplements/updatePhoto.php";
    public static final String UPDATE_PHOTO_DESC_SUPPLEMENTS = SERVER + "supplements/updatePhotoDesc.php";

    public static final String UPDATE_PHOTO_MERCHANDISE = SERVER + "merchandise/updatePhoto.php";

    public static final String UPDATE_BRAND_IMAGES_API = SERVER + "brand_images/update.php";
    public static final String GET_ALL_ORDERS = SERVER + "orders/getAllOrders.php?filter=";
    public static final String GET_ORDER_BY_CLIENT_ID = SERVER + "orders/retrieve.php";
    
    public static final String UPDATE_ORDER_STATUS = SERVER + "orders/updateOrderStatus.php?";
    public static final String GET_ALL_TOKENS = SERVER + "fcmToken/getAll.php";
    public static final String GET_TOKEN_BY_MOBILE = SERVER + "fcmToken/getByMobile.php?mobile=";

    public static final String GET_MODULE_DATA_BY_MAC = SERVER + "module/getByMac.php?mac=";
    public static final String UPDATE_MODULE_EMAIL_BY_MAC = SERVER + "module/update.php?mac=";
    public static final String UPDATE_MODULE_SMS_BY_MAC = SERVER + "module/update.php?mac=";

    public static final String ACTIVATE_PRODUCT_API = TEST_SERVER + "license_api/activate_mac.php?";
    public static final String CHECK_MAC_ACTIVATION_STATUS_API = TEST_SERVER + "license_api/getMacStatus.php?mac=";


    /*public static final String CREATE_WORKOUT_SUB_TYPE_SET_API = SERVER+"workoutSubTypeSet/create.php";
    public static final String SINGLE_READ_WORKOUT_SUB_TYPE_SET_API = SERVER+"workoutSubTypeSet/single_read.php?id=";
    public static final String UPDATE_WORKOUT_SUB_TYPE_SET_API = SERVER+"workoutSubTypeSet/update.php";*/

}
