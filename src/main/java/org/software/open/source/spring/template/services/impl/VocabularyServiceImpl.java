package org.software.open.source.spring.template.services.impl;

import org.software.open.source.spring.template.apis.models.requests.CreateVocabularyRequest;
import org.software.open.source.spring.template.apis.models.responses.PageResponse;
import org.software.open.source.spring.template.apis.models.responses.VocabularyResponse;
import org.software.open.source.spring.template.io.entities.VocabularyEntity;
import org.software.open.source.spring.template.io.repositories.VocabularyRepository;
import org.software.open.source.spring.template.mapper.VocabularyMapper;
import org.software.open.source.spring.template.services.VocabularyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VocabularyServiceImpl implements VocabularyService {

    private final VocabularyRepository vocabularyRepository;
    private final VocabularyMapper vocabularyMapper;

    @Override
    public VocabularyResponse createVocabulary(CreateVocabularyRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'createVocabulary'");
    }

    @Override
    public PageResponse<VocabularyResponse> searchVocabularies(String word, int page, int size, String sortBy, String direction) {
        log.info("Searching vocabularies with word: {}, page: {}, size: {}, sortBy: {}, direction: {}", word, page, size, sortBy, direction);

        // Create a sort direction
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (StringUtils.hasText(direction) && "desc".equalsIgnoreCase(direction.trim())) {
            sortDirection = Sort.Direction.DESC;
        }

        // Create a sort object
        Sort sort = Sort.by(sortDirection, StringUtils.hasText(sortBy) ? sortBy : "word");

        // Create pageable
        Pageable pageable = PageRequest.of(page, size, sort);

        // Search vocabularies
        Page<VocabularyEntity> vocabularyPage;
        if (StringUtils.hasText(word)) {
            vocabularyPage = vocabularyRepository.findByWordContainingIgnoreCase(word.trim(), pageable);
        } else {
            vocabularyPage = vocabularyRepository.findAll(pageable);
        }

        // Convert to response
        Page<VocabularyResponse> responsePage = vocabularyPage.map(vocabularyMapper::toResponse);

        return PageResponse.from(responsePage);
    }

}
