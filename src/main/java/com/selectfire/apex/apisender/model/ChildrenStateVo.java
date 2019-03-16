package com.selectfire.apex.apisender.model;

import lombok.Data;

@Data
public class ChildrenStateVo {
	
	private ChildrenStateMetadataVo metadata;
	private int value;
	private double percentile;
	private String displayValue;
	private String displayRank;
}
