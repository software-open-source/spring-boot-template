package org.software.open.source.spring.template.domain.ports.in;

import org.software.open.source.spring.template.domain.models.commands.UpdateVocabularyCommand;
import org.software.open.source.spring.template.domain.models.responses.VocabularyResponse;

import java.util.Optional;

public interface UpdateVocabularyUseCase {
    Optional<VocabularyResponse> updateVocabulary(UpdateVocabularyCommand command);
}