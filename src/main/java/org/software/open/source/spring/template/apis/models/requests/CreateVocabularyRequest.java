package org.software.open.source.spring.template.apis.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVocabularyRequest {

    @NotBlank(message = "word must not be blank")
    private String word;

    @NotBlank(message = "phonetic must not be blank")
    private String phonetic;

    @NotBlank(message = "wordType must not be blank")
    private String wordType;

    @NotBlank(message = "wordTypeEn must not be blank")
    private String wordTypeEn;

    @NotBlank(message = "meaningVn must not be blank")
    private String meaningVn;

    @NotBlank(message = "meaningExplanationVn must not be blank")
    private String meaningExplanationVn;

    @NotBlank(message = "verbForms must not be blank")
    private String verbForms;

    @NotBlank(message = "example must not be blank")
    private String example;

    @NotBlank(message = "exampleMeaning must not be blank")
    private String exampleMeaning;

}