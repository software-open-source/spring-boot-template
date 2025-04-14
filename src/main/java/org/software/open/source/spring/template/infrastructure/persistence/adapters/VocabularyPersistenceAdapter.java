package org.software.open.source.spring.template.infrastructure.persistence.adapters;

import java.util.List;
import java.util.Optional;

import org.software.open.source.spring.template.domain.models.responses.VocabularyResponse;
import org.software.open.source.spring.template.domain.ports.out.VocabularyRepositoryPort;
import org.software.open.source.spring.template.infrastructure.persistence.entities.VocabularyEntity;
import org.software.open.source.spring.template.infrastructure.persistence.mapper.VocabularyMapper;
import org.software.open.source.spring.template.infrastructure.persistence.repositories.JpaVocabularyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VocabularyPersistenceAdapter implements VocabularyRepositoryPort {

    private final JpaVocabularyRepository repository;
    private final VocabularyMapper mapper;

    @Override
    public VocabularyResponse save(VocabularyResponse vocabulary) {
        VocabularyEntity entity = mapper.toEntity(vocabulary);
        VocabularyEntity savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    @Override
    public Optional<VocabularyResponse> findById(Integer id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public Optional<VocabularyResponse> findByWord(String word) {
        return repository.findByWord(word).map(mapper::toResponse);
    }

    @Override
    public List<VocabularyResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public Page<VocabularyResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Page<VocabularyResponse> searchByWordContaining(String word, Pageable pageable) {
        return repository.findByWordContainingIgnoreCase(word, pageable).map(mapper::toResponse);
    }
}