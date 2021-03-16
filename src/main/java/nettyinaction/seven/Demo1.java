package nettyinaction.seven;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Demo1 {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
        ScheduledFuture future = executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(" 60 seconds later ");
            }
        },60, TimeUnit.SECONDS);

        executor.shutdown();
    }
}
