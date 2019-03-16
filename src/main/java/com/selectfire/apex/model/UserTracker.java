package com.selectfire.apex.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_trackers")
public class UserTracker {

	@Id
	private int id;

	private int userId;

	private int legendId;

	private String key;

	private String categoryKey;

	private float value;

	private float percentile;
}
