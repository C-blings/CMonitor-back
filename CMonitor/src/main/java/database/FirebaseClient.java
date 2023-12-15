package database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.util.concurrent.ExecutionException;

public class FirebaseClient {

    private static final Firestore database;

    static {
        database = FirestoreClient.getFirestore();
    }

    public static DocumentSnapshot getDocument(String documentId) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = database.collection("projects").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        return future.get();
    }

    public static <T> String addToCollection(String collectionName, String documentName, T object) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collection = database.collection(collectionName).document(documentName).set(object);
        return collection.get().getUpdateTime().toString();
    }

    public static String deleteFormCollection(String collectionName, String documentName) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result = database.collection(collectionName).document(documentName).delete();
        return result.get().getUpdateTime().toString();
    }

    public static <T> String updateDocument(String collectionName, String documentName, T newObject) throws ExecutionException, InterruptedException {
        return addToCollection(collectionName, documentName, newObject);
    }
}
