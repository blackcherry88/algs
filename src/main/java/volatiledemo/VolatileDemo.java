package volatiledemo;

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
        for(int i=0;i<10;i++){
            new Thread(() -> {
                for(int j=0;j<1000;j++)
                    test.increase();
            }).start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            Thread.yield();
        }
        System.out.println(test.inc);
    }
}
