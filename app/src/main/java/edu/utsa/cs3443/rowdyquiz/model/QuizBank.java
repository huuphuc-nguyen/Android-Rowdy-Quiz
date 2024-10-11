package edu.utsa.cs3443.rowdyquiz.model;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import edu.utsa.cs3443.rowdyquiz.MainActivity;

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
    public void loadQuestion(MainActivity activity){
        AssetManager manager = activity.getAssets();
        Scanner scanner = null;
        String  filename = "questions.csv";
        try{
            InputStream file = manager.open(filename);
            scanner = new Scanner(file);

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] tokens = line.split(",");

                Question newQuestion = new Question(tokens[0].trim(),tokens[1].trim().equals("true"));
                addQuestion(newQuestion);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (scanner!=null){
                scanner.close();
            }
        }
    }
}
