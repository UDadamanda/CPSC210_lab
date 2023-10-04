package model.quiz;

import model.exceptions.AnswerIncorrectException;
import model.exceptions.OutOfTriesException;
import model.question.Question;
import model.question.QuestionList;
import model.question.ShortAnswerQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LimitedTriesQuizTest extends QuizTest {
    private LimitedTriesQuiz quiz;

    @BeforeEach
    void runBefore() {
        QuestionList questionList = new QuestionList();

        Question q1 = new ShortAnswerQuestion(4, "What planet are we on?", "Earth");
        Question q2 = new ShortAnswerQuestion(2, "What country are we in?", "Canada");

        questionList.addQuestion(q1);
        questionList.addQuestion(q2);
        quiz = new LimitedTriesQuiz(questionList);
    }

    @Test
    void testConstructor() {
        assertEquals(0, quiz.getMarkSoFar());
        assertTrue(quiz.hasMoreQuestions());
        assertEquals(6, quiz.getMaxMark());
    }

    @Test
    void testSubmitAnswerAllCorrect() {
        try {
            quiz.getNextQuestion();
            String feedback = quiz.submitAnswer("Earth");
            assertEquals("Correct!", feedback);
            assertEquals(4, quiz.getMarkSoFar());
            quiz.getNextQuestion();
            feedback = quiz.submitAnswer("Canada");
            assertEquals("Correct!", feedback);
            assertEquals(6, quiz.getMarkSoFar());
            assertFalse(quiz.hasMoreQuestions());
            assertEquals("Your final mark is: 6 out of 6", quiz.endQuiz());
        } catch (OutOfTriesException e) {
            fail("Should not have thrown OutOfTriesException.");
        } catch (AnswerIncorrectException e) {
            fail("Should not have thrown AnswerIncorrectException.");
        }
    }

    @Test
    void testSubmitAnswerOneFailOneCorrect() {
        try {
            quiz.getNextQuestion();
            quiz.submitAnswer("Not Earth");
            fail("AnswerIncorrectException was not thrown");
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            // all good
        }

        assertEquals(1, quiz.getAttempted());
        assertEquals(3, quiz.getRemainAttempt());

        try {
            String feedback = quiz.submitAnswer("Earth");
            assertEquals("Correct!", feedback);
            assertEquals(3, quiz.getMarkSoFar());
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            fail("Should not catch AnswerIncorrectException");
        }

        try {
            quiz.getNextQuestion();
            quiz.submitAnswer("Not Earth");
            fail("AnswerIncorrectException was not thrown");
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            // all good
        }

        assertEquals(1, quiz.getAttempted());
        assertEquals(1, quiz.getRemainAttempt());

        try {
            String feedback = quiz.submitAnswer("Canada");
            assertEquals("Correct!", feedback);
            assertEquals(4, quiz.getMarkSoFar());
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            fail("Should not catch AnswerIncorrectException");
        }
    }

    @Test
    void testSubmitAnswerLastAttempt() {
        try {
            quiz.getNextQuestion();
            quiz.submitAnswer("Not Earth");
            fail("AnswerIncorrectException was not thrown");
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            // all good
        }

        assertEquals(1, quiz.getAttempted());
        assertEquals(3, quiz.getRemainAttempt());

        try {
            quiz.submitAnswer("Not Earth");
            fail("AnswerIncorrectException was not thrown");
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            // all good
        }

        assertEquals(2, quiz.getAttempted());
        assertEquals(2, quiz.getRemainAttempt());

        try {
            quiz.submitAnswer("Not Earth");
            fail("AnswerIncorrectException was not thrown");
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            // all good
        }

        assertEquals(3, quiz.getAttempted());
        assertEquals(1, quiz.getRemainAttempt());

        try {
            String feedback = quiz.submitAnswer("Earth");
            assertEquals("Correct!", feedback);
            assertEquals(1, quiz.getMarkSoFar());
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            fail("Should not catch AnswerIncorrectException");
        }

        try {
            quiz.getNextQuestion();
            quiz.submitAnswer("Not Earth");
            fail("AnswerIncorrectException was not thrown");
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            // all good
        }

        assertEquals(1, quiz.getAttempted());
        assertEquals(1, quiz.getRemainAttempt());

        try {
            String feedback = quiz.submitAnswer("Canada");
            assertEquals("Correct!", feedback);
            assertEquals(2, quiz.getMarkSoFar());
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            fail("Should not catch AnswerIncorrectException");
        }
    }

    @Test
    void testSubmitAnswerNoMoreAttempt() {
        try {
            quiz.getNextQuestion();
            quiz.submitAnswer("Not Earth");
            fail("AnswerIncorrectException was not thrown");
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            // all good
        }

        assertEquals(1, quiz.getAttempted());
        assertEquals(3, quiz.getRemainAttempt());

        try {
            quiz.submitAnswer("Not Earth");
            fail("AnswerIncorrectException was not thrown");
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            // all good
        }

        assertEquals(2, quiz.getAttempted());
        assertEquals(2, quiz.getRemainAttempt());

        try {
            quiz.submitAnswer("Not Earth");
            fail("AnswerIncorrectException was not thrown");
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            // all good
        }

        assertEquals(3, quiz.getAttempted());
        assertEquals(1, quiz.getRemainAttempt());

        try {
            quiz.submitAnswer("Not Earth");
            fail("OutOfTriesException was not thrown");
        } catch (OutOfTriesException e) {
            // all good
        } catch (AnswerIncorrectException e) {
            fail("Should not catch AnswerIncorrectException");
        }

        try {
            quiz.getNextQuestion();
            quiz.submitAnswer("Not Canada");
            fail("AnswerIncorrectException was not thrown");
        } catch (OutOfTriesException e) {
            fail("Should not catch OutOfTriesException");
        } catch (AnswerIncorrectException e) {
            // all good
        }

        assertEquals(1, quiz.getAttempted());
        assertEquals(1, quiz.getRemainAttempt());

        try {
            quiz.submitAnswer("Not Canada");
            fail("AnswerIncorrectException was not thrown");
        } catch (OutOfTriesException e) {
            // all good
        } catch (AnswerIncorrectException e) {
            fail("Should not catch AnswerIncorrectException");
        }

        assertEquals(0, quiz.getMarkSoFar());

    }

}
