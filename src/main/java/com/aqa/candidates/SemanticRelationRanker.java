package com.aqa.candidates;

import com.aqa.kb.KnowledgeBase;
import com.aqa.relations.SemanticRelationExtractor;
import com.aqa.relations.SimpleSemanticRelationExtractor;

/**
 * The class that uses a {@link SemanticRelationExtractor} to answer questions. You will need to implement the {@link
 * #answerQuestion(KnowledgeBase, String, SemanticRelationExtractor)} method.
 */
public class SemanticRelationRanker implements Ranker {
    @Override
    public RankedCandidates answerQuestion(KnowledgeBase knowledgeBase, String question) {
        return answerQuestion(knowledgeBase, question, SimpleSemanticRelationExtractor.getSemanticRelationExtractor());
    }

    private RankedCandidates answerQuestion(KnowledgeBase knowledgeBase, String question,
                                            SemanticRelationExtractor semanticRelationExtractor) {
        // TODO Implement a system that uses the knowledge base and semantic relation extractor to answer the question
        return null;
    }
}
