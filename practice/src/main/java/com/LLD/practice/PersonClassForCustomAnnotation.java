package com.LLD.practice;

public class PersonClassForCustomAnnotation {

	@MyCustomAnnotation(name = "Full Name",length = 100,required = true)
	private String name;
	
	@MyCustomAnnotation(name = "Age",length = 3,required = true)
	private int age;

	public PersonClassForCustomAnnotation(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public PersonClassForCustomAnnotation(String name) {
		this.name = name;
	}

	public PersonClassForCustomAnnotation(int age) {
		this.age = age;
	}
}
