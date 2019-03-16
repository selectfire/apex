package com.selectfire.apex.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "legends")
public class Legend {

	@Id
	private int id;

	private String name;

	private String icon;
}
