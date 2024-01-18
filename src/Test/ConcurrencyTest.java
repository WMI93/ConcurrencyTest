package Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrencyTest {
    public static void main(String[] args) {
        (new ExtendsThreadClass()).run();// execute sequential format
        (new ExtendsThreadClass()).start();// execute multithread format
         (new ExtendsThreadClass()).join();// wait until this thread to doe ==> join(0)

        //example using executor
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.shutdownNow();
        executor.submit(() ->System.out.println("callable interface"));

        (new Thread(new implementsRunnableInterface())).start(); // old method 1
        executor.execute(new implementsRunnableInterface());// using thread Pool

        (new ExtendsThreadClass).start(); // old method 2
        executor.execute(new ExtendsThreadClass());// using thread Pool
        executor.shutdownNow();

        ExecutorService executorService=Executors.newSingleThreadExecutor();
        // try catch (InterruptedException) must be applied for sleep method
        Runnable runnableTask = () -> {TimeUnit.MILLISECONDS.sleep(300);};
        Callable<String> callableTask = () -> {TimeUnit.MILLISECONDS.sleep(300);
            return "Task's execution";};

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);// * 3

        executorService.execute(runnableTask);//example fo execute (Runnable task)
        executorService.submit(callableTask);////example to submit(Collabke <T> task)
        executorService.submit(runnableTask);//example to submit (Runnable task)

        try {
            //get an collection of Callable Task instance
            List<Future<String>> futures = executorService.invokeAll(callableTasks);
            String result = executorService.invokeAny(callableTasks);//return one result
        } catch (InterruptedException e) {
            System.out.println("invokeAll method is checked exception");
        } catch (ExecutionException e) {
            System.out.println("invokeAny method is checked exception");
        }


        // Example create an instance of ScheduledExecutorService
        // using Excutors class factory
        ScheduledExecutorService ses=
                Executors.newSingleThreadScheduledExecutor();

        //example schedule method with Callable task
        Future<String> resultFutureC =ses.schedule(callableTask, 1, TimeUnit.SECONDS);

        //example schedule method with Runaable task
        ScheduledFuture<?> resultFutureR =ses.schedule(runnableTask, 1, TimeUnit.SECONDS);

        //example running a task after an initial delay of 100 milliseconds
        //And after that, it will run the same task every 450 milliseconds:
        ses.scheduleAtFixedRate(runnableTask, 100, 450, TimeUnit.MILLISECONDS);

        //Example : the execution will 150-millisecond
        //between the end of the current execution and the start of next one
        ses.scheduleWithFixedDelay(runnableTask, 100, 150, TimeUnit.MILLISECONDS);

    }
}
class ExtendsThreadClass extends Thread {}
class implementsRunnableInterface implements Runnable {
    @Override
    void run() {}
}


//interface ExecutorService extends Executor {}

//interface ScheduledExecutorService extends ExecutorService {}

//class ThreadPoolExecutor extends AbstractExecutorService implements ExecutorService {}

//class ScheduledThreadPoolExecutor extends ThreadPoolExecutor implements ScheduledExecutorService {...}

