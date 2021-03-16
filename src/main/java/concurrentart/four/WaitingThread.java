package concurrentart.four;


import sun.misc.Lock;

public class WaitingThread {

    public static void main(String[] args) {

        Lock obj = new Lock();

        new Thread(()->{
            while (true){
                synchronized (obj){
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ,"waitingThread-111").start();
    }
}
