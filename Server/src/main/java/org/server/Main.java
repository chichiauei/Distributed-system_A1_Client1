package org.server;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Main {

    private static final int NUM_THREADS = 100;
    private static final String BASE_URL = "http://localhost:8080/Server_war_exploded/";
    public void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i = 0; i < NUM_THREADS; i++) {
            executor.execute(new Client(BASE_URL));
        }
        System.out.print("1");
    }
}

