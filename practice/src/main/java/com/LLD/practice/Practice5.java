package com.LLD.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Practice5 {

	static class GenericExample<T>{
		T obj;
		GenericExample(T obj) { this.obj = obj; } // constructor
	    public T getObject() { return this.obj; }
	    
	    public List<T> singletonList(T value) {
	    	return Collections.singletonList(value);
	    }
	    
	    public void method2(T element) {
	    	System.out.println(element.getClass().getName()
                    + " = " + element);
	    }
	    
	    static {
	    	System.out.println("Static Block");
	    }
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		GenericExample<Integer> genericExample = new GenericExample("String");
		System.out.println(genericExample.getObject());
		genericExample.method2(10);
		System.out.println(genericExample.singletonList(20));
		
		Class.forName("com.LLD.practice.Practice5.GenericExample");
		
	}
}
