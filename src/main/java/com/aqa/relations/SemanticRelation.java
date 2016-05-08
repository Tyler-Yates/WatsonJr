package com.aqa.relations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Represents a semantic relation.
 */
public class SemanticRelation {
    protected final String name;
    protected final Map<String, String> features;

    /**
     * Constructs a new semantic relation with the given name and features.
     *
     * @param name     the given name
     * @param features the given features
     */
    public SemanticRelation(@JsonProperty("name") String name, @JsonProperty("features") Map<String, String> features) {
        this.name = name;
        this.features = features;
    }

    /**
     * Returns the name of the semantic relation.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the features of the semantic relation. The features are represented as a map from feature name to feature
     * value. Feature values are {@code null} if the feature is not present in the semantic relation.
     *
     * @return the features
     */
    public Map<String, String> getFeatures() {
        return features;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, features.keySet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final SemanticRelation that = (SemanticRelation) o;

        return name.equals(that.name) && features.keySet().equals(that.features.keySet());
    }

    @Override
    public String toString() {
        final SortedSet<String> sortedFeatures = new TreeSet<>(features.keySet());
        final List<String> pairs = new ArrayList<>();
        for (final String key : sortedFeatures) {
            pairs.add(key + "=" + features.get(key));
        }
        return name + "(" + Joiner.on(", ").useForNull("null").join(pairs) + ")";
    }
}
