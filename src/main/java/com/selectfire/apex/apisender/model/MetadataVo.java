package com.selectfire.apex.apisender.model;

import java.util.List;

import lombok.Data;

@Data
public class MetadataVo {

	private List<String> statsCategoryOrder;
	private int platformId;
	private String platformUserHandle;
	private String accountId;
	private String cacheExpireDate;
	private int level;
}
