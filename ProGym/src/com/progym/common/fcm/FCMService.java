package com.progym.common.fcm;

import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.progym.common.constants.ProjectConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class FCMService {
    private Logger logger = LoggerFactory.getLogger(FCMService.class);


    public void sendMessageToMultipleToken(PushNotificationRequest request) throws FirebaseMessagingException {

        Map<String,String> map = new HashMap<>();

        map.put(ProjectConstants.CLIENT_NAME,request.getClientName());
        map.put(ProjectConstants.NOTIFICATION_TYPE,request.getNotificationType());
        map.put(ProjectConstants.TARGET_CLASS,request.getTargetClass());
        map.put(ProjectConstants.TITLE,request.getTitle());
        map.put(ProjectConstants.BODY,request.getMessage());
        map.put(ProjectConstants.IMAGE,request.getImage());

        List<String> registrationTokens = request.getTokensList();

        Notification.Builder builder = Notification.builder();
        builder.setTitle(request.getTitle());
        builder.setBody(request.getMessage());
        builder.setImage(request.getImage());

        MulticastMessage message = MulticastMessage.builder()
                .putAllData(map)
                .addAllTokens(registrationTokens)
                .setNotification(builder.build())
                .build();

        BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);

        System.out.println(response.getSuccessCount() + " messages were sent successfully");
    }

    public void sendMessageToSingleToken(PushNotificationRequest request) throws InterruptedException, ExecutionException, FirebaseMessagingException {

        Map<String,String> map = new HashMap<>();
        map.put(ProjectConstants.CLIENT_NAME,request.getClientName());
        map.put(ProjectConstants.NOTIFICATION_TYPE,request.getNotificationType());
        map.put(ProjectConstants.TARGET_CLASS,request.getTargetClass());
        map.put(ProjectConstants.TITLE,request.getTitle());
        map.put(ProjectConstants.BODY,request.getMessage());
        map.put(ProjectConstants.IMAGE,request.getImage());


        Message message = getPreconfiguredMessageWithData(map , request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response+ " msg "+jsonOutput);
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging
                .getInstance()
                .sendAsync(message)
                .get();
    }


    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                        .setTag(topic).build()).build();
    }
    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder()
                        .setCategory(topic)
                        .setThreadId(topic)
                        .build())
                .build();
    }
    private Message getPreconfiguredMessageToToken(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request)
                .setToken(request.getToken())
                .build();
    }
    private Message getPreconfiguredMessageWithoutData(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request)
                .setTopic(request.getTopic())
                .build();
    }
    private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request)
                .putAllData(data)
                .setToken(request.getToken())
                .build();
    }
    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        Notification.Builder builder = Notification.builder();
        builder.setTitle(request.getTitle());
        builder.setBody(request.getMessage());
        builder.setImage(request.getImage());
        builder.setImage(request.getImage());
        //builder.build();
        return Message.builder()
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .setNotification(builder.build());
    }

    public void sendBatchMessagesToToken(List<PushNotificationRequest> list) throws FirebaseMessagingException {
        List<Message> messages = new ArrayList<>();
        for(PushNotificationRequest request : list){
            Map<String,String> map = new HashMap<>();
            if(request.getImage() == null){
                request.setImage("");
                map.put(ProjectConstants.IMAGE,"");
            }
            else{
                map.put(ProjectConstants.IMAGE,request.getImage());
            }



            map.put(ProjectConstants.CLIENT_NAME,request.getClientName());
            map.put(ProjectConstants.NOTIFICATION_TYPE,request.getNotificationType());
            map.put(ProjectConstants.TARGET_CLASS,request.getTargetClass());
            map.put(ProjectConstants.TITLE,request.getTitle());
            map.put(ProjectConstants.BODY,request.getMessage());


            Message message = getPreconfiguredMessageBuilder(request)
                    .setNotification(Notification.builder()
                            .setTitle(request.getTitle())
                            .setBody(request.getMessage())
                            .setImage(request.getImage())
                            .build())
                    .putAllData(map)
                    .setToken(request.getTokensList().get(0))
                    .build();

            messages.add(message);
        }

        BatchResponse response = FirebaseMessaging.getInstance().sendAll(messages);
        System.out.println(response.getSuccessCount() + " messages were sent successfully");
    }
}