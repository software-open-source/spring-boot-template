package org.software.open.source.spring.template.domain.models.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVocabularyCommand {
    private Integer id;
    private String word;
    private String phonetic;
    private String wordType;
    private String wordTypeEn;
    private String meaningVn;
    private String meaningExplanationVn;
    private String verbForms;
    private String example;
    private String exampleMeaning;
}