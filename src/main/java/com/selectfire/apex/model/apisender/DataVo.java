package com.selectfire.apex.model.apisender;

import java.util.List;

import lombok.Data;

@Data
public class DataVo {

	/**user id */
	private String id;
	/**user type ex> player */
	private String type;
	/**legend info */
	private List<ChildrenVo> children;
	/**legend category data, user id, date, level */
	private MetadataVo metadata;
	/**kill cnt, pct */
	private List<StatsVo> stats;
}
