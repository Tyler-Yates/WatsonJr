package com.aqa.kb;

import com.aqa.relations.SemanticRelation;
import com.aqa.relations.SemanticRelationExtractor;
import com.google.common.collect.ImmutableList;
import edu.stanford.nlp.simple.Sentence;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single knowledge base. A knowledge base contains zero or more {@link Document}s.
 * <p>
 * Documents can only be added to a knowledge base, not removed.
 */
public class KnowledgeBase {
    protected final List<Document> documents = new ArrayList<>();

    /**
     * Returns the documents in the current knowledge base.
     *
     * @return the documents
     */
    public List<Document> getDocuments() {
        return ImmutableList.copyOf(documents);
    }

    /**
     * Returns the document with the given ID if one exists, {@code null} otherwise.
     *
     * @param id the given ID
     * @return the document or {@code null}
     */
    public Document getDocument(int id) {
        // Make sure the index isn't out of bounds
        if (id < 0 || id >= documents.size()) {
            return null;
        }

        // Document ID should be its index in the list
        final Document bestGuess = documents.get(id);
        if (bestGuess.getId() == id) {
            return bestGuess;
        }

        // If not, search through all documents
        for (final Document document : documents) {
            if (document.getId() == id) {
                return document;
            }
        }
        return null;
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
        documents.add(new Document(id, documentText, sentence.words(), sentence.parse().toString(), sentence.nerTags(),
                sentence.dependencyGraph().toCompactString(), semanticRelations));
    }

    @Override
    public String toString() {
        return "KnowledgeBase{" +
                "documents=" + documents +
                '}';
    }
}
