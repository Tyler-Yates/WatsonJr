package com.aqa.relations;

import com.aqa.candidates.RankedCandidate;
import com.aqa.kb.KnowledgeBase;

import java.util.List;

/**
 * Interface for classes that match semantic relations extracted from a question.
 */
public interface SemanticRelationMatcher {
    /**
     * Searches the given knowledge base to answer the given question using the given semantic relation extractor.
     *
     * @param knowledgeBase             the given knowledge base
     * @param question                  the given knowledge base
     * @param semanticRelationExtractor the given knowledge base
     * @return the ranked documents
     */
    List<RankedCandidate> matchRelations(KnowledgeBase knowledgeBase, String question,
                                         SemanticRelationExtractor semanticRelationExtractor);
}
