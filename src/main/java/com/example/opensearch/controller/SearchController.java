package com.example.opensearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.opensearch.service.OpenSearchService;
import com.example.opensearch.model.SearchRequestDTO;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

	private final OpenSearchService searchService;

	// GET Query
	@GetMapping("/{indexName}")
	public ResponseEntity<List<Map<String, Object>>> search(
			@PathVariable String indexName,
			@RequestParam String query) {
		return ResponseEntity.ok(searchService.search(indexName, query));
	}

	//POST endpoint for JSON-based query
	@RequestMapping(
			value = "/filter",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Map<String, Object>>> searchWithFilters(
			@RequestBody SearchRequestDTO searchRequest) {
		return ResponseEntity.ok(searchService.searchWithFilters(searchRequest));
	}

	// Mapping Endpoint
	@GetMapping("/{indexName}/mapping")
	public ResponseEntity<Map<String, Object>> getMapping(
			@PathVariable String indexName) {
		return ResponseEntity.ok(searchService.getIndexMapping(indexName));
	}
}