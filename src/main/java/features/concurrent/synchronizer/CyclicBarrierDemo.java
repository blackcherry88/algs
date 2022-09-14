package features.concurrent.synchronizer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class CyclicBarrierDemo implements Runnable {
    volatile int cycle = 0;
    CyclicBarrier entryBarrier;
    CyclicBarrier exitBarrier;

    public CyclicBarrierDemo(CyclicBarrier entryBarrier, CyclicBarrier exitBarrier) {
        this.entryBarrier = entryBarrier;
        this.exitBarrier = exitBarrier;
    }

    @Override
    public void run() {
        var name = currentThread().getName();
        while (true) {
            try {
                entryBarrier.await();
                sleep(1000);
                System.out.println(name + " finished cycle " + cycle);
                exitBarrier.await();
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

        CyclicBarrier entryBarrier = new CyclicBarrier(size + 1);
        CyclicBarrier exitBarrier = new CyclicBarrier(size + 1);
        for (int i = 0; i < size; ++i) {
            var t = new CyclicBarrierDemo(entryBarrier, exitBarrier);
            tasks.add(t);
            workers.add(new Thread(t, "Worker"+(i+1)));
        }
        workers.forEach(t -> t.start());

        int cycles = 10;
        for (int i = 1; i <= cycles; ++i) {
            entryBarrier.await();
            exitBarrier.await();
            final int c = i;
            tasks.forEach(t -> t.cycle = c);
        }

        workers.forEach(w -> w.interrupt());
        System.out.println(Thread.currentThread().getName() + "waiting for all worker exits");
        sleep(10000);
    }
}
