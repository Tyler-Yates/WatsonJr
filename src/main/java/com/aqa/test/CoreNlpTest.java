package com.aqa.test;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

public class CoreNlpTest {
    public static void main(String[] args) {
        final Document document = new Document("This is a sentence. This is the second sentence.");
        final String json = document.json(Sentence::nerTags);
        System.out.println(json);
    }
}
