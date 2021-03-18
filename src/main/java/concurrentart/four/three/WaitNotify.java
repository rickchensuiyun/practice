package concurrentart.four.three;

import concurrentart.four.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {

        Thread waitThread = new Thread(new Wait(),"NotifyThread");
        waitThread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread notifyThread = new Thread(new Notify() , "NotifyThread");
        notifyThread.start();


    }

    static class Notify implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread()+" hold lock .notify @"+
                        new SimpleDateFormat("HH:mm:ss").format(
                        new Date()) );
                lock.notifyAll();
                flag = false;
                SleepUtils.second(2);
            }

            synchronized (lock){
                System.out.println(Thread.currentThread()+" hold lock again .notify @"+
                        new SimpleDateFormat("HH:mm:ss").format(
                                new Date()) );

                SleepUtils.second(2);
            }
        }
    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                while (flag){
                    try {
                        System.out.println(Thread.currentThread()+" flag is true," +
                                "wait @ "+ new SimpleDateFormat("HH:mm:ss").format(
                                        new Date()
                        ));
                        lock.wait();
                    }catch (InterruptedException e){

                    }
                }
                System.out.println(Thread.currentThread()+" flag is false," +
                        "wait @ "+ new SimpleDateFormat("HH:mm:ss").format(
                        new Date()
                ));

            }
        }
    }
}
