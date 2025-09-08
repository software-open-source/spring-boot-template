package org.software.open.source.spring.template.apis.controllers;

import org.software.open.source.common.apis.models.responses.Response;
import org.software.open.source.spring.template.apis.models.requests.CreateVocabularyRequest;
import org.software.open.source.spring.template.apis.models.responses.PageResponse;
import org.software.open.source.spring.template.apis.models.responses.VocabularyResponse;
import org.software.open.source.spring.template.services.VocabularyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vocabularies")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    @PostMapping
    public ResponseEntity<Response<VocabularyResponse>> createVocabulary(@RequestBody CreateVocabularyRequest request) {
        VocabularyResponse createdVocabulary = vocabularyService.createVocabulary(request);
        Response<VocabularyResponse> response = new Response<>();
        response.setData(createdVocabulary);
        response.setMessage("Vocabulary created successfully");
        response.setStatusCode(HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Response<PageResponse<VocabularyResponse>>> searchVocabularies(@RequestParam String word, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "word") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        PageResponse<VocabularyResponse> vocabularies = vocabularyService.searchVocabularies(word, page, size, sortBy, direction);

        Response<PageResponse<VocabularyResponse>> response = new Response<>();
        response.setData(vocabularies);
        response.setMessage("Vocabularies found successfully");
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

}
