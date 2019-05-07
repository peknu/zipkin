package se.sbab.demo.zipkin;

import brave.ScopedSpan;
import brave.Tracing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@RestController
public class Backend {

    @Autowired
    private Tracing tracing;

    @Autowired
    private BackendService backendService;

    @RequestMapping("/api")
    public String printDate() {
        //tracing.tracer().currentSpanCustomizer().tag("TAG NAME", "TAG VALUE").annotate("ANNOTATION");
        internalCall();
        return new Date().toString();
    }

    // Start a new span "manually" using the injected tracing object
    private void internalCall() {
        ScopedSpan span = tracing.tracer().startScopedSpan("encode");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        backendService.backendCall();
        span.finish();
    }

    public static void main(String[] args) {
        SpringApplication.run(Backend.class, "--spring.application.name=Backend", "--server.port=9000");
    }
}
