package com.example.opensearch.model;

import lombok.Data;
import java.util.Map;

@Data
public class SearchRequestDTO {
	private String indexName;
	private Map<String, Object> filters;
	private Integer from = 0;
	private Integer size = 10;
	private String sortField;
	private String sortOrder;
}
