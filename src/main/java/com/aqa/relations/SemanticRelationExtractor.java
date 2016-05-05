package com.aqa.relations;

import edu.stanford.nlp.simple.Sentence;

import java.util.List;

/**
 * Interface for classes that extract semantic relations from text.
 */
public interface SemanticRelationExtractor {
    /**
     * Returns the semantic relations extracted from the given document.
     *
     * @param document the given document
     * @return the semantic relations
     */
    List<SemanticRelation> extractSemanticRelations(Sentence document);
}
