package server.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import database.FirebaseClient;
import models.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LogService {

    public String addLog(Log log) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = FirebaseClient.getCollection(log.projectName);
        DocumentReference documentReference = FirebaseClient.getDocument(collectionReference, String.valueOf(log.dateTime));
        return FirebaseClient.addToDocument(documentReference, log);
    }

    public List<Log> getLogs(ApiFuture<QuerySnapshot> collection, String projectName) throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> documents = collection.get().getDocuments();
        ArrayList<Log> logs = new ArrayList<Log>();
        for (DocumentSnapshot document : documents){
            if (document.exists()){
                logs.add(document.toObject(Log.class));
            }
        }

        return logs;
    }

}
