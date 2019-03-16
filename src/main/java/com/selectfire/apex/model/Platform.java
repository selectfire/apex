package com.selectfire.apex.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "platform")
public class Platform {

	@Id
	private int id;

	private String name;
}
