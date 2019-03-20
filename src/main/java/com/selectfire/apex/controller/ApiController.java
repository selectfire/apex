package com.selectfire.apex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.selectfire.apex.model.User;
import com.selectfire.apex.service.UserService;

@RestController
public class ApiController {

	private UserService userService;

	@Autowired
	public ApiController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public User get(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "platform", required = true) int platform,
			@RequestParam(value = "updatable", required = false) boolean updatable) {

		User user = userService.get(name, platform, updatable);

		return user;
	}
}
