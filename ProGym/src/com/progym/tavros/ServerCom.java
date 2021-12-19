package com.progym.tavros;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.progym.api.ServerApi;
import com.progym.common.fcm.PushNotificationRequest;
import com.progym.common.fcm.PushNotificationService;
import com.progym.common.model.FcmTokenModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class ServerCom {

    static String jsonString = "";

    public static TaskExecutor getTaskExecutor(){
        ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
        t.setCorePoolSize(1);
        t.setMaxPoolSize(5);
        t.initialize();
        return t;
    }

    public static String sendSingleObjectToServer(String api, Object object) {
        String resultId = "";
        System.out.println("Calling api :" + api);
        try {
            jsonString = new ObjectMapper().writeValueAsString(object);
            System.out.println(jsonString);
            resultId = sendPostRequestToServer(jsonString, api);
        } catch (Exception e) {}
        return resultId;
    }

    public static void sendMultipleObjectToServer(String api, List<String> jsonStringList) {
        System.out.println("Calling api :" + api);
        getTaskExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    for (String jsonString : jsonStringList) {
                        System.out.println(jsonString);
                        sendPostRequestToServer(jsonString, api);
                    }
                } catch (Exception e) {
                }
            }
        });
    }


    private static String sendPostRequestToServer(String json, String apiUrl) throws Exception {
        URL obj = new URL(apiUrl);
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();


        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
        if(postConnection.getHeaderFields().get("result_id") != null)
            return postConnection.getHeaderFields().get("result_id").get(0);
            else
                return "";



    }

    public static String sendGetRequestToServer(String api) {
        System.out.println("Calling api : " + api);
        String jsonResponse = "";
        try {
            URL urlForGetRequest = new URL(api);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();


            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                // print result
                System.out.println("JSON String Result " + response.toString());
                jsonResponse = response.toString();
                //GetAndPost.POSTRequest(response.toString());
            } else {
                System.out.println("GET NOT WORKED");
            }
        } catch (Exception e) {
            System.out.print("");
        }
        return jsonResponse;
    }

    public static void sendSameMessageToAllTokens(PushNotificationRequest request) {
        new PushNotificationService().sendSameMessageToAllTokens(request);
    }

    public static void sentBatchFcmNotification(List<PushNotificationRequest> requestList) {
        new PushNotificationService().sendBatchNotificationMessage(requestList);
    }

    public static Map<String, List<String>> getAllTokensByMobileList(List<String> mobileList) {
        List<FcmTokenModal> list = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        String finalString = "";
        for (String o : mobileList) {
            finalString = finalString.concat("'").concat(o).concat("'").concat(",");
        }
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_TOKEN_BY_MOBILE_LIST + finalString.substring(0, finalString.length() - 1));
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                FcmTokenModal array[] = gson.fromJson(response, FcmTokenModal[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        for (FcmTokenModal obj : list) {
            if (map.get(obj.getMobile()) != null) {
                List<String> tokens = map.get(obj.getMobile());
                tokens.add(obj.getToken());
                map.replace(obj.getMobile(), tokens);
            } else {
                List<String> tokens = new ArrayList<>();
                tokens.add(obj.getToken());
                map.put(obj.getMobile(), tokens);
            }

        }
        return map;

    }

    public static List<FcmTokenModal> getAllTokensByMobile(String mobile) {
        List<FcmTokenModal> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_TOKEN_BY_MOBILE + mobile);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                FcmTokenModal array[] = gson.fromJson(response, FcmTokenModal[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    public static List<FcmTokenModal> getAllTokens() {
        List<FcmTokenModal> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_TOKENS);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                FcmTokenModal array[] = gson.fromJson(response, FcmTokenModal[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

}

