package org.eg;

import java.security.Timestamp;
import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static CountDownLatch completed;
    public static void main(String[] args) throws InterruptedException {

        final int NUM_THREADS = 200;
        final String BASE_URL = "http://localhost:8080/Server_war_exploded/";
        final RequestCounter counter = new RequestCounter();
        completed = new CountDownLatch(NUM_THREADS);
        Instant start = Instant.now();
        for (int i = 0; i < NUM_THREADS; i++) {
            Client client = new Client(BASE_URL);
            Thread thread = new Thread(client);
            thread.start();
            counter.inc();
        }
        completed.await();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        double totalThroughput = (double) counter.getVal() / (double) timeElapsed.toMillis() * 1000;
        System.out.println("Value should be equal to " + NUM_THREADS + " It is: " + counter.getVal() + ".");
        System.out.println("There are " + Client.getSuccessfulThreadsCount() + " successful threads and " + Client.getFailedThreadsCount() + " failed threads.");
        System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds.");
        System.out.println("The total throughput in requests per second is " + totalThroughput + ".");
    }
}


