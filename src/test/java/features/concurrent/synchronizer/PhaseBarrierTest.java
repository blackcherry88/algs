package features.concurrent.synchronizer;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Phaser;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class PhaseBarrierTest {

    @Test
    public void useAsCountDownLatch() {
        final Phaser phaser = new Phaser(1); // register itself

        final int size = 5;
        //register phase tasks
        for(int i = 0; i < size; ++i) {
            phaser.register();
            new Thread(()-> {
                // must wait for size + 1, so it is blocked
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + " starts...");
                try {sleep(1000);} catch (InterruptedException e) {}
                System.out.println(Thread.currentThread().getName() + " is done");
            }, "worker"+i).start();
        }

        // size + 1 arrives, then we deregister self
        phaser.arriveAndDeregister();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void useAsOneShot() {
        final Phaser entryPhaser = new Phaser(1); // register itself
        final int size = 5;
        final Phaser exitPhaser = new Phaser(size);

        //register phase tasks
        for(int i = 0; i < size; ++i) {
            entryPhaser.register();
            new Thread(()-> {
                // must wait for size + 1, so it is blocked
                entryPhaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + " starts...");
                try {sleep(1000);} catch (InterruptedException e) {}
                System.out.println(Thread.currentThread().getName() + " is done");
                exitPhaser.arrive();
            }, "worker"+i).start();
        }

        // size + 1 arrives, then we deregister self
        entryPhaser.arriveAndDeregister();
        int phase = exitPhaser.getPhase();
        System.out.println("Wait for phase " + phase + " done");
        exitPhaser.awaitAdvance(phase);
        int nextPhase = 1;
        assertEquals(nextPhase, exitPhaser.getPhase());
    }

    @Test
    public void useAsCyclicTasks() {
        final int size = 5;
        final int iterations = 10;
        //one more tasks for the controller thread
        final Phaser phaser = new Phaser(size+1) {
            protected boolean onAdvance(int phase, int regParties) {
                return phase == iterations || regParties == 0;
            }
        };

        //register phase tasks
        for(int i = 0; i < size; ++i) {
            new Thread(()-> {
                var name = Thread.currentThread().getName();
                while (!phaser.isTerminated()) {
                    // must wait for size + 1, so it is blocked
                    phaser.arriveAndAwaitAdvance();
                    var p = phaser.getPhase();
                    if (p < 0)
                        break;
                    System.out.println(name + " starts phase " + p);
                    try {sleep(1000);} catch (InterruptedException e) {}
                    System.out.println(name + " finishes phase " + p);
                }
            }, "worker"+i).start();
        }

        while(!phaser.isTerminated()) phaser.arriveAndAwaitAdvance();
    }

    @Test
    public void testParentChildren() {
        Phaser root = new Phaser(1);
        Phaser c1 = new Phaser(root, 4);
        Phaser c2 = new Phaser(root, 5);
        Phaser c3 = new Phaser(c2, 0);
        System.out.println(root);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        int expectedRootParties = 3;
        assertEquals(expectedRootParties, root.getRegisteredParties());
    }

}