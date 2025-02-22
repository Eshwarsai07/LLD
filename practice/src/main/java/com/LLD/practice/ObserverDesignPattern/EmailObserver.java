package com.LLD.practice.ObserverDesignPattern;

public class EmailObserver implements Stocksobserver{

	public String email;
	StocksObservable so;
	
	public EmailObserver(String email,StocksObservable so) {
		this.email = email;
		this.so = so;
	}
	
	@Override
	public void update() {
		
		System.out.println("Email has been sent to "+email+" as stock was arrived with new load");
	}

}
