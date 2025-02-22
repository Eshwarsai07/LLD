package com.LLD.practice.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InitiBinderExample {

	@InitBinder
	protected void initBinder(DataBinder binder) {

		binder.registerCustomEditor(String.class, "FirstName", new FirstNamePropertyEditor());
	}

	public String getUserDetails(@RequestParam(name = "firstName") String frstName) {
		return "";
	}

	public class FirstNamePropertyEditor extends PropertyEditorSupport {

		public void setAsText(String text) {

			setValue(text.trim().toLowerCase());

		}
	}

}
