package features.concurrent;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class VolatileDemo {
    /**
     * This demo that volatile while ensuring visibility, not guarantee atomic (or avoid race condition)
     */
    public volatile int inc = 0;

    public void increase() {
        //This is no atomic operation
        inc++;
    }

    public static void main(String[] args) {
        final VolatileDemo test = new VolatileDemo();
        int numThreads = 10;

        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < numThreads; i++) {
            var t = new Thread(() -> {
                for (int j = 0; j < 1000; j++)
                    test.increase();
            });
            threads.add(t);
        }
        threads.forEach(Thread::start);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
        }


        for (var t: threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(test.inc);
    }
}
