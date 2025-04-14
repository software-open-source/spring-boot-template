package org.software.open.source.spring.template.infrastructure.web.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.software.open.source.spring.template.domain.models.commands.CreateVocabularyCommand;
import org.software.open.source.spring.template.domain.models.responses.VocabularyResponse;
import org.software.open.source.spring.template.domain.ports.in.CreateVocabularyUseCase;
import org.software.open.source.spring.template.domain.ports.in.DeleteVocabularyUseCase;
import org.software.open.source.spring.template.domain.ports.in.RetrieveVocabularyUseCase;
import org.software.open.source.spring.template.domain.ports.in.UpdateVocabularyUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

class VocabularyControllerTest {

    @Mock
    private CreateVocabularyUseCase createVocabularyUseCase;

    @Mock
    private RetrieveVocabularyUseCase retrieveVocabularyUseCase;

    @Mock
    private UpdateVocabularyUseCase updateVocabularyUseCase;

    @Mock
    private DeleteVocabularyUseCase deleteVocabularyUseCase;

    @InjectMocks
    private VocabularyController vocabularyController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vocabularyController).build();
        objectMapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close();
        }
    }

    @Test
    void testCreateVocabulary() throws Exception {
        CreateVocabularyCommand command = new CreateVocabularyCommand();
        VocabularyResponse response = new VocabularyResponse();
        when(createVocabularyUseCase.createVocabulary(command)).thenReturn(response);

        mockMvc.perform(post("/api/v1/vocabularies").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(command))).andExpect(status().isCreated()).andExpect(jsonPath("$.message").value("Vocabulary created successfully"));

        verify(createVocabularyUseCase).createVocabulary(command);
    }

    @Test
    void testGetVocabularyById() throws Exception {
        VocabularyResponse response = new VocabularyResponse();
        when(retrieveVocabularyUseCase.getVocabularyById(1)).thenReturn(Optional.of(response));

        mockMvc.perform(get("/api/v1/vocabularies/1")).andExpect(status().isOk()).andExpect(jsonPath("$.message").value("Vocabulary retrieved successfully"));

        verify(retrieveVocabularyUseCase).getVocabularyById(1);
    }

    @Test
    void testSearchVocabularies() throws Exception {
        Page<VocabularyResponse> page = new PageImpl<>(Collections.emptyList());
        when(retrieveVocabularyUseCase.searchVocabularies(eq("word"), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/v1/vocabularies/search").param("word", "word").param("page", "0").param("size", "10")).andExpect(status().isOk()).andExpect(jsonPath("$.message").value("Vocabularies found successfully"));

        verify(retrieveVocabularyUseCase).searchVocabularies(eq("word"), any(Pageable.class));
    }

    @Test
    void testGetVocabularyByExactWord() throws Exception {
        VocabularyResponse response = new VocabularyResponse();
        when(retrieveVocabularyUseCase.getVocabularyByWord("word")).thenReturn(Optional.of(response));

        mockMvc.perform(get("/api/v1/vocabularies/exact").param("word", "word")).andExpect(status().isOk()).andExpect(jsonPath("$.message").value("Vocabulary retrieved successfully"));

        verify(retrieveVocabularyUseCase).getVocabularyByWord("word");
    }

    @Test
    void testDeleteVocabulary() throws Exception {
        when(deleteVocabularyUseCase.deleteVocabularyById(1)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/vocabularies/1")).andExpect(status().isNoContent());

        verify(deleteVocabularyUseCase).deleteVocabularyById(1);
    }
}