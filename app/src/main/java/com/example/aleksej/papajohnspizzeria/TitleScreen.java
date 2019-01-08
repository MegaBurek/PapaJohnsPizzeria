package com.example.aleksej.papajohnspizzeria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TitleScreen extends AppCompatActivity {

    TextView Enter;
    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_screen);

        fillDB();
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


    private void fillDB(){
        fillUsers();
//        fillReservations();
    }

    private void fillUsers(){
        UsersRepo repo = new UsersRepo(db);
        repo.addUser("Mark","Jones","Mark","Jones");
        repo.addUser("Sam","White","Sam","White");
    }

//    private void fillReservations(){
//        ReservationsRepo repo = new ReservationsRepo(db);
//        repo.addReservation(4,"Mark Jones","2018.05.15 , Wed","12:30");
//    }


}
