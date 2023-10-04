package model.quiz;

import model.exceptions.AnswerIncorrectException;
import model.question.ArithmeticQuestion;
import model.question.Question;
import model.question.QuestionList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnlimitedTriesQuizTest extends QuizTest {
    private UnlimitedTriesQuiz quiz;

    @BeforeEach
    void runBefore() {
        QuestionList questionList = new QuestionList();

        Question q1 = new ArithmeticQuestion(5, ArithmeticQuestion.Operation.ADDITION, 2, 3);
        Question q2 = new ArithmeticQuestion(5, ArithmeticQuestion.Operation.SUBTRACTION, 4,
                3);
        Question q3 = new ArithmeticQuestion(5, ArithmeticQuestion.Operation.MULTIPLICATION,5,
                3);

        questionList.addQuestion(q1);
        questionList.addQuestion(q2);
        questionList.addQuestion(q3);

        quiz = new UnlimitedTriesQuiz(questionList);
    }

    @Test
    void testConstructor() {
        assertEquals(0, quiz.getMarkSoFar());
        assertTrue(quiz.hasMoreQuestions());
        assertEquals(15, quiz.getMaxMark());
    }

    @Test
    void testSubmitAnswerAllCorrect() {
        try {
            quiz.getNextQuestion();
            String feedback = quiz.submitAnswer("5");
            assertEquals("Correct!", feedback);
            assertEquals(5, quiz.getMarkSoFar());
            assertEquals(1, quiz.getNumAttempts());

            quiz.getNextQuestion();
            feedback = quiz.submitAnswer("1");
            assertEquals("Correct!", feedback);
            assertEquals(10, quiz.getMarkSoFar());
            assertEquals(2, quiz.getNumAttempts());

            quiz.getNextQuestion();
            feedback = quiz.submitAnswer("15");
            assertEquals("Correct!", feedback);
            assertEquals(15, quiz.getMarkSoFar());
            assertEquals(3, quiz.getNumAttempts());
            assertFalse(quiz.hasMoreQuestions());
            assertEquals("It took you 3 attempts to answer 3 questions correctly.", quiz.endQuiz());
        } catch (Exception e) {
            fail("\"Should not have thrown exception.");
        }
    }

    @Test
    void testSubmitAnswerNotCorrect() {
        try {
            quiz.getNextQuestion();
            quiz.submitAnswer("7");
            fail("AnswerIncorrectException was not thrown");
        } catch (AnswerIncorrectException e) {
            // all good
        }
    }
}
