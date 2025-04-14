package org.software.open.source.spring.template.domain.ports.out;

import java.util.List;
import java.util.Optional;

import org.software.open.source.spring.template.domain.models.responses.VocabularyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VocabularyRepositoryPort {

    VocabularyResponse save(VocabularyResponse vocabulary);

    Optional<VocabularyResponse> findById(Integer id);

    Optional<VocabularyResponse> findByWord(String word);

    List<VocabularyResponse> findAll();

    Page<VocabularyResponse> findAll(Pageable pageable);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    Page<VocabularyResponse> searchByWordContaining(String word, Pageable pageable);

}