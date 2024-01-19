package Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrencyTest {
    Object lock1=new Object(); Object lock2=new Object();
    public static void main(String[] args) {
        ThreadDemo1 t1=new ThreadDemo1();
        ThreadDemo1 t2=new ThreadDemo1();
        t1.start();t2.start();
    }

    private static class ThreadDemo1 extends Thread {
        public void run() {
            synchronized (Lock1) {// in ThreadDemo2 1s synchronized(lock2)
                System.out.println("Thread 2: Holding lock 2 ... ");
                //    Thread.sleep(10); must be in try/catch(InterruptedException e) block
                Thread.sleep(10);
                System.out.println("Thread 2: Waiting for lock 1 ... ");
                synchronized (Lock2) {// in ThreadDemo2 is synchronized(lock1)
                    System.out.println("Thread 2: Holding lock 1 & 2 ... ");
                }
            }
        }
    }

    private static class ThreadDemo2 extends Thread {
        public void run() {
            synchronized (Lock2) { // in ThreadDemo1 is synchronized(lock1)
                System.out.println("Thread 2: Holding lock 2 ... ");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                System.out.println("Thread 2: Waiting for lock 1 ... ");
                synchronized (Lock1) {//// in ThreadDemo1 is synchronized(lock2)
                    System.out.println("Thread 2: Holding lock 1 & 2 ... ");
                }
            }
        }
    }
}
