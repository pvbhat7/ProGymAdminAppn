package com.progym.common.fcm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PushNotificationService {

    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);

    private FCMService fcmService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    public PushNotificationService() {
    }


    public void sendSameMessageToAllTokens(PushNotificationRequest request) {
        try {
            new FCMService().sendMessageToMultipleToken(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void sendBatchNotificationMessage(List<PushNotificationRequest> request) {
        try {
            new FCMService().sendBatchMessagesToToken(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}