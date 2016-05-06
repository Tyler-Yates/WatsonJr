package com.aqa.candidates;

import com.aqa.kb.Document;

/**
 * Class representing a ranked candidate answer for a specific query.
 */
public class RankedCandidate {
    private final String query;
    private final float score;
    private final Document document;

    /**
     * Creates a new ranked candidate for the given query with the given score representing the given document.
     *
     * @param query    the given query
     * @param score    the given score
     * @param document the given document
     */
    public RankedCandidate(String query, float score, Document document) {
        this.query = query;
        this.score = score;
        this.document = document;
    }

    /**
     * Returns the query that produced the current candidate.
     *
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Returns the current candidate's score.
     *
     * @return the score
     */
    public float getScore() {
        return score;
    }

    /**
     * Returns the document that the current candidate represents.
     *
     * @return the document
     */
    public Document getDocument() {
        return document;
    }

    @Override
    public String toString() {
        return "RankedCandidate{" +
                "query='" + query + '\'' +
                ", score=" + score +
                ", document=" + document +
                '}';
    }
}
