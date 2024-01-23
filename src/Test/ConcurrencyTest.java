package Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrencyTest {

    public static void main(String[] args) {
        Runnable r=() -> System.out.println("test Runable");
        //Eg using Lock interface
        Lock lock = new ReentrantLock();
        // Acquire the lock
        boolean tryLock=lock.tryLock();
        boolean trylock_timeout=lock.tryLock(5,TimeUnit.SECONDS);
        if(trylock_timeout) {
            try {
                // recommended to use lcock with try/catch block
                lock.lock();
                // Access the shared resource
            } finally {
                // Release the lock_è
                lock.unlock();
            }
        }
    }
}

