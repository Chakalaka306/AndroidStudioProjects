package com.example.andreas.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            EditText uname = (EditText) findViewById(R.id.TVusername);
            EditText pw = (EditText)findViewById(R.id.TVpassword);

            String str_uname = uname.getText().toString();
            String str_pw = pw.getText().toString();

            //hier kommet die db verbindung hin wo das pw und der username von der db in eine string variable gesetzt wird

            String uname_db = "andi";
            String pw_db = "123";

            if(str_uname == uname_db && str_pw == pw_db){
                setContentView(R.layout.activity_navigation);
            }
        }
        if (v.getId() == R.id.btnRegister) {
            setContentView(R.layout.activity_registry);
        }
    }
}
