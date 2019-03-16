package com.selectfire.apex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.selectfire.apex.model.UserTracker;

@Repository
public interface UserTrackerRepository extends JpaRepository<UserTracker, Integer> {

}
