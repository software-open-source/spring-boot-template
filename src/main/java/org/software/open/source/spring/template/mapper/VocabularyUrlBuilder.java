package org.software.open.source.spring.template.mapper;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class VocabularyUrlBuilder {

    @Value("${vocabulary.url-base}")
    private String baseUrl;

    @Named("wordToUrl")
    public String wordToUrl(String word) {
        String base = normalizeBase(baseUrl);
        if (!StringUtils.hasText(word)) {
            return base + "{{word}}";
        }
        return base + word.trim();
    }

    private String normalizeBase(String configured) {
        if (!StringUtils.hasText(configured)) {
            return "https://www.oxfordlearnersdictionaries.com/definition/english/";
        }
        return configured.endsWith("/") ? configured : configured + "/";
    }
}