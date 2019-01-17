package com.example.aleksej.papajohnspizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;

public class LogIn extends AppCompatActivity {

    EditText username, password;
    Button signin, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        initComponents();
    }

    private void initComponents(){
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);

        System.out.println(username.getText());

        signin = (Button)findViewById(R.id.signIn);
        register = (Button)findViewById(R.id.register);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!UserAPI.checkLogIn(username.getText().toString(),password.getText().toString())){
                    Intent mainMenu = new Intent(LogIn.this, MainMenu.class);
                    LogIn.this.startActivity(mainMenu);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Incorrect Username or Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
