package com.aqa.relations;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

class TestSemanticRelation extends SemanticRelation {
    /**
     * Constructs a new semantic relation with the given name and features.
     *
     * @param name     the given name
     * @param features the given features
     */
    public TestSemanticRelation(@JsonProperty("name") String name,
                                @JsonProperty("features") Map<String, String> features) {
        super(name, features);
    }
}
