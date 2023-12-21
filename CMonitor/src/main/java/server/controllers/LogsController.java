package server.controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QuerySnapshot;
import database.FirebaseClient;
import org.springframework.web.bind.annotation.*;
import server.services.LogService;

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
    public String addLog(@RequestParam String projectName, @RequestParam String dateTime, @RequestBody Object log) throws ExecutionException, InterruptedException {
        return logService.addLog(projectName, dateTime, log);
    }

    @GetMapping("/get-logs")
    public List<Object> getLogs(@RequestParam String projectName) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = FirebaseClient.getCollection(projectName);
        ApiFuture<QuerySnapshot> collection = collectionReference.get();
        return logService.getLogs(collection, projectName);
    }
}
