package com.example.aleksej.papajohnspizzeria;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONObject;

public class SingleTableActivity extends AppCompatActivity {

    TextView tableNo,type,smokers,reserved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_table);
        String id = getIntent().getStringExtra("tableNo");
        System.out.println(id);

        initRealEstate(id);
    }

    @SuppressLint("HandlerLeak")
    public void initRealEstate(String id){
        Api.getJSON("http://192.168.0.10:5000/json/"+id, new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                String odgovor = getJson();

                try {
                    JSONObject object = new JSONObject(odgovor);
                    Table table = Table.parseJSON(object);

                    tableNo =(TextView) findViewById(R.id.tableNo);
                    type =(TextView) findViewById(R.id.type);
                    smokers =(TextView) findViewById(R.id.smokers);
                    reserved =(TextView) findViewById(R.id.reserved);

                    tableNo.setText("Table No. : " + String.valueOf(table.getTableNo()));
                    type.setText("Type: " + table.getType());
                    smokers.setText("Smoking or Non Smoking: " + table.getSmokers());
                    reserved.setText("Reserved: " + String.valueOf(table.isReserved()));

                } catch (Exception e) {
                    //((TextView)findViewById(R.id.text1)).setText(odgovor);
                }
                //((TextView)findViewById(R.id.labelJson)).setText(odgovor);

            }

        });

    }
}
