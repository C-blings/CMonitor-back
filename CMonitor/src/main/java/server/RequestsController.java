package server;

import models.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestsController {
    @PostMapping(value = "add-log")
    public void getLog(@RequestBody Log log){
        System.out.println(log.logStatus);
    }
}
