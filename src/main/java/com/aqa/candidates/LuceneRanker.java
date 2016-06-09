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
 * <p>
 * The following links are useful for finding out how to use Lucene:
 * <p>
 * <a href="http://oak.cs.ucla.edu/cs144/projects/lucene/">http://oak.cs.ucla.edu/cs144/projects/lucene/</a>
 * <p>
 * <a href="https://lucene.apache.org/core/6_0_0/core/overview-summary.html">https://lucene.apache
 * .org/core/6_0_0/core/overview-summary.html</a>
 */
public class LuceneRanker implements Ranker {
    @Override
    public RankedCandidates answerQuestion(KnowledgeBase knowledgeBase, String question) {
        // TODO Use Lucene to create an in-memory index of the knowledge base and execute the question as a search query

        // Use RAMDirectory from org.apache.lucene.store to create the index in memory

        // Use IndexWriter from org.apache.lucene.index to add documents to the index

        // Use Query from org.apache.lucene.search to turn the String question into a Lucene query

        // Use IndexSearcher from org.apache.lucene.search to execute the query
        return null;
    }
}
