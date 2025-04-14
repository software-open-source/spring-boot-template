package org.software.open.source.spring.template.domain.ports.in;

import org.software.open.source.spring.template.domain.models.commands.CreateVocabularyCommand;
import org.software.open.source.spring.template.domain.models.responses.VocabularyResponse;

public interface CreateVocabularyUseCase {
    VocabularyResponse createVocabulary(CreateVocabularyCommand command);
}