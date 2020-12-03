package com.dyr.practice;

/**
 * Created By dyr on 2020/12/1.
 */
public class PS {
    public static void main(String[] args) {
       final DB db = new DB();

        new Thread(new Runnable() {
            @Override
            public void run() {
           for(int n = 1;n<10 ;n++){
               db.add();
           }
            }
        },"生产者-1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int n = 1;n<10 ;n++){
                    db.add();
                }
            }
        },"生产者-2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int n = 1;n<10 ;n++){
                    db.dcre();
                }
            }
        },"消费者-1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int n = 1;n<10 ;n++){
                    db.dcre();
                }
            }
        },"消费者-2").start();

    }
}



class DB{

    public static int num = 0;
    public synchronized void add(){
        while (num > 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"生产了一个商品");
        num++;
        this.notifyAll();
    }
    public synchronized  void dcre(){
        while (num==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"消费了一个商品");
        num--;
            this.notifyAll();
    }

}
