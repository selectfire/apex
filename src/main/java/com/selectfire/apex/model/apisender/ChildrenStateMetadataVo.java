package com.selectfire.apex.model.apisender;

import lombok.Data;

@Data
public class ChildrenStateMetadataVo {
	
	private String key;
	private String name;
	private String categoryKey;
	private String categoryName;
	private Boolean isReversed;
}
