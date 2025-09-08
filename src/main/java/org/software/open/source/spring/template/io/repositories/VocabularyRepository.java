package org.software.open.source.spring.template.io.repositories;

import java.util.Optional;

import org.software.open.source.spring.template.io.entities.VocabularyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocabularyRepository extends JpaRepository<VocabularyEntity, Integer> {
    Optional<VocabularyEntity> findByWord(String word);

    Page<VocabularyEntity> findByWordContainingIgnoreCase(String word, Pageable pageable);
}