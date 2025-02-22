package com.LLD.practice;

public class ThreadCreation {

	@FunctionalInterface
	interface TriFunc<T, K, L,M>  {

		public M accept(T val1, K val2, L val3);
	}

	public static void main(String[] args) {

		TriFunc<Integer,Integer,Integer,Integer> t = (a,b,c) ->  a+b+c;
		System.out.println(t.accept(10, 10, 10));
	}
}
