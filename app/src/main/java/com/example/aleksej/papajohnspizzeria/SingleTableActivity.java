package com.example.aleksej.papajohnspizzeria;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class SingleTableActivity extends AppCompatActivity {

    TextView tableNo,type,smokers,reserved,chairs;
    ImageView tableImage;
    LinearLayout reserveForm;
    Button reserveBtn;
    EditText nameText, dateText, timeText;

    final Calendar myCalendar1 = Calendar.getInstance();
    final Calendar myCalendar2 = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_table);

        Intent endReservation = new Intent(this,EndReservation.class);

        int id = getIntent().getIntExtra("tableNo",0);

        initComponents(id, endReservation);
    }

    @SuppressLint("HandlerLeak")
    public void initComponents(int id, final Intent endReservation){
        Api.getJSON("http://192.168.0.10:5000/tables/"+String.valueOf(id), new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                String response = getJson();

                try {
                    JSONObject object = new JSONObject(response);
                    final Table table = Table.parseJSON(object);
                    setUpReservationBtn(table, endReservation);

                    reserveForm = (LinearLayout) findViewById(R.id.reserveForm);

                    tableNo =(TextView) findViewById(R.id.tableNo);
                    tableImage = (ImageView) findViewById(R.id.tableImage);
                    chairs = (TextView) findViewById(R.id.chairs);
                    type =(TextView) findViewById(R.id.type);
                    smokers =(TextView) findViewById(R.id.smokers);
                    reserved = (TextView) findViewById(R.id.reservedMsg);

                    reserveBtn = (Button) findViewById(R.id.reservation);


                    tableNo.setText("Table No. : " + String.valueOf(table.getTableNo()));

                    switch (table.getType()) {
                        case SpinnerUtils.BIG:
                            tableImage.setImageResource(R.drawable.big);
                            break;
                        case SpinnerUtils.SMALL:
                            tableImage.setImageResource(R.drawable.small);
                            break;
                    }
                    chairs.setText("No. Of Chairs: " + table.getChairs());

                    type.setText("Type: " + (table.getType()).toUpperCase());
                    smokers.setText((table.getSmokers()).toUpperCase());

                    switch (table.getSmokers()) {
                        case "smoking":
                            smokers.setText("Smoking");
                            break;
                        case "non_smoking":
                            smokers.setText("Non - Smoking");
                            break;
                    }


                } catch (Exception e) {
                    ((TextView)findViewById(R.id.smokers)).setText(response);
                }
                //((TextView)findViewById(R.id.labelJson)).setText(odgovor);

            }

        });

        reserveBtn = (Button) findViewById(R.id.reservation);

        nameText = (EditText) findViewById(R.id.nameText);
        timeText = (EditText) findViewById(R.id.timeText);
        dateText = (EditText) findViewById(R.id.dateText);



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, month);
                myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelDate();
            }
        };

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SingleTableActivity.this, date,
                        myCalendar1.get(Calendar.YEAR),
                        myCalendar1.get(Calendar.MONTH),
                        myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar pickedTime = Calendar.getInstance();
                int hour = pickedTime.get(Calendar.HOUR_OF_DAY);
                int minute = pickedTime.get(Calendar.MINUTE);
                TimePickerDialog TimePicker;

                TimePicker = new TimePickerDialog(SingleTableActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                        timeText.setText( hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                TimePicker.show();
            }
        });


    }

    private void setUpReservationBtn(final Table table, final Intent endReservation){
        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onClick(View v) {
                checkIfReserved(endReservation, table, dateText.getText().toString(), timeText.getText().toString());
            }
        });
    }

    private void updateLabelDate() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateText.setText(sdf.format(myCalendar1.getTime()));
    }

    @SuppressLint("HandlerLeak")
    private void checkIfReserved(final Intent endReservation,final Table table, final String dateToCompare, final String timeToCompare) {

        int tableNo = table.getTableNo();
        Api.getJSON("http://192.168.0.10:5000/reservations/"+String.valueOf(tableNo), new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                String response = getJson();
                try {
                    JSONObject object = new JSONObject(response);
                    final Reservation reservation = Reservation.parseJSON(object);
                    System.out.println("Returned Object: " + object);

                    String date = reservation.getDate();
                    String time = reservation.getTime();

//                    String highTime = String.valueOf(Integer.valueOf(time)+1); //trying to check 1 hour before or after a reservation
//                    String lowTime = String.valueOf(Integer.valueOf(time)-1);

                    if(object.length() == 0){
                        System.out.println(date +" "+ dateToCompare + " " + time +" "+ timeToCompare);
                        if(!(date == dateToCompare && time == timeToCompare)){
                            sendNewReservation(table);

                            endReservation.putExtra("nameInfo",nameText.getText().toString());
                            endReservation.putExtra("dateInfo",dateText.getText().toString());
                            endReservation.putExtra("timeInfo",timeText.getText().toString());

                            startActivity(endReservation);
                        }
                        else{
                            reserveForm.setVisibility(View.GONE);
                            reserved.setText("Table has been Reserved");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error from checkIfReserved " + e);
                }

            }
        });
    }


    private void sendNewReservation(Table table){
        List<Api.Element> data = new ArrayList<>();
        Random rand = new Random();

        int resID = rand.nextInt(100) + 1;

        data.add(new Api.Element("resID", String.valueOf(resID) ) );
        data.add(new Api.Element("tableNo", String.valueOf(table.getTableNo()) ) );
        data.add(new Api.Element("name", nameText.getText().toString() ) );
        data.add(new Api.Element("date", dateText.getText().toString() ) );
        data.add(new Api.Element("time", timeText.getText().toString() ) );
        Api.postDataJSON("http://192.168.0.10:5000/add", data, new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                String odgovor = getJson();
                System.out.println("Finished reservation: " + odgovor);
            }
        });
    }

}
