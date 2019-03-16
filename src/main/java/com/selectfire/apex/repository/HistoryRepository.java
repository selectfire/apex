package com.selectfire.apex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.selectfire.apex.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {

}
