package com.aqa.relations;

import com.aqa.candidates.RankedCandidates;
import com.aqa.kb.KnowledgeBase;

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
     * @return the ranked candidate answers for the question
     */
    RankedCandidates matchRelations(KnowledgeBase knowledgeBase, String question,
                                    SemanticRelationExtractor semanticRelationExtractor);
}
