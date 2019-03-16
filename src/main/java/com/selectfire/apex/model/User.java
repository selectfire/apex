package com.selectfire.apex.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.selectfire.apex.model.apisender.ResponseDataVo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {

	public User(ResponseDataVo responseDataVo) {
		name = responseDataVo.getData().getMetadata().getPlatformUserHandle();
	}

	@Id
	private int id;

	private String name;

	@OneToOne
	@JoinColumn(name = "platformId")
	private Platform platform;

	private String accountId;

	private int level;

	@OneToMany
	@JoinColumn(name = "userId")
	private List<UserTracker> userTracker;
}
