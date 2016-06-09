package com.aqa.candidates;

import com.aqa.kb.Document;
import com.aqa.kb.TestDocument;
import com.aqa.kb.TestKnowledgeBase;
import com.google.common.collect.ImmutableList;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LuceneRankerTest {
    private static final Document DOC_1 = createDocument(1, "Bob works at Google in Seattle.");
    private static final Document DOC_2 = createDocument(2, "John works at Google in San Francisco.");
    private static final Document DOC_3 = createDocument(3, "Mary caught a fish in the Gulf of Mexico.");

    private Ranker luceneRanker;
    private TestKnowledgeBase knowledgeBase;

    @Before
    public void setUp() throws Exception {
        luceneRanker = new LuceneRanker();

        knowledgeBase = new TestKnowledgeBase();
        knowledgeBase.addDocument(DOC_1);
        knowledgeBase.addDocument(DOC_2);
        knowledgeBase.addDocument(DOC_3);
    }

    @Test
    public void testAnswerQuestionSeattle() throws Exception {
        assertQueryResultsCorrect("Who works at Google in Seattle?", ImmutableList.of(DOC_1, DOC_2));
        assertQueryResultsCorrect("Who works at Google in San Francisco?", ImmutableList.of(DOC_2, DOC_1));
    }

    private void assertQueryResultsCorrect(String query, List<Document> documents) throws IOException, ParseException {
        final RankedCandidates rankedCandidates = luceneRanker.answerQuestion(knowledgeBase, query);
        assertNotNull(rankedCandidates);

        int index = 0;
        for (final RankedCandidate rankedCandidate : rankedCandidates.getRankedCandidates()) {
            assertEquals(documents.get(index), rankedCandidate.getDocument());
            index++;
        }
    }

    private static Document createDocument(int id, String text) {
        return new TestDocument(id, text, null, null, null, null, null);
    }
}