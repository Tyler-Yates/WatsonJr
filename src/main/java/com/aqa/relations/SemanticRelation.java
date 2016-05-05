package com.aqa.relations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Joiner;

import java.util.Map;

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
    public String toString() {
        return name + "(" + Joiner.on(", ").join(features.values()) + ")";
    }
}
