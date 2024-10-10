package edu.utsa.cs3443.rowdyquiz.model;

import java.util.ArrayList;

public class QuizBank {
    private ArrayList<Question> questions;
    private int qIndex;
    private Question currentQuestion;

    public QuizBank(){
        questions = new ArrayList<Question>();
        qIndex = 0;
        currentQuestion = null;
    }

    public int getqIndex (){
        return qIndex;
    }

    public void setqIndex(int index){
        this.qIndex = index;
    }

    public Question getCurrentQuestion(){
        if (getqIndex() >= 0 && getqIndex() < questions.size() ){
            currentQuestion = questions.get(getqIndex());
            setqIndex(getqIndex() + 1);
        } else{
            setqIndex(0);
            currentQuestion = questions.get(getqIndex());
        }

        return currentQuestion;
    }

    public String getCurrentQuestionText(){
        return currentQuestion.getQuestion();
    }

    public boolean getCurrentQuestionAnswer(){
        return currentQuestion.getAnswer();
    }

    public void addQuestion(Question question){
        questions.add(question);
    }
    public void loadQuestion(){
        addQuestion(new Question("Today is Monday", true));
        addQuestion(new Question("This app is RowdyQuiz", true));
        addQuestion(new Question("The color of app name is black", false));
        addQuestion(new Question("This app is built with HTML", false));
    }
}
