package com.dyr.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created By dyr on 2020/12/1.
 * 轮流打印ABC
 */
public class ConditionDemo {
    public static void main(String[] args) {
        final Printer printer = new Printer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1;i<=20;i++){
                    printer.print(1);
                }

            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1;i<=20;i++){
                    printer.print(2);
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1;i<=20;i++){
                    printer.print(3);
                }
            }
        },"C").start();

    }

}




class Printer{

    private static Lock lock = new ReentrantLock();
    private static int point = 1;

    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public  void print(int myOrder){
        lock.lock();
        switch (myOrder){
            case 1:
                if (point != 1){
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName());
                point = 2;
                condition2.signal();
                break;
            case 2:
                if(point !=2 ){
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName());
                point = 3;
                condition3.signal();
                break;
            case 3:
                if(point != 3){
                    try {
                        condition3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName());
                point = 1;
                condition1.signal();
                break;
        }
        lock.unlock();
    }

}