package com.LLD.practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue2 {

	interface BlockingQ {
		void put(int val);

		void remove();
	}

	public static class BlockingQImpl implements BlockingQ {

		Queue<Integer> q;
		int buffersize;
		ReentrantLock lock;
		Condition notFull;
		Condition notEmpty;

		@Override
		public void put(int val) {
			try {
				lock.lock();
				System.out.println("Lock acquired by -->" + Thread.currentThread().getName());
				while (q.size() == buffersize) {
					System.out.println("queue is full waiting for consumers to consume");
					notFull.await();
				}
				q.add(val);
				System.out.println("Queue size after put call " + q.size() + " " + Thread.currentThread().getName());
				notEmpty.signalAll();
			} catch (Exception e) {
				System.out.println("Exception occured during put call" + e);
			} finally {
				lock.unlock();
			}
		}

		public BlockingQImpl(int buffersize) {
			this.buffersize = buffersize;
			q = new LinkedList<>();
			lock = new ReentrantLock();
			notFull = lock.newCondition();
			notEmpty = lock.newCondition();
		}

		public BlockingQImpl() {
			this(5);
		}

		@Override
		public void remove() {
			try {
				lock.lock();
				System.out.println("Lock acquired by -->" + Thread.currentThread().getName());
				while (q.isEmpty()) {
					System.out.println("queue is empty waiting for producers to produce");
					notEmpty.await();
				}
				q.poll();
				System.out.println("Queue size after remove call " + q.size() + " " + Thread.currentThread().getName());
				notFull.signalAll();
			} catch (Exception e) {
				System.out.println("Exception occured" + e);
			} finally {
				lock.unlock();
			}
		}

	}

	public static void main(String[] args) {
		ExecutorService ex = Executors.newFixedThreadPool(10);
		BlockingQ queue = new BlockingQImpl();

		for (int i = 0; i < 5; i++) {
			ex.submit(() -> {
				queue.put(1);
			});
		}

		for (int i = 0; i < 5; i++) {
			ex.submit(() -> {
				queue.remove();
			});
		}

		ex.shutdown();

	}
}
