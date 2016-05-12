package com.aqa.candidates;

import com.aqa.kb.Document;

/**
 * Class representing a single ranked candidate answer for a specific query. A candidate answer is a single {@link
 * Document}.
 */
public class RankedCandidate implements Comparable<RankedCandidate> {
    private final float score;
    private final Document document;

    /**
     * Creates a new ranked candidate for the given query with the given score representing the given document.
     *
     * @param score    the given score
     * @param document the given document
     */
    RankedCandidate(float score, Document document) {
        this.score = score;
        this.document = document;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        final RankedCandidate that = (RankedCandidate) o;

        return getDocument().equals(that.getDocument());

    }

    @Override
    public int hashCode() {
        return getDocument().hashCode();
    }

    @Override
    public String toString() {
        return "RankedCandidate{" +
                "score=" + score +
                ", document=" + document +
                '}';
    }

    @Override
    public int compareTo(RankedCandidate o) {
        // We want candidates sorted from greatest score to lowest score so negate the result of the compare
        return -Float.compare(score, o.score);
    }
}
