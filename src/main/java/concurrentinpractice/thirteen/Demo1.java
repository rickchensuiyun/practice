package concurrentinpractice.thirteen;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        try{
            if(lock.tryLock()){
                sendMsg();
                return;
            }else{
                return;
            }
        }finally {
            System.out.println(">>>>>>>>>.解锁");
            lock.unlock();
        }
    }


    public static void sendMsg(){
        System.out.println(">>>>>>>>>>>>>>>发送消息");
    }

}
