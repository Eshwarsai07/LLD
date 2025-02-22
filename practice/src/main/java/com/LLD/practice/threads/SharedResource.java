package com.LLD.practice.threads;

import java.util.ArrayList;
import java.util.List;

public class SharedResource {

	public static List<Integer> buffer = new ArrayList();;
	public static int bufferSize = 5;

	public SharedResource(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public SharedResource() {
	}

	public synchronized void addItem(int i) throws InterruptedException {
		if (buffer.size() == 5) {
			wait();
		}
		buffer.add(i);
		System.out.println("Item added to List by :" + Thread.currentThread().getName());
		notify();
	}
	
	//slmaApplicantToCinLookup

	public synchronized void getItem() throws InterruptedException {
		if (buffer.size() == 0) {
			wait();
		}
		buffer.remove(buffer.size() - 1);
		System.out.println("Item consumed from List by :" + Thread.currentThread().getName());
		notify();
	}

}
