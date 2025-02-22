package com.LLD.practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice4 {

	class A {
		public void method() throws Exception {

		}

		public static void method2() {
			System.out.println("class a method2");
		}
	}

	class B extends A {
		@Override
		public void method() throws IOException {

		}

		public static void method2() {
			System.out.println("class b method2");
		}
	}

	public static void main(String[] args) {
		List<String> l = new ArrayList(Arrays.asList(1, 2, 5));
		l.remove(0);
		System.out.println(l);
		new Practice4(). new B().method2();

	}
}
