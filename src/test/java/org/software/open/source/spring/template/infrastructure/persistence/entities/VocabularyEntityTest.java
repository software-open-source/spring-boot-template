package org.software.open.source.spring.template.infrastructure.persistence.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class VocabularyEntityTest {

    private static Validator validator;
    private VocabularyEntity vocabulary;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUpVocabularyInstance() {

        vocabulary = new VocabularyEntity();
        vocabulary.setWord("example");
        vocabulary.setPhonetic("/ɪɡˈzæmpəl/");
        vocabulary.setWordType("danh từ");
        vocabulary.setWordTypeEn("noun");
        vocabulary.setMeaningVn("ví dụ");
        vocabulary.setMeaningExplanationVn("Một điều hoặc người đại diện cho một nhóm.");

        vocabulary.setExample("This is an example sentence.");
        vocabulary.setExampleMeaning("Đây là một câu ví dụ.");

    }

    private String createString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append('a');
        }
        return sb.toString();
    }

    @Test
    void testValidVocabularyEntity() {
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertTrue(violations.isEmpty(), "Vocabulary entity should be valid");

        assertEquals("example", vocabulary.getWord());
        assertEquals("/ɪɡˈzæmpəl/", vocabulary.getPhonetic());

    }

    @Test
    void testWordIsNull() {
        vocabulary.setWord(null);
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertEquals(1, violations.size());
        assertEquals("word", violations.iterator().next().getPropertyPath().toString());
        assertEquals("{jakarta.validation.constraints.NotNull.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    void testWordSizeMax() {
        vocabulary.setWord(createString(100));
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testWordSizeExceeded() {
        vocabulary.setWord(createString(101));
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertEquals(1, violations.size());
        assertEquals("word", violations.iterator().next().getPropertyPath().toString());
        assertEquals("{jakarta.validation.constraints.Size.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    void testWordIsEmptyString() {

        vocabulary.setWord("");
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testPhoneticIsNull() {
        vocabulary.setPhonetic(null);
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertEquals(1, violations.size());
        assertEquals("phonetic", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void testPhoneticSizeMax() {
        vocabulary.setPhonetic(createString(50));
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testPhoneticSizeExceeded() {
        vocabulary.setPhonetic(createString(51));
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertEquals(1, violations.size());
        assertEquals("phonetic", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void testMeaningVnIsNull() {
        vocabulary.setMeaningVn(null);
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertEquals(1, violations.size());
        assertEquals("meaningVn", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void testMeaningVnSizeMax() {
        vocabulary.setMeaningVn(createString(200));
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testMeaningVnSizeExceeded() {
        vocabulary.setMeaningVn(createString(201));
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertEquals(1, violations.size());
        assertEquals("meaningVn", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void testMeaningExplanationVnIsNull() {
        vocabulary.setMeaningExplanationVn(null);
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertEquals(1, violations.size());
        assertEquals("meaningExplanationVn", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void testMeaningExplanationVnIsVeryLong() {

        vocabulary.setMeaningExplanationVn(createString(5000));
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testVerbFormsIsNullIsValid() {

        vocabulary.setVerbForms(null);
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertTrue(violations.isEmpty());
        assertNull(vocabulary.getVerbForms());
    }

    @Test
    void testVerbFormsSizeMax() {
        vocabulary.setVerbForms(createString(100));
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testVerbFormsSizeExceeded() {
        vocabulary.setVerbForms(createString(101));
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertEquals(1, violations.size());
        assertEquals("verbForms", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void testVerbFormsIsEmptyString() {
        vocabulary.setVerbForms("");
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testExampleIsNull() {
        vocabulary.setExample(null);
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertEquals(1, violations.size());
        assertEquals("example", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void testExampleIsVeryLong() {

        vocabulary.setExample(createString(5000));
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testExampleMeaningIsNull() {
        vocabulary.setExampleMeaning(null);
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertEquals(1, violations.size());
        assertEquals("exampleMeaning", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void testExampleMeaningSizeMax() {
        vocabulary.setExampleMeaning(createString(200));
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testExampleMeaningSizeExceeded() {
        vocabulary.setExampleMeaning(createString(201));
        Set<ConstraintViolation<VocabularyEntity>> violations = validator.validate(vocabulary);
        assertEquals(1, violations.size());
        assertEquals("exampleMeaning", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    void testGettersAndSetters() {
        VocabularyEntity entity = new VocabularyEntity();
        Integer id = 1;
        String word = "test";

        entity.setId(id);
        entity.setWord(word);

        assertEquals(id, entity.getId());
        assertEquals(word, entity.getWord());

    }
}
