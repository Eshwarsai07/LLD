package com.LLD.practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueuImpl<T> {

	interface BlockQueu<T> {
		public void put(T val);

		public void remove();
	}

	class BlockQueuImpl implements BlockQueu<T> {

		ReentrantLock lock = new ReentrantLock();
		Queue<T> queue = new LinkedList<>();
		int bufferSize;

		Condition notFull;
		Condition notEmpty;

		public BlockQueuImpl(int bufferSize) {
			this.bufferSize = bufferSize;
		}

		public BlockQueuImpl() {
			this.bufferSize = 1;
			notFull = lock.newCondition();
			notEmpty = lock.newCondition();
		}

		@Override
		public void put(T val) {
			try {
				lock.lock();
				System.out.println("Lock aqcquired by " + Thread.currentThread().getName());
				while (queue.size() == bufferSize) {
					System.out.println(Thread.currentThread().getName() + " is waiting as queue is full");
					notFull.await();
				}
				queue.add(val);
				System.out
						.println("Queue size after put call " + queue.size() + " " + Thread.currentThread().getName());
				notEmpty.signalAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
//				System.out.println("Lock relased by " + Thread.currentThread().getName());
				lock.unlock();
			}

		}

		@Override
		public void remove() {
			try {
				lock.lock();
				System.out.println("Lock aqcquired by " + Thread.currentThread().getName());
				while (queue.size() == 0) {
					System.out.println(Thread.currentThread().getName() + " is waiting as queue is empty");
					notEmpty.await();

				}
				queue.poll();
				System.out.println(
						"Queue size after remove call " + queue.size() + " " + Thread.currentThread().getName());
				notFull.signalAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
//				System.out.println("Lock relased by " + Thread.currentThread().getName());
				lock.unlock();
			}
		}

	}

	class CustomThreadFactory implements ThreadFactory {

		@Override
		public Thread newThread(Runnable r) {

			return null;
		}

	}

	public static void main(String[] args) {
		BlockQueu<Integer> b = new BlockingQueuImpl<Integer>().new BlockQueuImpl();

		ExecutorService service = Executors.newFixedThreadPool(30);

		for (int i = 0; i < 2; i++) {
			service.submit(() -> {
				b.put(1);
			});
		}

		for (int i = 0; i < 2; i++) {
			service.submit(() -> {
				b.remove();
			});
		}
		
		service.shutdown();

	}
}
