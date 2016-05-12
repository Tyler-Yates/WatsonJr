package com.aqa.candidates;

import com.aqa.kb.KnowledgeBase;
import com.aqa.relations.SemanticRelationExtractor;

/**
 * Interface for classes that generate ranked candidate answers for questions.
 */
public interface Ranker {
    /**
     * Answers the given question using the given knowledge base and semantic relation extractor.
     *
     * @param knowledgeBase             the given knowledge base
     * @param question                  the given question
     * @param semanticRelationExtractor the given semantic relation extractor
     * @return the ranked candidate answers
     */
    RankedCandidates answerQuestion(KnowledgeBase knowledgeBase, String question,
                                    SemanticRelationExtractor semanticRelationExtractor);
}
