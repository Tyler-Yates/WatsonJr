package com.aqa.relations;

import edu.stanford.nlp.simple.Sentence;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

@SuppressWarnings("JavaDoc")
public class SimpleSemanticRelationExtractorTest {
    private static SemanticRelationExtractor extractor;

    @Before
    public void setUp() throws Exception {
        extractor = SimpleSemanticRelationExtractor.getSemanticRelationExtractor();
    }

    @Test
    public void testExtractSemanticRelationsEmployment() throws Exception {
        testEmploymentRelation("Bob works for Google.", "Bob", "Google", null);
        testEmploymentRelation("Joe was hired by Google.", "Joe", "Google", null);
        testEmploymentRelation("Sue is employed by Google.", "Sue", "Google", null);
        testEmploymentRelation("Google hired Mark.", "Mark", "Google", null);
        testEmploymentRelation("Mary works at Google in San Francisco.", "Mary", "Google", "San Francisco");
        testEmploymentRelation("Toby works for Microsoft in Seattle.", "Toby", "Microsoft", "Seattle");
        testEmploymentRelation("Macy is employed by Amazon in Austin.", "Macy", "Amazon", "Austin");
        testEmploymentRelation("George works in Houston.", "George", null, "Houston");
        testEmploymentRelation("Susan works in San Jose.", "Susan", null, "San Jose");
        testEmploymentRelation("John is employed by Ford Motor Company.", "John", "Ford Motor Company", null);
    }

    private static void testEmploymentRelation(String sentence, String employee, String employer, String location) {
        final List<SemanticRelation> semanticRelations = extractor.extractSemanticRelations(new Sentence(sentence));
        final SemanticRelation expectedRelation =
                EmploymentSemanticRelation.createRelation(employee, employer, location);
        assertThat(semanticRelations, hasItem(expectedRelation));
    }
}