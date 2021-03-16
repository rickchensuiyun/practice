package stu.week04;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeWork0342 {

    private static int num = 0;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        int result = 0;
        // 在这里创建一个线程或线程池，
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                num = sum();
                countDownLatch.countDown();
            }
        });
        // 异步执行 下面方法
        System.out.println(">>>>>>>>>>>>>.线程数：" + Thread.activeCount());
        countDownLatch.await();

        // 确保拿到result 并输出
        result = num;
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
        executorService.shutdown();
    }


    private static int sum() {
        return fibo(45);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
