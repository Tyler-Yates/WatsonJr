package com.aqa.kb;

import com.aqa.relations.SemanticRelation;
import com.aqa.relations.SemanticRelationExtractor;
import com.google.common.collect.ImmutableList;
import edu.stanford.nlp.simple.Sentence;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single knowledge base.
 */
public class KnowledgeBase {
    protected final List<Document> documents = new ArrayList<>();

    public List<Document> getDocuments() {
        return ImmutableList.copyOf(documents);
    }

    /**
     * Adds the given text as a document into the current knowledge base using the given semantic relation extractor.
     *
     * @param documentText              the document text
     * @param semanticRelationExtractor the given semantic relation extractor
     */
    public void addDocument(String documentText, SemanticRelationExtractor semanticRelationExtractor) {
        final int id = documents.size();
        final Sentence sentence = new Sentence(documentText);
        final List<SemanticRelation> semanticRelations = semanticRelationExtractor.extractSemanticRelations(sentence);
        documents.add(new Document(id, sentence.parse().toString(), semanticRelations));
    }
}
