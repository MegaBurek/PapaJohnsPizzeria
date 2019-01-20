package com.example.aleksej.papajohnspizzeria;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TitleScreen extends AppCompatActivity {

    TextView Enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_screen);

        initComponents();
        fillReservations();
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

    private void fillReservations(){
        List<Api.Element> data = new ArrayList<>();

        data.add(new Api.Element("resID", "54"));
        data.add(new Api.Element("tableNo", "12"));
        data.add(new Api.Element("name", "Mark Jones"));
        data.add(new Api.Element("date", "12/01/19"));
        data.add(new Api.Element("time", "12:30"));
        Api.postDataJSON("http://192.168.0.10:5000/add", data, new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                String odgovor = getJson();
                System.out.println(odgovor);
            }
        });
    }


}
