package org.software.open.source.spring.template.application.services;

import java.util.List;
import java.util.Optional;

import org.software.open.source.spring.template.domain.models.commands.CreateVocabularyCommand;
import org.software.open.source.spring.template.domain.models.commands.UpdateVocabularyCommand;
import org.software.open.source.spring.template.domain.models.responses.VocabularyResponse;
import org.software.open.source.spring.template.domain.ports.in.CreateVocabularyUseCase;
import org.software.open.source.spring.template.domain.ports.in.DeleteVocabularyUseCase;
import org.software.open.source.spring.template.domain.ports.in.RetrieveVocabularyUseCase;
import org.software.open.source.spring.template.domain.ports.in.UpdateVocabularyUseCase;
import org.software.open.source.spring.template.domain.ports.out.VocabularyRepositoryPort;
import org.software.open.source.spring.template.exception.ApplicationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VocabularyService implements CreateVocabularyUseCase, RetrieveVocabularyUseCase, UpdateVocabularyUseCase, DeleteVocabularyUseCase {

    private final VocabularyRepositoryPort vocabularyRepositoryPort;

    @Override
    public VocabularyResponse createVocabulary(CreateVocabularyCommand command) {
        VocabularyResponse vocabulary = VocabularyResponse.builder().word(command.getWord()).phonetic(command.getPhonetic()).wordType(command.getWordType()).wordTypeEn(command.getWordTypeEn()).meaningVn(command.getMeaningVn()).meaningExplanationVn(command.getMeaningExplanationVn())
                .verbForms(command.getVerbForms()).example(command.getExample()).exampleMeaning(command.getExampleMeaning()).build();

        return vocabularyRepositoryPort.save(vocabulary);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VocabularyResponse> getVocabularyById(Integer id) {
        return Optional.of(vocabularyRepositoryPort.findById(id).orElseThrow(() -> new ApplicationException("Vocabulary with ID " + id + " does not exist.", HttpStatus.NOT_FOUND)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VocabularyResponse> getVocabularyByWord(String word) {
        return Optional.of(vocabularyRepositoryPort.findByWord(word).orElseThrow(() -> new ApplicationException("Vocabulary with word '" + word + "' does not exist.", HttpStatus.NOT_FOUND)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VocabularyResponse> getAllVocabularies() {
        return vocabularyRepositoryPort.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VocabularyResponse> getAllVocabularies(Pageable pageable) {
        return vocabularyRepositoryPort.findAll(pageable);
    }

    @Override
    public Optional<VocabularyResponse> updateVocabulary(UpdateVocabularyCommand command) {
        if (!vocabularyRepositoryPort.existsById(command.getId())) {
            throw new ApplicationException("Vocabulary with ID " + command.getId() + " does not exist.", HttpStatus.NOT_FOUND);
        }

        VocabularyResponse vocabulary = VocabularyResponse.builder().id(command.getId()).word(command.getWord()).phonetic(command.getPhonetic()).wordType(command.getWordType()).wordTypeEn(command.getWordTypeEn()).meaningVn(command.getMeaningVn())
                .meaningExplanationVn(command.getMeaningExplanationVn()).verbForms(command.getVerbForms()).example(command.getExample()).exampleMeaning(command.getExampleMeaning()).build();

        return Optional.of(vocabularyRepositoryPort.save(vocabulary));
    }

    @Override
    public boolean deleteVocabularyById(Integer id) {
        if (!vocabularyRepositoryPort.existsById(id)) {
            throw new ApplicationException("Vocabulary with ID " + id + " does not exist.", HttpStatus.NOT_FOUND);
        }
        vocabularyRepositoryPort.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VocabularyResponse> searchVocabularies(String keyword, Pageable pageable) {
        return vocabularyRepositoryPort.searchByWordContaining(keyword, pageable);
    }

}
