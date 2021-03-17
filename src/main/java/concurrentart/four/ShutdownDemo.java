package concurrentart.four;

import java.util.concurrent.TimeUnit;

/**
 * 这种同时标识位和中断状态去停止线程，让线程有机会
 * 做一些清理操作而不是直接停止的方式更加优雅和安全。
 */
public class ShutdownDemo {

    public static void main(String[] args) {

        Runnable t1 = new Task();
        Thread countThread = new Thread(t1,"customThread1");
        countThread.start();
        SleepUtils.second(1);
        countThread.interrupt();

        //线程2
        Task t2 = new Task();
        countThread = new Thread(t2,"customThread2");
        countThread.start();
        SleepUtils.second(1);
        t2.cancel();
    }

    private static class Task implements Runnable{
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("count i = "+i);
        }

        public void cancel(){
            on = false;
        }
    }
}
