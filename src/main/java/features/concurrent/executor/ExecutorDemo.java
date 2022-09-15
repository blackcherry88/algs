package features.concurrent.executor;

import features.concurrent.PrimeRunnable;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.lang.Thread.sleep;

public class ExecutorDemo {

    public static void main(String[] args) throws InterruptedException {
        long MAX_VALUE = 1000000000L;
        int count = 20;

        ThreadFactory threadFactory = runnable -> {
            Thread thread = new Thread(runnable);

            // Set a handler for any uncaught exception.
            thread.setUncaughtExceptionHandler((thr, ex) -> {
                System.out.println("Encountered an exception:"
                        + ex
                        + " for thread "
                        + thread);
            });
            return thread;
        };

        int availableProcessors = Runtime
                .getRuntime()
                .availableProcessors();
        System.out.println("Available processor " + availableProcessors);
        Executor executor =
            Executors.newFixedThreadPool(
                    availableProcessors > 2? availableProcessors-2: availableProcessors,
                    threadFactory);

        // Create "count" random values and check to see if they
        // are prime.
        new Random()
                // Generate "count" random between sMAX_VALUE - count
                // and sMAX_VALUE.
                .longs(count, MAX_VALUE - count, MAX_VALUE)

                // Convert each random number into a PrimeRunnable and
                // execute it.
                .forEach(randomNumber ->
                        executor.execute(new PrimeRunnable(randomNumber)));

        sleep(5000);
        System.exit(0);
    }

}
