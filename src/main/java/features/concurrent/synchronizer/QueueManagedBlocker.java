package features.concurrent.synchronizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;


public class QueueManagedBlocker<T> implements ForkJoinPool.ManagedBlocker {
    static final Logger logger = LogManager.getLogger(QueueManagedBlocker.class.getName());

    final BlockingQueue<T> queue;
    volatile T value = null;
    QueueManagedBlocker(BlockingQueue<T> queue) {
        this.queue = queue;
    }
    public boolean block() throws InterruptedException {
        if (value == null)
            value = queue.take();
        System.out.println(Thread.currentThread().getName() + " is done with value "  + value);
        return true;
    }
    public boolean isReleasable() {
        return false;
    }
    public T getValue() {
        return value;
    }
}
