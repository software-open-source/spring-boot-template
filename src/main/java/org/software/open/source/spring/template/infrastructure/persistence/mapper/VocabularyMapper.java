package org.software.open.source.spring.template.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.software.open.source.spring.template.domain.models.responses.VocabularyResponse;
import org.software.open.source.spring.template.infrastructure.persistence.entities.VocabularyEntity;

@Mapper(componentModel = "spring")
public interface VocabularyMapper {

    VocabularyMapper INSTANCE = Mappers.getMapper(VocabularyMapper.class);

    VocabularyResponse toResponse(VocabularyEntity entity);

    VocabularyEntity toEntity(VocabularyResponse response);

}