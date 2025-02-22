package com.LLD.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PrintTask implements Runnable {
    private static final int THREAD_COUNT = 5;
    private static final int PRINT_LIMIT = 10; // How many rounds each thread prints
    private static int currentIndex = 0;

    private final int threadId;
    private static final Lock lock = new ReentrantLock();
    private static final Condition[] conditions = new Condition[THREAD_COUNT];

    public PrintTask(int threadId) {
        this.threadId = threadId;
    }

    static {
        for (int i = 0; i < THREAD_COUNT; i++) {
            conditions[i] = lock.newCondition();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < PRINT_LIMIT; i++) {
            lock.lock();
            try {
                while (currentIndex % THREAD_COUNT != threadId) {
                    conditions[threadId].await(); // Wait if it's not this thread's turn
                }

                // Print statement
                System.out.println("Thread-" + threadId + " prints: " + (i + 1));

                // Move to the next thread
                currentIndex++;

                // Signal the next thread
                conditions[currentIndex % THREAD_COUNT].signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

public class SleepBasedThreadPrinting {
    public static void main(String[] args) {
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new PrintTask(i), "Thread-" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}

