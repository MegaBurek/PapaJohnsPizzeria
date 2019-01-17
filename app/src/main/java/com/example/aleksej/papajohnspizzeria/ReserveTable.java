package com.example.aleksej.papajohnspizzeria;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ReserveTable extends AppCompatActivity {

    LinearLayout linLayout1, linLayout2;
    ArrayList<Table> tables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_page);

        Intent singleTable = new Intent(this, SingleTableActivity.class);

//        splash_picture_background = (ImageView) findViewById(R.id.imageView_splash_logo);
//        linearLayout_splash = (LinearLayout) findViewById(R.id.linearLayout_splash);
//        splash_picture_background.setImageResource(R.drawable.main_background);


        initComponents(singleTable);

//        applyBlur(splash_picture_background, linearLayout_splash);
    }

    private void  initComponents(final Intent singleTable){

        tables = TableAPI.getTables();

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linLayout1 = (LinearLayout) findViewById(R.id.smoking);
        linLayout2 = (LinearLayout) findViewById(R.id.non_smoking);



        for (final Table t : tables) {
            ConstraintLayout item = (ConstraintLayout) inflater.inflate(R.layout.table_image, null);

            switch (t.getType()) {
                case SpinnerUtils.BIG:
                    ((ImageView) item.findViewById(R.id.greskaSlika)).setImageResource(R.drawable.big);
                    break;
                case SpinnerUtils.SMALL:
                    ((ImageView) item.findViewById(R.id.greskaSlika)).setImageResource(R.drawable.small);
                    break;
            }

            switch(t.getSmokers()) {
                case SpinnerUtils.SMOKING:
                    linLayout1.addView(item);
                    break;
                case SpinnerUtils.NON_SMOKING:
                    linLayout2.addView(item);
                    break;
            }

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(t.getTableNo());
                    singleTable.putExtra("id", t.getTableNo());
                    startActivity(singleTable);

                }
            });
        }


    }

//    private void applyBlur(final ImageView the_picture, final LinearLayout the_layout) {
//        the_picture.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override public boolean onPreDraw() {
//                the_picture.getViewTreeObserver().removeOnPreDrawListener(this);
//                the_picture.buildDrawingCache();
//                bitmap = the_picture.getDrawingCache();
//                BlurEffect.blur_overlay(ReserveTable.this, bitmap, the_layout);
//                return true;
//            }
//        });
//    }



}