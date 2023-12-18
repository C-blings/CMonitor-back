package server.controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import database.FirebaseClient;
import models.Log;
import org.springframework.web.bind.annotation.*;
import server.services.LogService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
public class LogsController {

    private final LogService logService;

    LogsController(LogService logService){
        this.logService = logService;
    }

    @PostMapping("/post-log")
    public String addLog(@RequestBody Log log) throws ExecutionException, InterruptedException {
        return logService.addLog(log);
    }

    @GetMapping("/get-logs")
    public List<Log> getLogs(@RequestParam String projectName) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = FirebaseClient.getCollection(projectName);
        ApiFuture<QuerySnapshot> collection = collectionReference.get();
        return logService.getLogs(collection, projectName);
    }
}
