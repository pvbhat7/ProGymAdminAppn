package com.progym.common.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;

public class FCM {

    private static String isInitialized = "";

    private static void initializeFCMObject() {
        try {
            FileInputStream serviceAccount = new FileInputStream("D:\\firebaseAdminSdkSecretKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            isInitialized = "true";
        }
        catch (Throwable ex) {
        }
    }

    public static void initialize() {
        if(isInitialized.isEmpty())
            initializeFCMObject();
    }
}
