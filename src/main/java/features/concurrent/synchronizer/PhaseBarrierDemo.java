package features.concurrent.synchronizer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class PhaseBarrierDemo implements Runnable {
    private Phaser phaser;

    public PhaseBarrierDemo(Phaser phaser) {
        this.phaser = phaser;
    }


    @Override
    public void run() {
        var name = currentThread().getName();
        while (!phaser.isTerminated()) {
            try {
                int phase = phaser.arriveAndAwaitAdvance();
                sleep(1000);
                System.out.println(name + " finished cycle " + phase);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println(name + " done");
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        int size = 5;
        List<Thread> workers = new ArrayList<>(size);

        Phaser phaser = new Phaser(size + 1);
        IntStream.range(0, size).forEach(i -> {
            var t = new PhaseBarrierDemo(phaser);
            workers.add(new Thread(
                    new PhaseBarrierDemo(phaser),
                    "Worker"+(i+1)));

        });
        workers.forEach(t -> t.start());

        int cycles = 10;
        for (int i = 1; i <= cycles; ++i) {
            phaser.arriveAndAwaitAdvance();
        }

        phaser.forceTermination();
        System.out.println(Thread.currentThread().getName() + "waiting for all worker exits");
        sleep(10000);
    }
}
