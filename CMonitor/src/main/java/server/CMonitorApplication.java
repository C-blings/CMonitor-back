package server;

import database.FirebaseAuth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class CMonitorApplication {

    public static void main(String[] args) throws IOException {

        FirebaseAuth.authorizeFirebase();

        SpringApplication.run(CMonitorApplication.class, args);
    }

}
