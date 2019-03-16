package com.selectfire.apex.model.apisender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ChildrenVo {

	private String id;
	private String type;
	private ChildrenMetadataVo metadata;
	private List<ChildrenStateVo> stats;
}
