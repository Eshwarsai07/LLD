package com.LLD.practice;

import java.lang.reflect.Field;

public class AnnotationValidator {

	public static void main(String[] args) {
		PersonClassForCustomAnnotation p = new PersonClassForCustomAnnotation(10);
		try {
			validate(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void validate(Object obj) throws Exception {

		Class c = obj.getClass();

		for (Field f : c.getDeclaredFields()) {
			if (f.isAnnotationPresent(MyCustomAnnotation.class)) {
				MyCustomAnnotation annotation = f.getAnnotation(MyCustomAnnotation.class);
				f.setAccessible(true);
				Object val = f.get(obj);

				if (val instanceof String && ((String) val).length() > annotation.length()) {
					throw new Exception(obj.getClass().getSimpleName() + " " + f.getName() + " Exceeded the max lenght");
				}

				if (annotation.required() && (val == null || val.toString().isEmpty())) {
					throw new Exception(f.getName() + " is required but is missing.");
				}
			}
		}

	}

}
