package com.example.aleksej.papajohnspizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button logOut,reserve,gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);


        initComponents();
    }

    private void  initComponents(){

        reserve = (Button) findViewById(R.id.reserve);
        reserve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent reservePage = new Intent(MainMenu.this, ReserveTable.class);
                MainMenu.this.startActivity(reservePage);
            }
        });

        gallery = (Button) findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryPage = new Intent(MainMenu.this, Gallery.class);
                MainMenu.this.startActivity(galleryPage);
            }
        });

        logOut = (Button) findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent titleScreen = new Intent(MainMenu.this, TitleScreen.class);
                MainMenu.this.startActivity(titleScreen);
            }
        });

    }



}
