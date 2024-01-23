package Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
public class ConcurrencyTest {
    //Task to do by threads
    private void removeLions() {System.out.println("Removing lions");}
    private void cleanPen() {System.out.println("Cleaning the pen");}
    private void addLions() {System.out.println("Adding lions");}

    public void performTask(){//CyclicBarrier c) {
        removeLions();
        cleanPen();
        addLions();
    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            var manager = new ConcurrencyTest();
            Runnable r=()->System.out.println("all threads have been arrived");
            // 4 : number of threads(parties) that should be waiting for each other
            // r: is Barrier action that will be executed by the last thread of this 4
            CyclicBarrier c=new CyclicBarrier(4,r);
            //the count of loops(4) that should be equal to number of threads initialized,♦
             for (int i = 0; i < 4; i++)
                service.submit(() -> manager.performTask());
        } finally {
            if (service != null) service.shutdown();
        }
    }
}

