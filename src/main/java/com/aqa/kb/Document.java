package com.aqa.kb;

import com.aqa.relations.SemanticRelation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a single document in a knowledge base.
 */
public class Document {
    protected final int id;
    protected final String parse;
    protected final List<SemanticRelation> semanticRelations;

    /**
     * Creates a new document with the given ID, parse string, and semantic relations.
     *
     * @param id                the given ID
     * @param parse             the given parse string
     * @param semanticRelations the given semantic relations
     */
    public Document(@JsonProperty("id") int id, @JsonProperty("parse") String parse,
                    @JsonProperty("semanticRelations") List<SemanticRelation> semanticRelations) {
        this.id = id;
        this.parse = parse;
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
     * Returns the parse string of the current document.
     *
     * @return the parse string
     */
    public String getParse() {
        return parse;
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
