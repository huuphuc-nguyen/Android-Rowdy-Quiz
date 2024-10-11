package edu.utsa.cs3443.rowdyquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.utsa.cs3443.rowdyquiz.model.*;

public class MainActivity extends AppCompatActivity {
    private QuizBank quizbank;
    private Toast currentToast;

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
                displayQuestion();
            }
        });
    }

    private void createQuizBank(){
        quizbank = new QuizBank();
        quizbank.loadQuestion(this);
    }

    private void displayQuestion(){
        quizbank.getCurrentQuestion();
        TextView questionText = findViewById(R.id.txt_question);
        questionText.setText(getQuestion());
    }

    private String getQuestion(){
        return quizbank.getCurrentQuestionText();
    }

    private boolean getAnswer(){
        return quizbank.getCurrentQuestionAnswer();
    }
}