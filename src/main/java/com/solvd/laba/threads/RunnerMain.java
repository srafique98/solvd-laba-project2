package com.solvd.laba.threads;

public class RunnerMain {
    public static void main(String[] args){

        // 2 ways for threads extending class and implementing interface through runnable
        RunnerThread obj1 = new RunnerThread("Hi");
        RunnerThread obj2 = new RunnerThread("Bye");

        Thread t1 = new Thread(obj1); // when using interface have to create objects of Thread
        Thread t2 = new Thread(obj2);

        t1.start();
        try {
            Thread.sleep(10);
        } catch (Exception e){
            System.out.println("Error threads");
        }
        t2.start();

    }


}
