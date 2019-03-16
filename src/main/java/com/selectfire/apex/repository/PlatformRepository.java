package com.selectfire.apex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.selectfire.apex.model.Platform;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Integer> {

}
