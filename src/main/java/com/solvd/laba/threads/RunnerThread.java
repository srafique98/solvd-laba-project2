package com.solvd.laba.threads;

public class RunnerThread implements Runnable{
    private String msg;

    RunnerThread(String msg) {
        this.msg = msg;
    }
    @Override
    public void run() {
        System.out.println(msg);
    }
}
