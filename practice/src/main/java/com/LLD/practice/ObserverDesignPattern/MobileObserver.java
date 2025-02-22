package com.LLD.practice.ObserverDesignPattern;

public class MobileObserver implements Stocksobserver{
	public String mobile;
	StocksObservable so;
	
	public MobileObserver(String mobile,StocksObservable so) {
		this.mobile = mobile;
		this.so = so;
	}
	
	@Override
	public void update() {
		System.out.println("Mssg has been sent to "+mobile+" as stock was arrived with new load");
	}

}
