package com.example.opensearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.opensearch.service.OpenSearchService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

	private final OpenSearchService searchService;

	@GetMapping("/{indexName}")
	public ResponseEntity<List<Map<String, Object>>> search(
			@PathVariable String indexName,
			@RequestParam String query) {
		return ResponseEntity.ok(searchService.search(indexName, query));
	}

	@GetMapping("/{indexName}/mapping")
	public ResponseEntity<Map<String, Object>> getMapping(
			@PathVariable String indexName) {
		return ResponseEntity.ok(searchService.getIndexMapping(indexName));
	}
}
