package com.LLD.practice.twobeans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFile {

	@Bean
	public User bean1() {
		return new User("Eshwar",10);
	}
	
	@Bean
	public User bean2() {
		return new User("Sai",10);
	}
}
