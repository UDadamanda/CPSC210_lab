package model.quiz;

import model.exceptions.AnswerIncorrectException;
import model.question.QuestionList;

public class UnlimitedTriesQuiz extends Quiz {
    private int numAttempt;
    private int questionsAnswered;

    public UnlimitedTriesQuiz(QuestionList questions) {
        super(questions);
    }

    // MODIFIES: this
    // EFFECTS: submit an answer to the current question and return feedback string;
    // does not modify max mark of current question;
    // throws AnswerIncorrectException if the user should re-try the question;
    @Override
    public String submitAnswer(String answer) throws AnswerIncorrectException {
        numAttempt++;
        boolean correct = super.checkAnswer(answer);
        if (!correct) {
            throw new AnswerIncorrectException("Incorrect! Try again");
        }
        questionsAnswered++;
        return "Correct!";
    }

    // EFFECTS: returns number of attempts taken to answer questions so far
    public int getNumAttempts() {
        return numAttempt;
    }

    @Override
    public String endQuiz() {
        return "It took you " + numAttempt + " attempts to answer " + questionsAnswered + " questions correctly.";
    }

}
