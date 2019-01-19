package com.example.aleksej.papajohnspizzeria;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONObject;

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
    EditText nameText, dateText, timeText;
    Button reserveBtn;

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
        Api.getJSON("http://192.168.0.10:5000/json/"+String.valueOf(id), new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                String response = getJson();

                try {
                    JSONObject object = new JSONObject(response);
                    Table table = Table.parseJSON(object);
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

                    if(table.isReserved()){
                        reserveForm.setVisibility(View.GONE);
                        reserved.setText("Table has been Reserved");
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
                        System.out.println(odgovor);
                    }
                });

                endReservation.putExtra("nameInfo",nameText.getText().toString());
                endReservation.putExtra("dateInfo",dateText.getText().toString());
                endReservation.putExtra("timeInfo",timeText.getText().toString());

                startActivity(endReservation);
            }
        });
    }

    private void updateLabelDate() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateText.setText(sdf.format(myCalendar1.getTime()));
    }

    private void updateLabelTime() {
        String myFormat = "H/mm"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateText.setText(sdf.format(myCalendar1.getTime()));
    }

}
