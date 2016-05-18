package com.aqa.kb;

import com.aqa.relations.SemanticRelation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Variant of {@link Document} used by test classes. DO NOT use this class in your code.
 */
public class TestDocument extends Document {
    /**
     * Creates a new document with the given ID, text, words, parse string, NER tags, dependencies, and semantic
     * relations.
     *
     * @param id                the given ID
     * @param text              the given text
     * @param words             the given words
     * @param parse             the given parse string
     * @param nerTags           the given NER tags
     * @param dependencies      the given dependencies
     * @param semanticRelations the given semantic relations
     */
    public TestDocument(@JsonProperty("id") int id,
                        @JsonProperty("text") String text,
                        @JsonProperty("words") List<String> words,
                        @JsonProperty("parse") String parse,
                        @JsonProperty("nerTags") List<String> nerTags,
                        @JsonProperty("dependencies") String dependencies,
                        @JsonProperty("semanticRelations") List<SemanticRelation> semanticRelations) {
        super(id, text, words, parse, nerTags, dependencies, semanticRelations);
    }
}
