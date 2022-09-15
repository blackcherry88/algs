package features.concurrent.synchronizer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class CyclicBarrierDemo implements Runnable {
    volatile int cycle = 0;
    CyclicBarrier barrier;

    public CyclicBarrierDemo(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        var name = currentThread().getName();
        while (!barrier.isBroken()) {
            try {
                barrier.await();
                sleep(1000);
                System.out.println(name + " finished cycle " + cycle);
            } catch (InterruptedException e) {
                break;
            } catch (BrokenBarrierException e) {
                System.out.println(name + " got broken exception " + e);
                break;
            }
        }
        System.out.println(name + " done");
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        int size = 5;
        List<CyclicBarrierDemo> tasks = new ArrayList<>(size);
        List<Thread> workers = new ArrayList<>(size);

        CyclicBarrier barrier = new CyclicBarrier(size + 1);
        for (int i = 0; i < size; ++i) {
            var t = new CyclicBarrierDemo(barrier);
            tasks.add(t);
            workers.add(new Thread(t, "Worker"+(i+1)));
        }
        workers.forEach(t -> t.start());

        int cycles = 10;
        for (int i = 1; i <= cycles; ++i) {
            barrier.await();
            final int c = i;
            tasks.forEach(t -> t.cycle = c);
        }
        workers.forEach(w -> w.interrupt());
        System.out.println(Thread.currentThread().getName() + "waiting for all worker exits");
        sleep(10000);
    }
}
