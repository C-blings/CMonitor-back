package server.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import database.FirebaseClient;
import models.Project;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ProjectService {

    private static final String collectionName = "projects";

    public String createProject(Project project) throws ExecutionException, InterruptedException {
        return FirebaseClient.addToCollection(collectionName, project.projectName, project);
    }

    public Project getProject(String documentId) throws ExecutionException, InterruptedException {
        DocumentSnapshot document = FirebaseClient.getDocument(documentId);

        Project project;
        if (document.exists()){
            project = document.toObject(Project.class);
            return project;
        }
        return null;
    }

    public String deleteProject(String documentId) throws ExecutionException, InterruptedException {
        return FirebaseClient.deleteFormCollection(collectionName, documentId);
    }

    public String updateProject(Project project) throws ExecutionException, InterruptedException {
        return FirebaseClient.updateDocument(collectionName, project.projectName, project);
    }
}
