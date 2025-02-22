package com.LLD.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintAlternateLetterAndNumber {

	private static Object lock = new Object();
	private static boolean isNumberTurn = false;

	public static void main(String[] args) {

		ExecutorService service = Executors.newFixedThreadPool(2);

		Thread t1 = new Thread(() -> {

			for (int i = 1; i < 27; i++) {
				synchronized (lock) {
					while (!isNumberTurn) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.print(i + " ");
					isNumberTurn = false;
					lock.notifyAll();
				}
			}
		});

		Thread t2 = new Thread(() -> {

			for (char i = 'a'; i <= 'z'; i++) {
				synchronized (lock) {
					while (isNumberTurn) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.print(i + " ");
					isNumberTurn = true;
					lock.notifyAll();
				}
			}
		});

		service.submit(() -> {

			for (char i = 'a'; i <= 'z'; i++) {
				synchronized (lock) {
					while (isNumberTurn) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.print(i + " ");
					isNumberTurn = true;
					lock.notifyAll();
				}
			}
		});

		service.submit(() -> {

			for (int i = 1; i < 27; i++) {
				synchronized (lock) {
					while (!isNumberTurn) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.print(i + " ");
					isNumberTurn = false;
					lock.notifyAll();
				}
			}
		});
		
		
		service.shutdown();
//		t1.start();
//		t2.start();

	}
}
