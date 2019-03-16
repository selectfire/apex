package com.selectfire.apex.model.apisender;

import lombok.Data;

@Data
public class StatsVo {
	private StateMetadataVo metadata;
	private int value;
	private int percentile;
	private String displayValue;
	private String displayRank;
}
