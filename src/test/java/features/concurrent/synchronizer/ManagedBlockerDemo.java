package features.concurrent.synchronizer;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class ManagedBlockerDemo {

    @Test
    public void testManagedBlocker() throws InterruptedException {
        BlockingQueue<String> bq = new ArrayBlockingQueue<String>(2);
        var expected = "A";
        bq.put(expected);
        bq.put("B");
        System.out.println("Queue is " + bq);
        QueueManagedBlocker<String> blocker  =  new QueueManagedBlocker<String>(bq);
        var pool = ForkJoinPool.commonPool();
        pool.execute(() -> {
            try {
                ForkJoinPool.managedBlock(blocker);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        while (pool.getQueuedSubmissionCount() > 0) {
            System.out.println(pool.getPoolSize() + " " + pool.getQueuedSubmissionCount() + " " + pool.getQueuedTaskCount());
            sleep(1000);
        }
        assertEquals(expected, blocker.getValue());
        System.out.println(Thread.currentThread().getName() + " got " + blocker.getValue());
    }
}