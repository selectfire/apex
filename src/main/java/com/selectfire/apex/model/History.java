package com.selectfire.apex.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "history")
public class History {

	@Id
	private int id;

	private LocalDateTime createdAt;

	private int platformId;

	private String username;

	@Column(columnDefinition = "BLOB")
	private String response;
}
