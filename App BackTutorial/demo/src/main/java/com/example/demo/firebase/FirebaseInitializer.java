package com.example.demo.firebase;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;


@Service
public class FirebaseInitializer {
    
    @PostConstruct
    private void initFireStore() throws IOException{
        
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("private-key-firestore.json");
        
        FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://fir-5db01.firebaseio.com/")
        .build();

        if(FirebaseApp.getApps().isEmpty()){
            FirebaseApp.initializeApp(options);
        }

    }

    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }
}
    