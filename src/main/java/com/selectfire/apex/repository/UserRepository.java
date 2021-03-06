package com.selectfire.apex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.selectfire.apex.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByName(String name);

	public User findByNameAndPlatformId(String name, int platformId);

	public User findByNameAndPlatformIdAndMaxHistoryId(String name, int platformId, int historyId);
}
