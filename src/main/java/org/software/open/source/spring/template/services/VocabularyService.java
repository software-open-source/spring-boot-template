package org.software.open.source.spring.template.services;

import org.software.open.source.spring.template.apis.models.requests.CreateVocabularyRequest;
import org.software.open.source.spring.template.apis.models.responses.PageResponse;
import org.software.open.source.spring.template.apis.models.responses.VocabularyResponse;

public interface VocabularyService {

    VocabularyResponse createVocabulary(CreateVocabularyRequest request);

    PageResponse<VocabularyResponse> searchVocabularies(String word, int page, int size, String sortBy, String direction);

    // Streamable<Order> getVocabularyById(Integer id);

    // Page<VocabularyResponse> searchVocabularies(String word, Pageable pageable);

    // Streamable<Order> getVocabularyByWord(String word);

    // Page<VocabularyResponse> getAllVocabularies(Pageable pageable);

    // Streamable<Order> updateVocabulary(UpdateVocabularyRequest command);

    // boolean deleteVocabularyById(Integer id);

}
