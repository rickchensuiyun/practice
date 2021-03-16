package stu.week04;

import java.util.concurrent.*;

public class HomeWork031 {

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        int result = 0;
        // 在这里创建一个线程或线程池，
        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        };
        // 异步执行 下面方法
        Future<Integer> future = executor.submit(task);
        System.out.println(">>>>>>>>>>>>>.线程数：" + Thread.activeCount());
        result = future.get();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
        executor.shutdown();
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
