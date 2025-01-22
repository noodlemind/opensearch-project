package com.example.opensearch.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.GetMappingsRequest;
import org.opensearch.client.indices.GetMappingsResponse;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenSearchService {

	private final RestHighLevelClient client;

	public List<Map<String, Object>> search(String indexName, String query) {
		try {
			log.debug("Executing search on index: {} with query: {}", indexName, query);
			SearchRequest searchRequest = new SearchRequest(indexName);
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.queryStringQuery(query));
			searchRequest.source(searchSourceBuilder);

			SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

			List<Map<String, Object>> results = Arrays.stream(response.getHits().getHits())
					                                    .map(hit -> hit.getSourceAsMap())
					                                    .collect(Collectors.toList());

			log.debug("Search completed. Found {} results", results.size());
			return results;
		} catch (Exception e) {
			log.error("Error executing search on index: {}", indexName, e);
			throw new RuntimeException("Error executing search", e);
		}
	}

	public Map<String, Object> getIndexMapping(String indexName) {
		try {
			log.debug("Getting mapping for index: {}", indexName);
			GetMappingsRequest request = new GetMappingsRequest().indices(indexName);
			GetMappingsResponse response = client.indices().getMapping(request, RequestOptions.DEFAULT);
			return response.mappings().get(indexName).getSourceAsMap();
		} catch (Exception e) {
			log.error("Error getting mapping for index: {}", indexName, e);
			throw new RuntimeException("Error getting index mapping", e);
		}
	}
}
