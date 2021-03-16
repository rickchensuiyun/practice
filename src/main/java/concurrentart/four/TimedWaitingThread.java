package concurrentart.four;

import java.util.concurrent.TimeUnit;

public class TimedWaitingThread {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);

                while (true){
                    System.out.println(">>>>>>>>>>>>..执行中");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"测试线程").start();
    }

}

class TimedWaiting implements Runnable{

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
