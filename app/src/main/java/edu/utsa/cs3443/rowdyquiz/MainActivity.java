package edu.utsa.cs3443.rowdyquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.rowdyquiz.model.*;

public class MainActivity extends AppCompatActivity {
    private QuizBank quizbank;
    private Toast currentToast;
    private static final String intentKey = "answer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        createQuizBank();

        displayQuestion();

        Button btn_next = findViewById(R.id.btn_next);
        Button btn_true = findViewById(R.id.btn_true);
        Button btn_false = findViewById(R.id.btn_false);
        Button btn_peek = findViewById(R.id.btn_peek);

        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cancel the previous toast
                if (currentToast != null) {
                    currentToast.cancel();
                }

                if (getAnswer()) {
                    currentToast = Toast.makeText(view.getContext(), "Yaaaay", Toast.LENGTH_LONG);
                    currentToast.show();
                }else{
                    currentToast = Toast.makeText(view.getContext(), "Try again !", Toast.LENGTH_LONG);
                    currentToast.show();
                }
            }
        });

        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cancel previous toast
                if (currentToast != null) {
                    currentToast.cancel();
                }
                if (getAnswer()) {
                    currentToast = Toast.makeText(view.getContext(), "Try again !", Toast.LENGTH_LONG);
                    currentToast.show();
                }else{
                    currentToast = Toast.makeText(view.getContext(), "Yaaaay", Toast.LENGTH_LONG);
                    currentToast.show();
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNextQuestion();
            }
        });

        btn_peek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchPeekActivity();
            }
        });
    }

    private void createQuizBank(){
        quizbank = new QuizBank();
        quizbank.loadQuestion(this);
        Log.e("Create","Load Data");
    }

    private void displayQuestion(){
        quizbank.getCurrentQuestion();
        TextView questionText = findViewById(R.id.txt_question);
        questionText.setText(getQuestion());
    }

    private void displayNextQuestion(){
        quizbank.goToNextQuestion();
        displayQuestion();
    }

    private String getQuestion(){
        return quizbank.getCurrentQuestionText();
    }

    private boolean getAnswer(){
        return quizbank.getCurrentQuestionAnswer();
    }

    private void launchPeekActivity(){
        Intent intent = new Intent(this, PeekActivity.class);
        intent.putExtra(intentKey, String.valueOf(getAnswer()));
        startActivity(intent);
    }

    public static String decodeIntent(){
        return intentKey;
    }

}