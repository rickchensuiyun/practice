package concurrentart.four;

import org.apache.tools.ant.taskdefs.Sleep;

public class ThreadState {
    public static void main(String[] args) {

        new Thread(new TimeWaiting(),"timewaitingThread") .start();
        new Thread(new Waiting(),"waitingThread") .start();
        new Thread(new Blocked(),"blocked1Thread") .start();
        new Thread(new Blocked(),"blocked2Thread") .start();


    }

    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while (true){
                SleepUtils.second(100);
            }
        }
    }

    static class Waiting implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUtils.second(100);
                }
            }
        }
    }
}
