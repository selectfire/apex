package com.selectfire.apex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selectfire.apex.model.User;
import com.selectfire.apex.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	private UserRepository userRepository;

	private TrackerService trackerService;

	@Autowired
	public UserService(UserRepository userRepository, TrackerService trackerService) {
		this.userRepository = userRepository;
		this.trackerService = trackerService;
	}

	public List<User> list() {
		return userRepository.findAll();
	}

	public User get(String name, int platformId, boolean updatable) {

		if (updatable) {
			log.info("{}", "updatable");
			User user = trackerService.get(name, platformId);

		}

		return get(name, platformId);
	}

	public User get(String name, int platformId) {
		return userRepository.findByNameAndPlatformId(name, platformId);
	}

	public User update(String name, int platformId) {
		return userRepository.findByName(name);
	}

	public void save(User user) {
		userRepository.save(user);
	}
}
