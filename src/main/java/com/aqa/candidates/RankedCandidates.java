package com.aqa.candidates;

import com.aqa.kb.Document;
import com.google.common.collect.ImmutableSortedSet;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Represents a ranked set of candidate answers for a given query. Each candidate is given a numeric score which is used
 * for ranking.
 */
public class RankedCandidates {
    private final String query;
    private final SortedSet<RankedCandidate> rankedCandidates;

    private RankedCandidates(String query, SortedSet<RankedCandidate> rankedCandidates) {
        this.query = query;
        this.rankedCandidates = rankedCandidates;
    }

    /**
     * Returns the query for which the current candidates are potential answers.
     *
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Returns the ranked set of candidate answers.
     *
     * @return the ranked set of candidate answers
     */
    public SortedSet<RankedCandidate> getRankedCandidates() {
        return ImmutableSortedSet.copyOf(rankedCandidates);
    }

    @Override
    public String toString() {
        return "RankedCandidates{" +
                "query='" + query + '\'' +
                ", rankedCandidates=" + rankedCandidates +
                '}';
    }

    /**
     * Class to build a {@link RankedCandidates} instance.
     */
    public static class Builder {
        private final String query;
        private final SortedSet<RankedCandidate> rankedCandidates = new TreeSet<>();

        /**
         * Creates a new builder for a {@link RankedCandidates} instance which represents the given query.
         *
         * @param query the query
         */
        public Builder(String query) {
            this.query = query;
        }

        /**
         * Adds the given document as a candidate answer with the given score.
         *
         * @param document the given document
         * @param score    the given score
         * @return the builder
         */
        public Builder addCandidate(Document document, float score) {
            rankedCandidates.add(new RankedCandidate(score, document));
            return this;
        }

        /**
         * Creates and returns a {@link RankedCandidates} instance with the current candidates.
         *
         * @return the created instance
         */
        public RankedCandidates build() {
            return new RankedCandidates(query, rankedCandidates);
        }
    }
}
