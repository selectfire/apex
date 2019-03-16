package com.selectfire.apex.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.selectfire.apex.model.Hello;

@RestController
public class HelloController {
	
	@RequestMapping("/")
	public Hello hello() {
		Hello hello = new Hello();
		hello.setStr("hello");
		return hello;
	}
}
