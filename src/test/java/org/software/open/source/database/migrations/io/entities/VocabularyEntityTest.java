package org.software.open.source.database.migrations.io.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VocabularyEntityTest {

    private VocabularyEntity vocabulary;

    @BeforeEach
    void setUp() {
        vocabulary = new VocabularyEntity();
    }

    @Test
    void testId() {
        vocabulary.setId(1);
        assertEquals(1, vocabulary.getId());
    }

    @Test
    void testWord() {
        vocabulary.setWord("Hello");
        assertEquals("Hello", vocabulary.getWord());
    }

    @Test
    void testPhonetic() {
        vocabulary.setPhonetic("/ˈhɛloʊ/");
        assertEquals("/ˈhɛloʊ/", vocabulary.getPhonetic());
    }

    @Test
    void testWordType() {
        vocabulary.setWordType("Noun");
        assertEquals("Noun", vocabulary.getWordType());
    }

    @Test
    void testWordTypeEn() {
        vocabulary.setWordTypeEn("Noun");
        assertEquals("Noun", vocabulary.getWordTypeEn());
    }

    @Test
    void testMeaningVn() {
        vocabulary.setMeaningVn("Xin chào");
        assertEquals("Xin chào", vocabulary.getMeaningVn());
    }

    @Test
    void testMeaningExplanationVn() {
        vocabulary.setMeaningExplanationVn("Lời chào");
        assertEquals("Lời chào", vocabulary.getMeaningExplanationVn());
    }

    @Test
    void testVerbForms() {
        vocabulary.setVerbForms("Greetings");
        assertEquals("Greetings", vocabulary.getVerbForms());
    }

    @Test
    void testExample() {
        vocabulary.setExample("Hello, how are you?");
        assertEquals("Hello, how are you?", vocabulary.getExample());
    }

    @Test
    void testExampleMeaning() {
        vocabulary.setExampleMeaning("Câu hỏi thăm sức khỏe");
        assertEquals("Câu hỏi thăm sức khỏe", vocabulary.getExampleMeaning());
    }
}
