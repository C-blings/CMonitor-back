package server.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import database.FirebaseClient;
import models.Project;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ProjectService {

    private static final String collectionName = "projects";

    public String createProject(Project project) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = FirebaseClient.getDocument(collectionName, project.projectName);
        return FirebaseClient.addToDocument(documentReference, project);
    }

    public Project getProject(String documentName) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = FirebaseClient.getCollection(collectionName);
        DocumentSnapshot document = FirebaseClient.getDocumentSnapshot(collectionReference, documentName);

        Project project;
        if (document.exists()){
            project = document.toObject(Project.class);
            return project;
        }
        return null;
    }

    public String deleteProject(String projectName) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = FirebaseClient.getDocument(collectionName, projectName);
        return FirebaseClient.deleteFormDocument(documentReference);
    }

    public String updateProject(Project project) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = FirebaseClient.getDocument(collectionName, project.projectName);
        return FirebaseClient.updateDocument(documentReference, project);
    }
}
