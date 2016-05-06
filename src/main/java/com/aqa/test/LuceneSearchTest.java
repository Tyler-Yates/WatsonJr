package com.aqa.test;

import com.aqa.candidates.LuceneSearch;
import com.aqa.kb.KnowledgeBase;
import com.aqa.relations.SemanticRelation;
import com.aqa.relations.SemanticRelationExtractor;
import edu.stanford.nlp.simple.Sentence;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Test class for creating and searching a Lucene index.
 */
public class LuceneSearchTest {
    /**
     * Method to test the index creation and search.
     *
     * @param args arguments
     * @throws Exception if an exception occurs
     */
    public static void main(String[] args) throws Exception {
        final SemanticRelationExtractor extractor = new SemanticRelationExtractor() {
            @Override
            public List<SemanticRelation> extractSemanticRelations(Sentence document) {
                return Collections.emptyList();
            }
        };

        final KnowledgeBase knowledgeBase = new KnowledgeBase();
        knowledgeBase.addDocument("John cooked fish with a frying pan.", extractor);
        knowledgeBase.addDocument("Bob works at Google in San Francisco.", extractor);
        knowledgeBase.addDocument("John works at Google in Boulder.", extractor);

        final Directory index = LuceneSearch.createIndex(knowledgeBase);
        performSearch(index, "Who works at Google?");
        performSearch(index, "Who works at Google in San Francisco?");
        performSearch(index, "Who works at Google in Boulder?");
    }

    private static void performSearch(Directory index, String searchQuery) throws IOException, ParseException {
        final ScoreDoc[] scoreDocs = LuceneSearch.searchIndex(index, searchQuery);
        System.out.println(Arrays.toString(scoreDocs));
        System.out.println("Results:");
        System.out.println("----------------------------------");
        for (final ScoreDoc scoreDoc : scoreDocs) {
            System.out.println(LuceneSearch.getDocument(index, scoreDoc.doc).get(LuceneSearch.TEXT_FIELD));
        }
        System.out.println("----------------------------------\n\n");
    }
}
