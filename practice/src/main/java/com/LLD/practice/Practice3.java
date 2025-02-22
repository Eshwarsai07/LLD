package com.LLD.practice;

import java.util.ArrayList;
import java.util.List;

public class Practice3 {

	static class SharedResoure {

		List<Integer> l;
		int bufferSize;

		public SharedResoure() {
			l = new ArrayList();
			bufferSize = 5;
		}

		synchronized void addItem(int i) throws InterruptedException {
			if (l.size() >= 5) {
				wait();
			}
			l.add(i);
			System.out.println("Item added to List by :" + Thread.currentThread().getName());
			notify();
		}

		synchronized void getItem() throws InterruptedException {
			if (l.size() == 0) {
				wait();
			}
			l.remove(l.size() - 1);
			System.out.println("Item consumed from List by :" + Thread.currentThread().getName());
			notify();
		}
	}

	public static void main(String[] args) {

		SharedResoure resource = new SharedResoure();
		Thread t1 = new Thread(() -> {
			for (int i = 1; i <= 5; i++) {
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
			for (int i = 1; i <= 5; i++) {
				try {
					Thread.sleep(1000);
					resource.addItem(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread t3 = new Thread(() -> {
			for (int i = 1; i <= 5; i++) {
				try {
					resource.getItem();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
		t3.start();
	}
}
