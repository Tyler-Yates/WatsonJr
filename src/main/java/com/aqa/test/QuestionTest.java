package com.aqa.test;

import com.aqa.candidates.RankedCandidate;
import com.aqa.kb.Document;
import com.aqa.kb.KnowledgeBase;
import com.aqa.relations.SemanticRelation;
import com.aqa.relations.SemanticRelationExtractor;
import com.aqa.relations.SemanticRelationMatcher;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;
import edu.stanford.nlp.simple.Sentence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class for matching relations of a question.
 */
public class QuestionTest {
    /**
     * Runner method.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        // Knowledge base
        final SemanticRelation relation1 = new SemanticRelation("Agent",
                ImmutableMap.of("event", "cook", "doer", "John"));
        final SemanticRelation relation2 = new SemanticRelation("Object",
                ImmutableMap.of("event", "cook", "target", "fish"));
        final SemanticRelation relation3 = new SemanticRelation("Instrument",
                ImmutableMap.of("event", "cook", "item", "a frying pan"));
        final SemanticRelationExtractor extractor1 = new SemanticRelationExtractor() {
            @Override
            public List<SemanticRelation> extractSemanticRelations(Sentence document) {
                return ImmutableList.of(relation1, relation2, relation3);
            }
        };
        final SemanticRelation relation4 = new SemanticRelation("Employment",
                ImmutableMap.of("employee", "Bob", "employer", "Google", "location", "San Francisco"));
        final SemanticRelationExtractor extractor2 = new SemanticRelationExtractor() {
            @Override
            public List<SemanticRelation> extractSemanticRelations(Sentence document) {
                return ImmutableList.of(relation4);
            }
        };
        final SemanticRelation relation5 = new SemanticRelation("Employment",
                ImmutableMap.of("employee", "Sue", "employer", "Google", "location", "Boulder"));
        final SemanticRelationExtractor extractor3 = new SemanticRelationExtractor() {
            @Override
            public List<SemanticRelation> extractSemanticRelations(Sentence document) {
                return ImmutableList.of(relation5);
            }
        };
        final KnowledgeBase knowledgeBase = new KnowledgeBase();
        knowledgeBase.addDocument("John cooked fish with a frying pan.", extractor1);
        knowledgeBase.addDocument("Bob works at Google in San Francisco.", extractor2);
        knowledgeBase.addDocument("Sue works at Google in Boulder.", extractor3);

        // Semantic relation matcher
        final SemanticRelationMatcher semanticRelationMatcher = new SemanticRelationMatcher() {
            @Override
            public List<RankedCandidate> matchRelations(KnowledgeBase knowledgeBase, String question,
                                                        SemanticRelationExtractor semanticRelationExtractor) {
                final List<SemanticRelation> questionRelations = semanticRelationExtractor.extractSemanticRelations(
                        new Sentence(question));

                final Map<Document, Float> scores = new HashMap<>();
                for (final Document document : knowledgeBase.getDocuments()) {
                    System.out.println("Examining document " + document.getId());
                    for (final SemanticRelation questionRelation : questionRelations) {
                        for (final SemanticRelation documentRelation : document.getSemanticRelations()) {
                            // Only examine semantic relations that are equal otherwise we might have mismatched feature
                            // keys when comparing the feature values
                            if (questionRelation.equals(documentRelation)) {
                                System.out.println("Found same relation:");
                                System.out.println(questionRelation);
                                System.out.println(documentRelation);

                                /*
                                Scoring each relation pair looks at all of the non-null features in the question's
                                semantic relation. A perfect score is 1.0 which means that all non-null features in the
                                question's semantic relation were equal to the corresponding feature in the document's
                                semantic relation.
                                 */
                                int nonNullFeatures = 0;
                                int matchedFeatures = 0;
                                for (final String featureKey : questionRelation.getFeatures().keySet()) {
                                    final String questionFeatureValue = questionRelation.getFeatures().get(featureKey);
                                    final String documentFeatureValue = documentRelation.getFeatures().get(featureKey);

                                    if (questionFeatureValue != null) {
                                        nonNullFeatures++;
                                        if (questionFeatureValue.equals(documentFeatureValue)) {
                                            matchedFeatures++;
                                        }
                                    }
                                }

                                final float additionalScore = 1.0f * matchedFeatures / nonNullFeatures;
                                if (scores.containsKey(document)) {
                                    scores.put(document, scores.get(document) + additionalScore);
                                } else {
                                    scores.put(document, additionalScore);
                                }
                                System.out.printf("Added score: %f; New score: %f\n\n", additionalScore,
                                        scores.get(document));
                            }
                        }
                    }
                    System.out.println();
                }

                // Sort and return the results
                final Ordering<Document> documentOrdering = Ordering.natural().reverse().onResultOf(
                        Functions.forMap(scores));
                final ImmutableSortedMap<Document, Float> sortedDocuments = ImmutableSortedMap.copyOf(
                        scores, documentOrdering);
                final List<RankedCandidate> rankedCandidates = new ArrayList<>(sortedDocuments.size());
                for (final Map.Entry<Document, Float> documentScoreEntry : sortedDocuments.entrySet()) {
                    rankedCandidates.add(
                            new RankedCandidate(question, documentScoreEntry.getValue(), documentScoreEntry.getKey()));
                }
                return rankedCandidates;
            }
        };

        // Question semantic relations
        final Map<String, String> features = new HashMap<>();
        features.put("employee", null);
        features.put("employer", "Google");
        features.put("location", "San Francisco");
        final SemanticRelation questionRelation = new SemanticRelation("Employment", features);
        final SemanticRelationExtractor questionRelationExtractor = new SemanticRelationExtractor() {
            @Override
            public List<SemanticRelation> extractSemanticRelations(Sentence document) {
                return ImmutableList.of(questionRelation);
            }
        };

        // Get the results
        final List<RankedCandidate> rankedCandidates = semanticRelationMatcher.matchRelations(knowledgeBase,
                "Who works at Google in San Francisco?", questionRelationExtractor);
        System.out.println("Ranked candidates: " + rankedCandidates);
    }
}
