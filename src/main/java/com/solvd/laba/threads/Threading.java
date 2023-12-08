package com.solvd.laba.threads;

public class Threading extends Thread{

    public void run(){
        System.out.println("Thread name: " + Thread.currentThread().getName());
    }
}
