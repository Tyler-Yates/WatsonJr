package com.aqa.candidates;

import edu.stanford.nlp.simple.Sentence;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LATGuesserTest {
    @Test
    public void guessLATPerson() throws Exception {
        // PERSON questions
        assertEquals(LAT.PERSON, guess("Who was the first king of Portugal?"));
        assertEquals(LAT.PERSON, guess("Who is the prime minister of England?"));
    }

    @Test
    public void guessLATLocation() throws Exception {
        // LOCATION questions
        assertEquals(LAT.LOCATION, guess("Where is the highest point in Portugal?"));
        assertEquals(LAT.LOCATION, guess("Where did Columbus sail to?"));
    }

    @Test
    public void guessLATFood() throws Exception {
        // FOOD questions
        assertEquals(LAT.FOOD, guess("What 'royal' cake is eaten during Natal in Portugal?"));
        assertEquals(LAT.FOOD, guess("What tasty treat comes from an eight-legged creature?"));
    }


    @Test
    public void guessLATGame() throws Exception {
        // GAME questions
        assertEquals(LAT.GAME, guess("What sport is the most popular worldwide?"));
        assertEquals(LAT.GAME, guess("What is played using a puck and ice-skates?"));
    }

    private static LAT guess(String question) {
        return LATGuesser.guessLAT(new Sentence(question));
    }
}