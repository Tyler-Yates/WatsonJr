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
 * Represents a semantic relation. Every semantic relation has a name and some number of features. Features are
 * represented by a {@link Map} of String keys and values. The keys in the map are the names of the features and the
 * values in the map are the values of the features.
 * <p>
 * For example, say we have a semantic relation called "Employment" which has three features: "employee", "employer",
 * and "location". Given the sentence {@code "John works at Google in San Francisco."} we can construct an Employment
 * semantic relation: {@code Employment("employee"="John", "employer"="Google", "location"="San Francisco")}.
 * <p>
 * Not every feature has to have a value: features can have {@code null} values. For instance, given the sentence {@code
 * "John works at Google."} we can construct an Employment relation: {@code Employment("employee"="John",
 * "employer"="Google", "location"=null)}.
 */
public abstract class SemanticRelation {
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
     * value. Feature values may be {@code null}.
     *
     * @return the features
     */
    public Map<String, String> getFeatures() {
        return features;
    }

    /**
     * Returns the value of the feature with the given name in the semantic relation. If the feature does not exist,
     * {@code null} is returned.
     *
     * @param featureName the given name
     * @return the feature value or {@code null} if the feature does not exist
     */
    public String getFeatureValue(String featureName) {
        return features.get(featureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, features.keySet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        final SemanticRelation that = (SemanticRelation) o;

        return name.equals(that.name) && features.equals(that.features);
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
