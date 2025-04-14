package org.software.open.source.spring.template.infrastructure.web.controllers;

import org.software.open.source.common.apis.models.responses.Response;
import org.software.open.source.spring.template.domain.models.commands.CreateVocabularyCommand;
import org.software.open.source.spring.template.domain.models.commands.UpdateVocabularyCommand;
import org.software.open.source.spring.template.domain.models.responses.PageResponse;
import org.software.open.source.spring.template.domain.models.responses.VocabularyResponse;
import org.software.open.source.spring.template.domain.ports.in.CreateVocabularyUseCase;
import org.software.open.source.spring.template.domain.ports.in.DeleteVocabularyUseCase;
import org.software.open.source.spring.template.domain.ports.in.RetrieveVocabularyUseCase;
import org.software.open.source.spring.template.domain.ports.in.UpdateVocabularyUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/vocabularies")
@RequiredArgsConstructor
public class VocabularyController {

    private final CreateVocabularyUseCase createVocabularyUseCase;
    private final RetrieveVocabularyUseCase retrieveVocabularyUseCase;
    private final UpdateVocabularyUseCase updateVocabularyUseCase;
    private final DeleteVocabularyUseCase deleteVocabularyUseCase;

    @PostMapping
    public ResponseEntity<Response<VocabularyResponse>> createVocabulary(@RequestBody CreateVocabularyCommand command) {
        VocabularyResponse createdVocabulary = createVocabularyUseCase.createVocabulary(command);
        Response<VocabularyResponse> response = new Response<>();
        response.setData(createdVocabulary);
        response.setMessage("Vocabulary created successfully");
        response.setStatusCode(HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<VocabularyResponse>> getVocabularyById(@PathVariable Integer id) {
        return retrieveVocabularyUseCase.getVocabularyById(id).map(vocabulary -> {
            Response<VocabularyResponse> response = new Response<>();
            response.setData(vocabulary);
            response.setMessage("Vocabulary retrieved successfully");
            response.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Response<PageResponse<VocabularyResponse>>> searchVocabularies(@RequestParam String word, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<VocabularyResponse> vocabularies = retrieveVocabularyUseCase.searchVocabularies(word, pageable);

        Response<PageResponse<VocabularyResponse>> response = new Response<>();
        response.setData(PageResponse.from(vocabularies));
        response.setMessage("Vocabularies found successfully");
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/exact")
    public ResponseEntity<Response<VocabularyResponse>> getVocabularyByExactWord(@RequestParam String word) {
        return retrieveVocabularyUseCase.getVocabularyByWord(word).map(vocabulary -> {
            Response<VocabularyResponse> response = new Response<>();
            response.setData(vocabulary);
            response.setMessage("Vocabulary retrieved successfully");
            response.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Response<Page<VocabularyResponse>>> getAllVocabularies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<VocabularyResponse> vocabularies = retrieveVocabularyUseCase.getAllVocabularies(pageable);

        Response<Page<VocabularyResponse>> response = new Response<>();
        response.setData(vocabularies);
        response.setMessage("Vocabularies retrieved successfully");
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<VocabularyResponse>> updateVocabulary(@PathVariable Integer id, @RequestBody UpdateVocabularyCommand command) {

        command.setId(id);

        return updateVocabularyUseCase.updateVocabulary(command).map(updatedVocabulary -> {
            Response<VocabularyResponse> response = new Response<>();
            response.setData(updatedVocabulary);
            response.setMessage("Vocabulary updated successfully");
            response.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteVocabulary(@PathVariable Integer id) {
        boolean deleted = deleteVocabularyUseCase.deleteVocabularyById(id);

        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
