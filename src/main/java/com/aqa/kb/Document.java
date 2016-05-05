package com.aqa.kb;

import com.aqa.relations.SemanticRelation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a single document in a knowledge base.
 */
public class Document {
    protected final int id;
    protected final String text;
    protected final List<String> words;
    protected final String parse;
    protected final List<String> nerTags;
    protected final List<SemanticRelation> semanticRelations;

    /**
     * Creates a new document with the given ID, text, words, parse string, NER tags, and semantic relations.
     *
     * @param id                the given ID
     * @param text              the given text
     * @param words             the given words
     * @param parse             the given parse string
     * @param nerTags           the given NER tags
     * @param semanticRelations the given semantic relations
     */
    public Document(@JsonProperty("id") int id, @JsonProperty("text") String text,
                    @JsonProperty("words") List<String> words, @JsonProperty("parse") String parse,
                    @JsonProperty("nerTags") List<String> nerTags,
                    @JsonProperty("semanticRelations") List<SemanticRelation> semanticRelations) {
        this.id = id;
        this.text = text;
        this.words = words;
        this.parse = parse;
        this.nerTags = nerTags;
        this.semanticRelations = semanticRelations;
    }

    /**
     * Returns the ID of the current document.
     *
     * @return the document id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the text of the current document.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the words of the current document.
     *
     * @return the words
     */
    public List<String> getWords() {
        return words;
    }

    /**
     * Returns the parse string of the current document.
     *
     * @return the parse string
     */
    public String getParse() {
        return parse;
    }

    /**
     * Returns the NER tags of the current document.
     *
     * @return the NER tags
     */
    public List<String> getNerTags() {
        return nerTags;
    }

    /**
     * Returns the semantic relations of the current document.
     *
     * @return the semantic relations
     */
    public List<SemanticRelation> getSemanticRelations() {
        return semanticRelations;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", parse='" + parse + '\'' +
                ", semanticRelations=" + semanticRelations +
                '}';
    }
}
