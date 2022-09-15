package features.concurrent.synchronizer;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point read() {
        return new Point(x, y);
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class StampedLockTest {

    protected void createWriteTask(final ExecutorService pool, final Point sharedPt, final StampedLock lock) {
        pool.execute(()-> {
            var random = new Random();
            final int maxRange = 4096;
            while (!pool.isShutdown()) {
                final int x = random.nextInt(maxRange);
                final int y = random.nextInt(maxRange);
                final long stamp = lock.writeLock();
                try {
                    sharedPt.set(x, y);
                    System.out.printf("Writer update share point %s\n", sharedPt);
                } finally {
                    lock.unlock(stamp);
                }

                try {
                    sleep(random.nextInt(100)+1);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
    }

    protected void createOptimisticReader(final ExecutorService pool, final Point sharedPt, final StampedLock lock) {
        pool.execute(()-> {
            final var random = new Random();
            long stamp = lock.tryOptimisticRead();
            Point pt = sharedPt.read();
            try {
                sleep(random.nextInt(10)+1);
            } catch (InterruptedException e) {
            }
            if (!lock.validate(stamp)) {
                stamp = lock.readLock();
                try {
                    pt = sharedPt.read();
                } finally {
                    lock.unlock(stamp);
                }
                System.out.println("Optimistic read failed " + pt);
            } else {
                System.out.println("Optimistic read succeeded " + pt);
            }
        });
    }

    @Test
    public void testOptimisticReadWithoutWrite() {
        final var sharedPt = new Point(2, 3);
        final var lock = new StampedLock();

        var pool = Executors.newFixedThreadPool(5);
        final int tasks = 10;
        IntStream.range(0, tasks).forEach(i -> createOptimisticReader(pool, sharedPt, lock));
    }

    @Test
    public void testOptimisticReadWithWrite() throws InterruptedException {
        final var sharedPt = new Point(2, 3);
        final var lock = new StampedLock();

        var pool = Executors.newFixedThreadPool(5);
        createWriteTask(pool, sharedPt, lock);

        final int tasks = 10;
        IntStream.range(0, tasks).forEach(i -> createOptimisticReader(pool, sharedPt, lock));
        sleep(3000);
        pool.shutdownNow();
    }
}