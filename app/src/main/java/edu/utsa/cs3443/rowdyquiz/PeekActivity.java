package edu.utsa.cs3443.rowdyquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PeekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_peek);

        Button btn_show = findViewById(R.id.btn_show_answer);
        Button btn_back = findViewById(R.id.btn_back);

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = getIntent().getStringExtra(MainActivity.decodeIntent());

                TextView txt =findViewById(R.id.txt_answer);
                String display_msg = txt.getText()+ " " + answer;
                txt.setText(display_msg);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain();
            }
        });
    }

    private void backToMain(){
        finish();
    }

}