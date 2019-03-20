package com.selectfire.apex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.selectfire.apex.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {

	@Query(value = "SELECT MAX(t.id) FROM History t WHERE t.username = :username;")
	public int getMax(@Param("username") String username);
}
