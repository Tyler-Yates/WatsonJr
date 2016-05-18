package com.aqa.candidates;

import com.aqa.kb.Document;
import com.aqa.kb.KnowledgeBase;

/**
 * Class that uses the Lucene search engine to answer questions. You will need to implement the {@link
 * #answerQuestion(KnowledgeBase, String)} method.
 * <p>
 * You should only access the text of the documents (using {@link Document#getText()}) from the knowledge base. You will
 * not need to use the information from CoreNLP in the document because Lucene will handle the natural language
 * processing for you.
 */
public class LuceneRanker implements Ranker {
    @Override
    public RankedCandidates answerQuestion(KnowledgeBase knowledgeBase, String question) {
        // TODO Use Lucene to create an in-memory index of the knowledge base and execute the question as a search query
        return null;
    }
}
