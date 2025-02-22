package com.LLD.practice.twobeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerExample {

	@Autowired
	User user;
}
