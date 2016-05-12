package com.aqa.kb;

import com.aqa.relations.SemanticRelation;
import com.aqa.relations.SemanticRelationExtractor;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.stanford.nlp.simple.Sentence;

import java.util.List;

/**
 * Represents a single document in a {@link KnowledgeBase}. For this project, a document is a single sentence.
 * <p>
 * Every document contains information obtained from CoreNLP's {@link Sentence} class and a {@link
 * SemanticRelationExtractor}. This information will help your system get around the difficulties of natural language in
 * order to answer questions from users. For instance, CoreNLP has a Named Entity Recognizer (NER) which allows you to
 * know whether "Virginia" refers to a person or a location.
 * <p>
 * Each document has a unique ID. This ID can be used to pull specific documents from a knowledge base.
 */
public class Document {
    protected final int id;
    protected final String text;
    protected final List<String> words;
    protected final String parse;
    protected final List<String> nerTags;
    protected final String dependencies;
    protected final List<SemanticRelation> semanticRelations;

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
    Document(@JsonProperty("id") int id,
             @JsonProperty("text") String text,
             @JsonProperty("words") List<String> words,
             @JsonProperty("parse") String parse,
             @JsonProperty("nerTags") List<String> nerTags,
             @JsonProperty("dependencies") String dependencies,
             @JsonProperty("semanticRelations") List<SemanticRelation> semanticRelations) {
        this.id = id;
        this.text = text;
        this.words = words;
        this.parse = parse;
        this.nerTags = nerTags;
        this.dependencies = dependencies;
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
     * Returns the dependencies of the current document.
     *
     * @return the dependencies
     */
    public String getDependencies() {
        return dependencies;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        final Document document = (Document) o;

        return getId() == document.getId() && getText().equals(document.getText());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getText().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", words=" + words +
                ", parse='" + parse + '\'' +
                ", nerTags=" + nerTags +
                ", dependencies='" + dependencies + '\'' +
                ", semanticRelations=" + semanticRelations +
                '}';
    }
}
