package com.aqa.kb;

/**
 * Variant of {@link KnowledgeBase} used by test classes. DO NOT use this class in your code.
 */
public class TestKnowledgeBase extends KnowledgeBase {
    /**
     * Adds the given {@link Document} to the knowledge base.
     *
     * @param document the given document
     */
    public void addDocument(Document document) {
        documents.add(document);
    }

    /**
     * Returns the ID that the next document should have in the knowledge base.
     *
     * @return the ID
     */
    public int getNextId() {
        return documents.size();
    }
}
