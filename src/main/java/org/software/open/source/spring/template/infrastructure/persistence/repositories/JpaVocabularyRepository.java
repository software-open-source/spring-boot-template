package org.software.open.source.spring.template.infrastructure.persistence.repositories;

import java.util.Optional;

import org.software.open.source.spring.template.infrastructure.persistence.entities.VocabularyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaVocabularyRepository extends JpaRepository<VocabularyEntity, Integer> {
    Optional<VocabularyEntity> findByWord(String word);

    Page<VocabularyEntity> findByWordContainingIgnoreCase(String word, Pageable pageable);
}