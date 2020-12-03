package com.dyr.practice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created By dyr on 2020/12/1.
 */



public class xiancheng {
    public static void main(String[] args) {
        System.out.println("开始---");
        final TicketClub ticketClub = new TicketClub();

        new Thread(new Runnable() {
            @Override
            public void run() {
                    System.out.println("A线程开始运行");
                    while (ticketClub.num > 0) {
                        ticketClub.getTicket();
                        try {
                            Thread.currentThread().sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }
        },"A线程").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B线程开始运行");
                while (ticketClub.num>0){
                ticketClub.getTicket();
                    try {
                        Thread.currentThread().sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"B线程").start();
    }
}

class TicketClub{
    public static int num = 10;
    public final static Lock lock = new ReentrantLock();

    public  void getTicket(){
        synchronized (lock){
                System.out.println(Thread.currentThread().getName()+"卖出第"+num+"张票");
                num--;
                lock.notifyAll();
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        }


}

