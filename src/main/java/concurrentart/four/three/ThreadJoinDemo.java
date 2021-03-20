package concurrentart.four.three;

/**
 * 在A线程中调用B线程的join方法：B.join();
 * 就是A线程暂停，等待B线程执行完了再继续执行。
 * 如果join方法有时间参数，那么如果B线程超过时间还未执行完毕，
 * A线程先行返回。
 */
public class ThreadJoinDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread task = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>task线程执行");
            }
        },"test");
        task.start();
        System.out.println(">>>>>>>>>>>>>>>>主线程继续执行11111111");
        Thread.sleep(1000);
        task.join(10000);   //让task线程优先执行，执行完了主线程才继续执行。
        System.out.println(">>>>>>>>>>>>>>>>主线程继续执行2222222222");


    }
}
