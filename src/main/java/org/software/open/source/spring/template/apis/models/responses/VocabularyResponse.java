package org.software.open.source.spring.template.apis.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyResponse {

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

    private String url;

}
