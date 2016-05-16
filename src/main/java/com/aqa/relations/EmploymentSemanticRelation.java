package com.aqa.relations;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a {@link SemanticRelation} that refers to employment.
 */
public class EmploymentSemanticRelation extends SemanticRelation {
    public static String NAME = "Employment";
    public static String EMPLOYEE_FEATURE = "employee";
    public static String EMPLOYER_FEATURE = "employer";
    public static String LOCATION_FEATURE = "location";

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
        features.put(EMPLOYEE_FEATURE, employee);
        features.put(EMPLOYER_FEATURE, employer);
        features.put(LOCATION_FEATURE, location);
        return new EmploymentSemanticRelation(NAME, features);
    }
}
