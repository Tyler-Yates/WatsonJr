package com.aqa.test;

import com.aqa.candidates.LuceneSearch;
import com.aqa.candidates.RankedCandidate;
import com.aqa.kb.KnowledgeBase;
import com.aqa.relations.SemanticRelation;
import com.aqa.relations.SemanticRelationExtractor;
import edu.stanford.nlp.simple.Sentence;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Test class for creating and searching a Lucene index.
 */
class LuceneSearchTest {
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

        final LuceneSearch luceneSearch = new LuceneSearch(knowledgeBase);
        performSearch(luceneSearch, "Who works at Google?");
        performSearch(luceneSearch, "Who works at Google in San Francisco?");
        performSearch(luceneSearch, "Who works at Google in Boulder?");
    }

    private static void performSearch(LuceneSearch luceneSearch,
                                      String searchQuery) throws IOException, ParseException {
        final List<RankedCandidate> rankedCandidates = luceneSearch.search(searchQuery);
        System.out.println(rankedCandidates);
        System.out.println("Results:");
        System.out.println("----------------------------------");
        for (final RankedCandidate rankedCandidate : rankedCandidates) {
            System.out.println(rankedCandidate.getDocument().getText());
        }
        System.out.println("----------------------------------\n\n");
    }
}
