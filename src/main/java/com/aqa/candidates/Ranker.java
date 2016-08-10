package com.aqa.candidates;

import com.aqa.kb.KnowledgeBase;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

/**
 * Interface for classes that generate ranked candidate answers for questions.
 */
public interface Ranker {
    /**
     * Uses the given knowledge base to answer the given question.
     *
     * @param knowledgeBase the given knowledge base
     * @param question      the given question
     * @return the ranked candidate answers
     */
    RankedCandidates answerQuestion(KnowledgeBase knowledgeBase, String question) throws IOException, ParseException;
}
