package model.question.checker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticAnswerCheckerTest {
    private ArithmeticAnswerChecker arithmeticAnswerChecker;

    @BeforeEach
    void runBefore() {arithmeticAnswerChecker = new ArithmeticAnswerChecker(4);
    }

    @Test
    void testCaseMatch() {
        assertTrue(arithmeticAnswerChecker.checkAnswer("4"));
    }

    @Test
    void testCaseDouble() {
        assertFalse(arithmeticAnswerChecker.checkAnswer("4.0"));
    }

    @Test
    void testCaseTooBig() {
        assertFalse(arithmeticAnswerChecker.checkAnswer("2147483648"));
    }

    @Test
    void testCaseTooSmall() {
        assertFalse(arithmeticAnswerChecker.checkAnswer("-2147483649"));
    }

    @Test
    void testCaseCharacter() {
        assertFalse(arithmeticAnswerChecker.checkAnswer("four"));
    }




}
