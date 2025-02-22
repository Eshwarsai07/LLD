package com.LLD.practice.ObserverDesignPattern;

import java.util.List;

public interface StocksObservable {
	
	public void add(Stocksobserver so);
	public void setData(int stock);
	public void notfyAll();
	public int getData();
	
}
