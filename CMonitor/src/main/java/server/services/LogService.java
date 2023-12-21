package server.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import database.FirebaseClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LogService {

    public <T> String addLog(String projectName, String dateTime, T log) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = FirebaseClient.getCollection(projectName);
        DocumentReference documentReference = FirebaseClient.getDocument(collectionReference, dateTime);
        return FirebaseClient.addToDocument(documentReference, log);
    }

    public List<Object> getLogs(ApiFuture<QuerySnapshot> collection, String projectName) throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> documents = collection.get().getDocuments();
        ArrayList<Object> logs = new ArrayList<Object>();
        for (DocumentSnapshot document : documents){
            if (document.exists()){
                logs.add(document.toObject(Object.class));
            }
        }

        return logs;
    }

}
