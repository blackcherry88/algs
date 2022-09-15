package features.concurrent.executor;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ExecutorTest {

    @Test
    public void testFutureTask() {
        var pool = Executors.newFixedThreadPool(2);
        Callable<Integer> c = () -> {
            return IntStream.range(0, 100).sum();
        };

        var f = pool.submit(c);
        try {
            var r = f.get();
            System.out.println("Result is " + r);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdownNow();
    }

}