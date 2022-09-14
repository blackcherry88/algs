package features.concurrent.monitor;


import static java.lang.Thread.sleep;

public class WaitDemo implements Runnable {

    @Override
    public void run() {
        var t = Thread.currentThread().getName();
        synchronized (this) {
            while(true) {
                try {
                    System.out.println(t  + " decides to wait on monitor");
                    this.wait();
                } catch (InterruptedException e) {
                    System.out.println("Does " + t + " still holds monitor? Answer: " + Thread.holdsLock(this));
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        var t = new Thread(new WaitDemo(), "Worker");
        t.start();
        var name = Thread.currentThread().getName();
        while (t.isAlive()) {
            try {
                sleep(5000);
            } catch (InterruptedException e) {
            }
            System.out.println(name + " tries to interrupt thread " + t.getName());
            t.interrupt();
        }
    }
}
