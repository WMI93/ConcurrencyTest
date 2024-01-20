package Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyTest {
    //EG : comareing between AtomicInteger and Integer
    static Integer integer =new Integer(0);
    static AtomicInteger atomicInteger=new AtomicInteger(0);

    static Runnable r1=()->System.out.println("Incremented value of atomic " +
            "integer is : " + atomicInteger.incrementAndGet());

    static Runnable r2=()->System.out.println("Incremented value of integer " +
            "is : " + ++integer);

    public static void main(String[] args) {
    //create three threads each for incrementing atomic and "normal" integers
        for (int i = 0; i < 5; i++) {
            new Thread(r1).start();
            new Thread(r2).start();
        }
        CopyOnWriteArrayList

    }
}