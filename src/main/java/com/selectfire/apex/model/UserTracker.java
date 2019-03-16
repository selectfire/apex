package com.selectfire.apex.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_trackers")
public class UserTracker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int userId;

	private int historyId;

//	@OneToOne
//	@JoinColumn(name = "legendId")
//	private Legend legend;
	private int legendId;

	@Column(name = "`key`")
	private String key;

	private String categoryKey;

	@Column(name = "`value`")
	private double value;

	private double percentile;

	private LocalDateTime createdAt;
}
