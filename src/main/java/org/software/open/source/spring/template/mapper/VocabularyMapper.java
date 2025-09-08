package org.software.open.source.spring.template.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.software.open.source.spring.template.apis.models.responses.VocabularyResponse;
import org.software.open.source.spring.template.io.entities.VocabularyEntity;

@Mapper(componentModel = "spring", uses = VocabularyUrlBuilder.class)
public interface VocabularyMapper {

    @Mapping(target = "url", source = "word", qualifiedByName = "wordToUrl")
    VocabularyResponse toResponse(VocabularyEntity entity);

    VocabularyEntity toEntity(VocabularyResponse response);
}
