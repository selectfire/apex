package com.selectfire.apex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.selectfire.apex.model.Legend;

@Repository
public interface LegendRepository extends JpaRepository<Legend, Integer> {

}
