package com.aqa.candidates;

import edu.stanford.nlp.simple.Sentence;

/**
 * Class that guess the Lexical Answer Type (LAT) for a question.
 */
public class LATGuesser {
    /**
     * Returns a {@link LAT} for the given question.
     * <p/>
     * For example, the question "Who was the first president of Portugal?"
     * would have a LAT of PERSON because the question starts with the word "who".
     * <p/>
     * Questions that start with the word "what" are harder to guess the LAT for.
     * You will need to look at other parts of the sentence.
     *
     * @param question the given question
     * @return a lexical answer type
     */
    public static LAT guessLAT(Sentence question) {
        if (question.word(0).equalsIgnoreCase("who")) {
            return LAT.PERSON;
        }
        if (question.word(0).equalsIgnoreCase("where")) {
            return LAT.LOCATION;
        }
        for (final String lemma : question.lemmas()) {
            if (lemma.equalsIgnoreCase("eat") || lemma.equalsIgnoreCase("treat") ||
                    lemma.equalsIgnoreCase("tasty"))
                return LAT.FOOD;
        }
        return null;
    }
}
