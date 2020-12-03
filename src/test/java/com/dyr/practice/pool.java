package com.dyr.practice;

import java.util.concurrent.*;

/**
 * Created By dyr on 2020/12/1.
 */
public class pool {
    public static void main(String[] args) {
        ExecutorService Executor = Executors.newFixedThreadPool(10);
       Thread t1 =  new Thread(new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                return 1;
            }
        }),"线程1");

        Executor.submit(t1);
        Executor.submit(t1);
        Executor.submit(t1);
        Executor.submit(t1);

        System.out.println("---"+Executor.isTerminated());
        System.out.println("---"+Executor.isShutdown());
        Executor.shutdown();
        System.out.println("---2"+Executor.isTerminated());
        System.out.println("---2"+Executor.isShutdown());


    }
}
