package com.aqa.relations;

import edu.stanford.nlp.simple.Sentence;

import java.util.List;

public interface SemanticRelationExtractor {
    List<SemanticRelation> extractSemanticRelations(Sentence document);
}
