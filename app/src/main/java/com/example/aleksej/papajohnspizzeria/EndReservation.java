package com.example.aleksej.papajohnspizzeria;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class EndReservation extends AppCompatActivity {

    TextView nameDisplay, timeDisplay, dateDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.end_reservation);

        initComponents();

    }

    public void initComponents(){
        nameDisplay = (TextView) findViewById(R.id.mainInfo);
        timeDisplay = (TextView) findViewById(R.id.timeInfo);
        dateDisplay = (TextView) findViewById(R.id.dateInfo);

        String mainInfo = getIntent().getStringExtra("nameInfo");
        String dateInfo = getIntent().getStringExtra("dateInfo");
        String timeInfo = getIntent().getStringExtra("timeInfo");

        nameDisplay.setText("The reservation has been made for " + mainInfo);
        dateDisplay.setText("Date: " + dateInfo);
        timeDisplay.setText("Time: " + timeInfo);
    }
}
