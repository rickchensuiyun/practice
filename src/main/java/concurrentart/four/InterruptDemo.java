package concurrentart.four;

/**
 * 演示中断的作用：
 * 中断只是给线程设置了一个标识，能不能起作用取决于编写线程代码的人，
 * 是否要根据中断状态进行一些操作。如果编写代码的人不理会这个状态则
 * 没什么用。
 */
public class InterruptDemo<main> {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {

            while (true){
                if(Thread.currentThread().isInterrupted()){ //获取中断状态
                    System.out.println(">>>>>>>>>>>>被中断了");

                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        //抛出异常也会清除中断状态
                        e.printStackTrace();
                    }
//                    Thread.interrupted();//清除中断状态
                    System.out.println(Thread.currentThread().isInterrupted());
                    break;
                }
            }
        });
        t.start();
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();//设置线程的中断状态。
    }



}
