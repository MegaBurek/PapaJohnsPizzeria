package com.example.aleksej.papajohnspizzeria;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONObject;

public class SingleTableActivity extends AppCompatActivity {

    TextView tableNo,type,smokers,reserved,chairs;
    ImageView tableImage;
    LinearLayout reserveForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_table);
        int id = getIntent().getIntExtra("tableNo",0);
        System.out.println(id);

        initComponents(id);
    }

    @SuppressLint("HandlerLeak")
    public void initComponents(int id){
        Api.getJSON("http://192.168.0.10:5000/json/"+String.valueOf(id), new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                String response = getJson();

                try {
                    JSONObject object = new JSONObject(response);
                    Table table = Table.parseJSON(object);

                    reserveForm = (LinearLayout) findViewById(R.id.reserveForm);

                    tableNo =(TextView) findViewById(R.id.tableNo);
                    tableImage = (ImageView) findViewById(R.id.tableImage);
                    chairs = (TextView) findViewById(R.id.chairs);
                    type =(TextView) findViewById(R.id.type);
                    smokers =(TextView) findViewById(R.id.smokers);
                    reserved = (TextView) findViewById(R.id.reservedMsg);


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
                    System.out.println(table.getSmokers());
                    smokers.setText((table.getSmokers()).toUpperCase());

                    switch (table.getSmokers()) {
                        case "true":
                            smokers.setText("Smoking");
                            break;
                        case "false":
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

    }

}
