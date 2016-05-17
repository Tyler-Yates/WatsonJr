package com.aqa.relations;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a {@link SemanticRelation} that refers to employment.
 */
public class EmploymentSemanticRelation extends SemanticRelation {
    // The name of the semantic relation
    public static String RELATION_NAME = "Employment";

    // The names of the features
    public static String EMPLOYEE_FEATURE_NAME = "employee";
    public static String EMPLOYER_FEATURE_NAME = "employer";
    public static String LOCATION_FEATURE_NAME = "location";

    private EmploymentSemanticRelation(@JsonProperty("name") String name,
                                       @JsonProperty("features") Map<String, String> features) {
        super(name, features);
    }

    /**
     * Constructs a new {@link EmploymentSemanticRelation} with the given feature values.
     *
     * @param employee the employee feature value
     * @param employer the employer feature value
     * @param location the location feature value
     * @return the created relation
     */
    public static EmploymentSemanticRelation createRelation(String employee, String employer, String location) {
        final Map<String, String> features = new HashMap<>();
        features.put(EMPLOYEE_FEATURE_NAME, employee);
        features.put(EMPLOYER_FEATURE_NAME, employer);
        features.put(LOCATION_FEATURE_NAME, location);
        return new EmploymentSemanticRelation(RELATION_NAME, features);
    }
}
