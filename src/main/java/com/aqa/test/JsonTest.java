package com.aqa.test;

import com.aqa.kb.KnowledgeBase;
import com.aqa.relations.SemanticRelation;
import com.aqa.relations.SemanticRelationExtractor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import edu.stanford.nlp.simple.Sentence;

import java.util.List;

public class JsonTest {
    public static void main(String[] args) {
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

        final KnowledgeBase knowledgeBase = new KnowledgeBase();
        knowledgeBase.addDocument("John cooked fish with a frying pan.", extractor1);
        knowledgeBase.addDocument("Bob works at Google.", extractor2);

        final ObjectMapper mapper = new ObjectMapper();
        final String s;
        try {
            s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(knowledgeBase);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
