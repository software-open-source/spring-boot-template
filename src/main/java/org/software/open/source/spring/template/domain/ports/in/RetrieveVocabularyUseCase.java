package org.software.open.source.spring.template.domain.ports.in;

import java.util.List;
import java.util.Optional;

import org.software.open.source.spring.template.domain.models.responses.VocabularyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RetrieveVocabularyUseCase {

    Optional<VocabularyResponse> getVocabularyById(Integer id);

    Optional<VocabularyResponse> getVocabularyByWord(String word);

    List<VocabularyResponse> getAllVocabularies();

    Page<VocabularyResponse> getAllVocabularies(Pageable pageable);

    // Thêm phương thức mới
    Page<VocabularyResponse> searchVocabularies(String keyword, Pageable pageable);
}