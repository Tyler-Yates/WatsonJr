package com.aqa.relations;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SuppressWarnings("JavaDoc")
public class SemanticRelationTest {
    @Test
    public void testEqualsSame() {
        final SemanticRelation semanticRelation = new TestSemanticRelation("Test", ImmutableMap.of("a", "b"));
        assertEquals(semanticRelation, semanticRelation);
        assertEquals(semanticRelation.hashCode(), semanticRelation.hashCode());
    }

    @Test
    public void testEqualsMatchingNameAndFeatures() {
        final String name = "TestRelation";
        final String featureName = "FeatureName";
        final SemanticRelation semanticRelation1 =
                new TestSemanticRelation(name, ImmutableMap.of(featureName, "value1"));
        final SemanticRelation semanticRelation2 =
                new TestSemanticRelation(name, ImmutableMap.of(featureName, "value2"));
        assertEquals(semanticRelation1, semanticRelation2);
        assertEquals(semanticRelation1.hashCode(), semanticRelation2.hashCode());
    }

    @Test
    public void testEqualsMatchingName() {
        final String name = "TestRelation";
        final SemanticRelation semanticRelation1 =
                new TestSemanticRelation(name, ImmutableMap.of("Feature1", "value1"));
        final SemanticRelation semanticRelation2 =
                new TestSemanticRelation(name, ImmutableMap.of("Feature2", "value2"));
        assertNotEquals(semanticRelation1, semanticRelation2);
    }

    @Test
    public void testEqualsMatchingFeatures() {
        final String featureName = "FeatureName";
        final SemanticRelation semanticRelation1 =
                new TestSemanticRelation("Relation1", ImmutableMap.of(featureName, "value1"));
        final SemanticRelation semanticRelation2 =
                new TestSemanticRelation("Relation2", ImmutableMap.of(featureName, "value2"));
        assertNotEquals(semanticRelation1, semanticRelation2);
    }

    @Test
    public void testEqualsMatchingFeaturesDifferentSizes() {
        final String name = "TestRelation";
        final String featureName = "FeatureName";
        final SemanticRelation semanticRelation1 =
                new TestSemanticRelation(name, ImmutableMap.of(featureName, "value1"));
        final SemanticRelation semanticRelation2 =
                new TestSemanticRelation(name, ImmutableMap.of(featureName, "value2", "OtherFeature", "value3"));
        assertNotEquals(semanticRelation1, semanticRelation2);
    }
}
