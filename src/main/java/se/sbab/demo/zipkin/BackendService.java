package se.sbab.demo.zipkin;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Service;

@Service
public class BackendService {
    //
    @NewSpan("db-call")
    public void backendCall() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
