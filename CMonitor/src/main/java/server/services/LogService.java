package server.services;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import database.FirebaseClient;
import models.Log;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class LogService {

    public String addLog(Log log) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = FirebaseClient.getCollection(log.projectName);
        DocumentReference documentReference = FirebaseClient.getDocument(collectionReference, "log_" + String.valueOf(log.logId));
        return FirebaseClient.addToDocument(documentReference, log);
    }

}
