package com.aqa.candidates;

import edu.stanford.nlp.simple.Sentence;

/**
 * Class that guess the Lexical Answer Type (LAT) for a question.
 */
public class LATGuesser {
    /**
     * Returns a {@link LAT} for the given question.
     * <p>
     * For example, the question "Who was the first president of Portugal?"
     * would have a LAT of PERSON because the question starts with the word "who".
     * <p>
     * Questions that start with the word "what" are harder to guess the LAT for.
     * You will need to look at other parts of the sentence.
     *
     * @param question the given question
     * @return a lexical answer type
     */
    public static LAT guessLAT(Sentence question) {
        // TODO Use heuristics to guess the LAT for the given question
        return null;
    }
}
