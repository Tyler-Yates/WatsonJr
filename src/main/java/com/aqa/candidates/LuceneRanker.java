package com.aqa.candidates;

import com.aqa.kb.KnowledgeBase;

/**
 * Class that uses the Lucene search engine to answer questions. You will need to implement the {@link
 * #answerQuestion(KnowledgeBase, String)} method.
 */
public class LuceneRanker implements Ranker {
    @Override
    public RankedCandidates answerQuestion(KnowledgeBase knowledgeBase, String question) {
        // TODO Use the Lucene to create an in-memory index of the knowledge base and
        return null;
    }
}
