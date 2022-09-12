package features.concurrent;

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class PingPong implements Runnable {
    private int count = 0;
    private final Semaphore mySemaphore;
    private final Semaphore otherSemaphore;

    public PingPong(Semaphore mySemaphore, Semaphore otherSemaphore) {
        this.mySemaphore = mySemaphore;
        this.otherSemaphore = otherSemaphore;
    }

    public void run() {
        while (true) {
            try {
                mySemaphore.acquire();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " caught interrupted exception");
                break;
            }
            System.out.println(Thread.currentThread().getName() + " " + count++);
            otherSemaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Semaphore pingSemaphore = new Semaphore(1);
        Semaphore pongSemaphore = new Semaphore(0);

        Thread ping = new Thread(new PingPong(pingSemaphore, pongSemaphore), "Ping");
        Thread pong = new Thread(new PingPong(pongSemaphore, pingSemaphore), "Pong");

        pong.start();
        ping.start();

        sleep(500);
        System.exit(0);
    }
}
