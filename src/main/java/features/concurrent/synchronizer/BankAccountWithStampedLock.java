package features.concurrent.synchronizer;

import java.util.concurrent.locks.StampedLock;


public class BankAccountWithStampedLock {
    private final StampedLock lock = new StampedLock();
    private double balance;
    public void deposit(double amount) {
        long stamp = lock.writeLock();
        try {
            balance = balance + amount;
        } finally {
            lock.unlockWrite(stamp);
        }
    }
    public double getBalance() {
        long stamp = lock.readLock();
        try {
            return balance;
        } finally {
            lock.unlockRead(stamp);
        }
    }
}
