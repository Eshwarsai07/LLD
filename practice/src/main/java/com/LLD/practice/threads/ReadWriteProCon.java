package com.LLD.practice.threads;

import java.util.concurrent.ArrayBlockingQueue;

public class ReadWriteProCon {

	public static void main(String[] args) {
		SharedResource resource = new SharedResource();
		SharedResource resource2 = new SharedResource();
		Thread t1 = new Thread(() -> {
			for(int i=1;i<=5;i++) {
				try {
					Thread.sleep(2000);
					resource.addItem(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		Thread t2 = new Thread(() -> {
			for(int i=1;i<=5;i++) {
				try {
					Thread.sleep(1000);
					resource2.addItem(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		Thread t3 = new Thread(() -> {
			for(int i=1;i<=5;i++) {
				try {
					resource2.getItem();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
//		ArrayBlockingQueue<E>
		
		t1.start();
		t2.start();
		t3.start();
	}
}
