package concurrentinpractice.three;

import java.util.Random;

public class ThreadLocalDemo {

    static final ThreadLocal<Integer> tl = new ThreadLocal<Integer>(){
        protected Integer initialValue() {
            return 1;
        }
    };

    public static void main(String[] args) throws InterruptedException {

        System.out.println(">>>>>>>>>>.主线程的值："+tl.get());
        for (int i = 0; i < 5; i++) {
            new Thread( ()-> {
                System.out.println("-------------------新线程的值111："+Thread.currentThread().getName()+"---"+tl.get());
                tl.set(new Random().nextInt(10));
                System.out.println("---------------------新线程的值222："+Thread.currentThread().getName()+"---"+tl.get());
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(">>>>>>>>>>.主线程的值222："+tl.get());

    }
}
