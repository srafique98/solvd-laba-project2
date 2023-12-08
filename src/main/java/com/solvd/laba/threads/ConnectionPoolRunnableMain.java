package com.solvd.laba.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionPoolRunnableMain {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolRunnableMain.class);
    public static void main(String[] args)  {
// if thread already exist and its not busy it will execute next task
// if all existing threads are busy then it will create new thread to execute task
// ExecutorService executor = Executors.newCachedThreadPool();

        ConnectionPool pool = new ConnectionPool(5);
        ExecutorService ex = Executors.newFixedThreadPool(7); // # of threads 7
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Connection c = pool.getConnection();
                    c.connect();
                    Thread.sleep(1000);
                    LOGGER.info("Completed");
                    pool.releaseConnection(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 7; i++) {
            ex.execute(task);
        }
        ex.shutdown();
    }
}
