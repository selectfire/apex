package com.selectfire.apex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selectfire.apex.model.User;
import com.selectfire.apex.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> list() {
		return userRepository.findAll();
	}

	public User get(String name) {
		return userRepository.findByName(name);
	}

	public User update(String name) {
		return userRepository.findByName(name);
	}
}
