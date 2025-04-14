package org.software.open.source.spring.template.infrastructure.persistence.adapters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.software.open.source.spring.template.domain.models.responses.VocabularyResponse;
import org.software.open.source.spring.template.infrastructure.persistence.entities.VocabularyEntity;
import org.software.open.source.spring.template.infrastructure.persistence.mapper.VocabularyMapper;
import org.software.open.source.spring.template.infrastructure.persistence.repositories.JpaVocabularyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class VocabularyPersistenceAdapterTest {

    @Mock
    private JpaVocabularyRepository repository;

    @Mock
    private VocabularyMapper mapper;

    @InjectMocks
    private VocabularyPersistenceAdapter adapter;

    @Test
    void testSave() {
        VocabularyResponse response = VocabularyResponse.builder().word("test").phonetic("/test/").build();

        VocabularyEntity entity = new VocabularyEntity();
        entity.setWord("test");
        entity.setPhonetic("/test/");

        when(mapper.toEntity(any(VocabularyResponse.class))).thenReturn(entity);
        when(repository.save(any(VocabularyEntity.class))).thenReturn(entity);
        when(mapper.toResponse(any(VocabularyEntity.class))).thenReturn(response);

        VocabularyResponse result = adapter.save(response);

        assertThat(result).isNotNull().isEqualTo(response);

        verify(mapper).toEntity(any(VocabularyResponse.class));
        verify(repository).save(any(VocabularyEntity.class));
        verify(mapper).toResponse(any(VocabularyEntity.class));
    }

    @Test
    void testFindById() {
        Integer id = 1;
        VocabularyEntity entity = new VocabularyEntity();
        VocabularyResponse response = new VocabularyResponse();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toResponse(entity)).thenReturn(response);

        Optional<VocabularyResponse> result = adapter.findById(id);

        assertThat(result).isPresent().contains(response);
        verify(repository).findById(id);
    }

    @Test
    void testFindByWord() {
        String word = "test";
        VocabularyEntity entity = new VocabularyEntity();
        VocabularyResponse response = new VocabularyResponse();
        when(repository.findByWord(word)).thenReturn(Optional.of(entity));
        when(mapper.toResponse(entity)).thenReturn(response);

        Optional<VocabularyResponse> result = adapter.findByWord(word);

        assertThat(result).isPresent().contains(response);
        verify(repository).findByWord(word);
    }

    @Test
    void testFindAll() {
        List<VocabularyEntity> entities = List.of(new VocabularyEntity());
        List<VocabularyResponse> responses = List.of(new VocabularyResponse());
        when(repository.findAll()).thenReturn(entities);
        when(mapper.toResponse(any(VocabularyEntity.class))).thenReturn(responses.get(0));

        List<VocabularyResponse> result = adapter.findAll();

        assertThat(result).isEqualTo(responses);
        verify(repository).findAll();
    }

    @Test
    void testFindAllWithPagination() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<VocabularyEntity> entities = new PageImpl<>(List.of(new VocabularyEntity()));
        Page<VocabularyResponse> responses = new PageImpl<>(List.of(new VocabularyResponse()));
        when(repository.findAll(pageable)).thenReturn(entities);
        when(mapper.toResponse(any(VocabularyEntity.class))).thenReturn(responses.getContent().get(0));

        Page<VocabularyResponse> result = adapter.findAll(pageable);

        assertThat(result).isEqualTo(responses);
        verify(repository).findAll(pageable);
    }

    @Test
    void testExistsById() {
        Integer id = 1;
        when(repository.existsById(id)).thenReturn(true);

        boolean result = adapter.existsById(id);

        assertThat(result).isTrue();
        verify(repository).existsById(id);
    }

    @Test
    void testDeleteById() {
        Integer id = 1;

        adapter.deleteById(id);

        verify(repository).deleteById(id);
    }

    @Test
    void testSearchByWordContaining() {
        String word = "test";
        PageRequest pageable = PageRequest.of(0, 10);
        Page<VocabularyEntity> entities = new PageImpl<>(List.of(new VocabularyEntity()));
        Page<VocabularyResponse> responses = new PageImpl<>(List.of(new VocabularyResponse()));
        when(repository.findByWordContainingIgnoreCase(word, pageable)).thenReturn(entities);
        when(mapper.toResponse(any(VocabularyEntity.class))).thenReturn(responses.getContent().get(0));

        Page<VocabularyResponse> result = adapter.searchByWordContaining(word, pageable);

        assertThat(result).isEqualTo(responses);
        verify(repository).findByWordContainingIgnoreCase(word, pageable);
    }
}
