package com.aqa.candidates;

import com.aqa.kb.Document;
import com.aqa.kb.TestDocument;
import com.aqa.kb.TestKnowledgeBase;
import com.aqa.relations.EmploymentSemanticRelation;
import com.aqa.relations.SemanticRelation;
import com.google.common.collect.ImmutableList;
import edu.stanford.nlp.simple.Sentence;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SemanticRelationRankerTest {
    private static final Document DOC_1 = createDocument(1, "Bob works at Google in Seattle.", ImmutableList.of(
            EmploymentSemanticRelation.createRelation("Bob", "Google", "Seattle")));
    private static final Document DOC_2 = createDocument(2, "John works at Google in San Francisco.", ImmutableList.of(
            EmploymentSemanticRelation.createRelation("John", "Google", "San Francisco")));
    private static final Document DOC_3 = createDocument(3, "John works for Google.", ImmutableList.of(
            EmploymentSemanticRelation.createRelation("John", "Google", null)));
    private static final Document DOC_4 = createDocument(4, "Lorem ipsum dolor sit amet.", ImmutableList.of());

    private Ranker semanticRelationRanker;
    private TestKnowledgeBase knowledgeBase;

    @Before
    public void setUp() throws Exception {
        semanticRelationRanker = new SemanticRelationRanker();

        knowledgeBase = new TestKnowledgeBase();
        knowledgeBase.addDocument(DOC_1);
        knowledgeBase.addDocument(DOC_2);
        knowledgeBase.addDocument(DOC_3);
        knowledgeBase.addDocument(DOC_4);
    }

    @Test
    public void testAnswerQuestion() throws Exception {
        assertQueryResultsCorrect("Who works at Google in Seattle?", ImmutableList.of(DOC_1, DOC_2, DOC_4));
        assertQueryResultsCorrect("Who works for Google in Seattle?", ImmutableList.of(DOC_1, DOC_2, DOC_4));
        assertQueryResultsCorrect("Who is employed by Google in Seattle?", ImmutableList.of(DOC_1, DOC_2, DOC_4));

        assertQueryResultsCorrect("Who works at Google in San Francisco?", ImmutableList.of(DOC_2, DOC_1, DOC_4));
        assertQueryResultsCorrect("Who works for Google in San Francisco?", ImmutableList.of(DOC_2, DOC_1, DOC_4));
        assertQueryResultsCorrect("Who is employed by Google in San Francisco?", ImmutableList.of(DOC_2, DOC_1, DOC_4));
    }

    private void assertQueryResultsCorrect(String query, List<Document> documents) throws IOException, ParseException {
        final RankedCandidates rankedCandidates = semanticRelationRanker.answerQuestion(knowledgeBase, query);
        assertNotNull(rankedCandidates);

        int index = 0;
        for (final RankedCandidate rankedCandidate : rankedCandidates.getRankedCandidates()) {
            assertEquals(rankedCandidate.getDocument(), documents.get(index));
            index++;
        }
    }

    private static Document createDocument(int id, String text, List<SemanticRelation> semanticRelations) {
        final Sentence sentence = new Sentence(text);
        return new TestDocument(id, text, sentence.words(), sentence.parse().toString(), sentence.nerTags(),
                sentence.dependencyGraph().toCompactString(), semanticRelations);
    }
}