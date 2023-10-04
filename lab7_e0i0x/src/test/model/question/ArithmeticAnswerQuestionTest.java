package model.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.question.ArithmeticQuestion.Operation.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticAnswerQuestionTest extends QuestionTest{
    private ArithmeticQuestion arithmeticQuestion2;
    private ArithmeticQuestion arithmeticQuestion3;

    @BeforeEach
    void runBefore() {
        question = new ArithmeticQuestion(5, ADDITION, 2, 3);
        arithmeticQuestion2 = new ArithmeticQuestion(5, SUBTRACTION, 2, 3);
        arithmeticQuestion3 = new ArithmeticQuestion(5, MULTIPLICATION, 2, 3);
    }

    @Test
    void testConstructor() {
        assertEquals("What is 2 + 3 ? [5 points]",
                question.getQuestionString());
    }

    @Test
    void testCheckAnswerCorrect() {
        assertTrue(question.isCorrect("5"));
        assertTrue(arithmeticQuestion2.isCorrect("-1"));
        assertTrue(arithmeticQuestion3.isCorrect("6"));
    }

    @Test
    void testCheckAnswerIncorrect() {
        assertFalse(question.isCorrect("7"));
        assertFalse(arithmeticQuestion2.isCorrect("1"));
        assertFalse(arithmeticQuestion3.isCorrect("7"));
    }

}
