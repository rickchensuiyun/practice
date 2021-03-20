package concurrentart.four.three;

import java.util.concurrent.TimeUnit;

/**
 * 该demo同样演示了join的作用
 * 从main线程开始，各线程依次执行。
 */
public class ThreadJoinDemo2 {

    public static void main(String[] args) throws InterruptedException {

        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(previous),i+"");
            thread.start();
            previous = thread;
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName()+" 中止");

    }

    static class Domino implements Runnable{

        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +" 中止");
        }
    }
}
