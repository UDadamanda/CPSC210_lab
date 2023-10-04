package model.quiz;

import model.exceptions.AnswerIncorrectException;
import model.exceptions.OutOfTriesException;
import model.question.QuestionList;

public class LimitedTriesQuiz extends Quiz {
    private int attempted;
    private int remaining;

    public LimitedTriesQuiz(QuestionList questions) {
        super(questions);
    }

    // MODIFIES: this
    // EFFECTS: submit an answer to the current question and return feedback string;
    // if the answer is incorrect, decrements the max mark of the current question by one;
    // throws AnswerIncorrectException if the user should re-try the question
    // throws an OutOfTriesException if the answer is incorrect and no more
    // attempts are allowed
    @Override
    public String submitAnswer(String answer) throws AnswerIncorrectException, OutOfTriesException {
        attempted++;
        remaining = super.getCurQuestion().getOriginalMark() - attempted;
        boolean correct = super.checkAnswer(answer);
        if (correct) {
            attempted = 0;
            return "Correct!";
        } else if (remaining < 1) {
            attempted = 0;
            throw new OutOfTriesException("No more attempts");
        } else {
            super.getCurQuestion().setMaxMark(super.getCurQuestion().getMaxMark() - 1);
            throw new AnswerIncorrectException("Incorrect! Try again");
        }
    }

    public int getRemainAttempt() {
        return remaining;
    }

    public int getAttempted() {
        return attempted;
    }
}
