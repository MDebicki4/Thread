package com.michaldebicki;

import static com.michaldebicki.ThreadColor.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE + "Hello from the main thread");


        Thread anotherThread = new AnotherThread();
        anotherThread.setName("====AnotherThread=====");
        anotherThread.start();

        new Thread() {
            public void run() {
                System.out.println(ANSI_GREEN + "Hello from the anonymous class thread");
            }
        }.start();

        Thread myRunnableThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println(ANSI_RED + "Hello from the anonymous class implementation of run");
                try {
                    anotherThread.join();
                    System.out.println(ANSI_RED + "AnotherThread terminated, or timed out, so I am running again");
                } catch (InterruptedException e) {
                    System.out.println(ANSI_RED + "I could not wait after all. I was interrupted");
                }
            }
        });
        myRunnableThread.start();
        //anotherThread.interrupt();

        System.out.println(ANSI_PURPLE + "Hello again from the main thread");


    }
}
