package com.example.achat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity1 extends AppCompatActivity {
    String phone;
    EditText ph=(EditText)findViewById(R.id.ph);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification);


    }
    void submit(View view){
        phone=ph.getText().toString();
        /*Intent intent=new Intent(MainActivity1.this,name_of_Activity.class);
        intent.putExtra(phone,phone);
        startActivity(intent);*/
    }
}
