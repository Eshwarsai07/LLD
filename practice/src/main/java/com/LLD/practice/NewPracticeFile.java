package com.LLD.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewPracticeFile {

	private static Object lock = new Object();
	private static boolean printLetter = false;

	public static void main(String[] args) {
		
		Logger l = LoggerFactory.getLogger(NewPracticeFile.class);
		
		l.info("Info logger");
		l.error("Error msg");
		
		Thread t1 = new Thread(() -> {

			for (int i = 1; i <= 26; i++) {
				synchronized (lock) {
					if (printLetter) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.print(i + " ");
					// Thread.sleep(2000);
					printLetter=true;
					lock.notify();
				}
			}
		});

		Thread t2 = new Thread(() -> {

			for (char i = 'a'; i <= 'z'; i++) {
				synchronized (lock) {
					if (!printLetter) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.print(i + " ");
					// Thread.sleep(2000);
					printLetter=false;
					lock.notify();
				}
			}
		});

		t1.start();
		t2.start();
	}
}
