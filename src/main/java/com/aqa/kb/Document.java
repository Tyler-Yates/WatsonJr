package com.aqa.kb;

import com.aqa.relations.SemanticRelation;

import java.util.List;

/**
 * Represents a single document in a knowledge base.
 */
public class Document {
    protected final int id;
    protected final String parse;
    protected final List<SemanticRelation> semanticRelations;

    public Document(int id, String parse, List<SemanticRelation> semanticRelations) {
        this.id = id;
        this.parse = parse;
        this.semanticRelations = semanticRelations;
    }

    public int getId() {
        return id;
    }

    public String getParse() {
        return parse;
    }

    public List<SemanticRelation> getSemanticRelations() {
        return semanticRelations;
    }
}
