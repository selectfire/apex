package com.selectfire.apex.apisender.model;

import lombok.Data;

@Data
public class StateMetadataVo {
	
	private String key;
	private String name;
	private String categoryKey;
	private String categoryName;
	private Boolean isReversed;
}
