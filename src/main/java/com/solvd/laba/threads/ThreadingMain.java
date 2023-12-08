package com.solvd.laba.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadingMain {
    public static void main(String[] args) {
//        ExecutorService executor = Executors.newFixedThreadPool(4); // # of threads 4

        // if thread already exist and its not busy it will execute next task
        // if all existing threads are busy then it will create new thread to execute task
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i=1; i<= 100;i++){
            executor.execute(new Threading());
        }

    }
}
