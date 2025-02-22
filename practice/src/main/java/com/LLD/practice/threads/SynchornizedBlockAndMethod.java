package com.LLD.practice.threads;

import java.util.concurrent.locks.ReentrantLock;

class BankingSynchronized {

	private int balance;

	public BankingSynchronized(int balance) {
		this.balance = balance;
	}

	public void deposit(int amt, ReentrantLock lock1) {
		synchronized (lock1) {
			int updatedBal = balance + amt;
			System.out.println(Thread.currentThread().getName() + " is trying to deposit the amount " + amt
					+ " from current balance " + balance);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			balance = updatedBal;
		}
		System.out.println(Thread.currentThread().getName() + " deposited the amount and current balance " + balance);
	}

	public void withdraw(int amt, ReentrantLock lock2) {
		synchronized (lock2) {
			if (amt <= balance) {
				int updatedBal = balance - amt;
				System.out.println(Thread.currentThread().getName() + " is trying to withdraw the amount " + amt
						+ " from current balance " + balance);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				balance = updatedBal;
			}
			System.out
					.println(Thread.currentThread().getName() + " withdrawn the amount and current balance " + balance);
		}
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}

public class SynchornizedBlockAndMethod {

	public static void main(String[] args) {

		BankingSynchronized bankAccount = new BankingSynchronized(100);

		ReentrantLock lock1 = new ReentrantLock();
		ReentrantLock lock2 = new ReentrantLock();
		Thread t1 = new Thread(() -> {
			bankAccount.deposit(50,lock1);
		}, "deposit 1");

		Thread t2 = new Thread(() -> {
			bankAccount.deposit(40,lock1);
		}, "deposit 2");

		Thread t3 = new Thread(() -> {
			bankAccount.withdraw(20,lock2);
		}, "withdraw 1");

		Thread t4 = new Thread(() -> {
			bankAccount.withdraw(60,lock2);
		}, "withdraw 2");

		t2.start();
		t1.start();
		t4.start();
		t3.start();

		try {
			t2.join();
			t1.join();
			t4.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("final balance after all threads execution " + bankAccount.getBalance());

	}
}
