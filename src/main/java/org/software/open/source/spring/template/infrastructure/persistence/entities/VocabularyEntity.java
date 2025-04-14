package org.software.open.source.spring.template.infrastructure.persistence.entities;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vocabulary")
public class VocabularyEntity {

    @Id
    @Column(name = "id", nullable = false)
    @ColumnDefault("nextval('vocabulary_id_seq')")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(name = "word", nullable = false, length = 100)
    private String word;

    @NotNull
    @Size(max = 50)
    @Column(name = "phonetic", nullable = false, length = 50)
    private String phonetic;

    @NotNull
    @Size(max = 50)
    @Column(name = "word_type", nullable = false, length = 50)
    private String wordType;

    @NotNull
    @Size(max = 50)
    @Column(name = "word_type_en", nullable = false, length = 50)
    private String wordTypeEn;

    @NotNull
    @Size(max = 200)
    @Column(name = "meaning_vn", nullable = false, length = 200)
    private String meaningVn;

    @NotNull
    @Column(name = "meaning_explanation_vn", nullable = false, length = Integer.MAX_VALUE)
    private String meaningExplanationVn;

    @Size(max = 100)
    @Column(name = "verb_forms", length = 100)
    private String verbForms;

    @NotNull
    @Column(name = "example", nullable = false, length = Integer.MAX_VALUE)
    private String example;

    @NotNull
    @Size(max = 200)
    @Column(name = "example_meaning", nullable = false, length = 200)
    private String exampleMeaning;
}