package se.sbab.demo.zipkin;


import brave.Tracer;
import brave.Tracing;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Reporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

public class Raw {
    private static final String ZIPKIN_SERVER_URL = "http://localhost:9411/api/v2/spans";

    public static void main(String[] args) throws InterruptedException {
        Sender sender = OkHttpSender.create(ZIPKIN_SERVER_URL);
        Reporter<Span> reporter = AsyncReporter.builder(sender).build();

        Tracing tracing = Tracing.newBuilder()
                .localServiceName("test-service")
                .spanReporter(reporter)
                .build();

        Tracer tracer = tracing.tracer();

        brave.Span span = tracer.newTrace().name("encode").start();
        span.annotate("Test");
        span.tag("Hello", "World");
        Thread.sleep(100);

        span.finish();
        tracing.close();
        Thread.sleep(1000); //Needed for the AsyncReport to send before program closes
    }
}
