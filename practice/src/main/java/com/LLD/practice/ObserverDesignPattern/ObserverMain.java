package com.LLD.practice.ObserverDesignPattern;

public class ObserverMain {
	public static void main(String[] args) {

		IphoneStocksObservable iphoneStocks = new IphoneStocksObservable();
		Stocksobserver so1 = new EmailObserver("eashwarsai5@gmail.com", iphoneStocks);
		Stocksobserver so2 = new EmailObserver("ganesh@gmail.com", iphoneStocks);
		Stocksobserver so3 = new MobileObserver("9959789336", iphoneStocks);

		OnePlusStocksObservable onePlusStocks = new OnePlusStocksObservable();
		Stocksobserver onePlusObserver1 = new EmailObserver("John@gmail.com", iphoneStocks);
		Stocksobserver onePlusObserver2 = new MobileObserver("9945789336", iphoneStocks);
		iphoneStocks.add(so1);
		iphoneStocks.add(so2);
		iphoneStocks.add(so3);

		iphoneStocks.setData(25);
		iphoneStocks.setData(0);
		
		onePlusStocks.add(onePlusObserver1);
		onePlusStocks.add(onePlusObserver2);
		
		onePlusStocks.setData(30);

	}
}
