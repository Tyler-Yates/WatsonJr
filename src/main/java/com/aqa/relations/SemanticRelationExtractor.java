package com.aqa.relations;

import edu.stanford.nlp.simple.Sentence;

import java.util.List;

/**
 * Interface for classes that extract semantic relations from text.
 * <p>
 * It is important to remember that semantic information can be worded in many different ways. For instance, all of the
 * following sentences express the same semantic information:
 * <pre>
 * "John works at Google."
 * "John works for Google."
 * "Google hired John."
 * "John is employed by Google."
 * </pre>
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
