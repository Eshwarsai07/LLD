package com.LLD.practice.ObserverDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class OnePlusStocksObservable implements StocksObservable {
	List<Stocksobserver> li;
	int stocks = 0;

	public OnePlusStocksObservable() {
		li = new ArrayList();
	}

	@Override
	public void setData(int stock) {
		if (stocks == 0) {
			notfyAll();
		}
		stocks = stock;
	}

	@Override
	public int getData() {
		return stocks;
	}

	@Override
	public void add(Stocksobserver so) {
		li.add(so);
	}

	@Override
	public void notfyAll() {

		for (Stocksobserver so : li) {
			so.update();
		}

	}

}
