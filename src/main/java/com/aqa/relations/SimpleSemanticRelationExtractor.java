package com.aqa.relations;

import edu.stanford.nlp.simple.Sentence;

import java.util.List;

/**
 * The class that extracts {@link SemanticRelation}s from documents. You will need to implement the {@link
 * #extractSemanticRelations(Sentence)} method.
 */
public class SimpleSemanticRelationExtractor implements SemanticRelationExtractor {
    private static final SimpleSemanticRelationExtractor simpleSemanticRelationExtractor =
            new SimpleSemanticRelationExtractor();

    private SimpleSemanticRelationExtractor() {
    }

    /**
     * Returns an instance of {@link SimpleSemanticRelationExtractor}.
     *
     * @return the instance
     */
    public static SemanticRelationExtractor getSemanticRelationExtractor() {
        return simpleSemanticRelationExtractor;
    }

    @Override
    public List<SemanticRelation> extractSemanticRelations(Sentence document) {
        // TODO Implement a system for examining a document and extracting semantic relations.
        return null;
    }
}
