package com.example.opensearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.opensearch.service.OpenSearchService;
import java.util.List;
import java.util.Map;

@RestController  // Changed from @Controller to @RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

	private final OpenSearchService searchService;

	// Basic health check
	@GetMapping("/")
	public ResponseEntity<String> healthCheck() {
		return ResponseEntity.ok("Service is running");
	}

	// Search endpoint
	@GetMapping("/{indexName}")
	public ResponseEntity<List<Map<String, Object>>> search(
			@PathVariable String indexName,
			@RequestParam String query) {
		return ResponseEntity.ok(searchService.search(indexName, query));
	}

	// Mapping endpoint
	@GetMapping("/{indexName}/mapping")
	public ResponseEntity<Map<String, Object>> getMapping(
			@PathVariable String indexName) {
		return ResponseEntity.ok(searchService.getIndexMapping(indexName));
	}
}