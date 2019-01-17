package com.example.aleksej.papajohnspizzeria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TitleScreen extends AppCompatActivity {

    TextView Enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_screen);

        initComponents();
    }

    private void initComponents(){

        Enter = findViewById(R.id.enter);

        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIn = new Intent(TitleScreen.this, LogIn.class);
                TitleScreen.this.startActivity(logIn);
            }
        });
    }


}
