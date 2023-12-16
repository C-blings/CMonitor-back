package server.controllers;

import models.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.services.LogService;

import java.util.concurrent.ExecutionException;

@RestController
public class LogsController {

    private final LogService logService;

    LogsController(LogService logService){
        this.logService = logService;
    }

    @PostMapping("/post-log")
    public String addLog(@RequestBody Log log) throws ExecutionException, InterruptedException {
        return logService.addLog(log);
    }
}
