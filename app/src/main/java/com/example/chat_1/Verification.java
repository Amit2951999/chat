package com.example.chat_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.google.firebase.auth.FirebaseAuth;



public class Verification extends Activity {
    String phone,name;
    EditText naMe;
    EditText ph;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification);
        naMe=findViewById(R.id.name1);
        ph=findViewById(R.id.ph);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void Submit(View view){
        name=naMe.getText().toString();
        phone=ph.getText().toString();
        if(name.isEmpty()&&phone.isEmpty()){
            naMe.setError("Phone number is required");
            naMe.requestFocus();
            ph.setError("Phone number is required");
            ph.requestFocus();
        }
        else if(name.isEmpty()){
            naMe.setError("Phone number is required");
            naMe.requestFocus();
        }
        else if(phone.isEmpty()){
            ph.setError("Phone number is required");
            ph.requestFocus();
        }
        else if(phone.length()<10){
            ph.setError("Please enter valid phone number");
            ph.requestFocus();
        }
        else if(phone.length()==10){
            Intent i = new Intent(this, Otp.class);
            i.putExtra("phone", "+91"+phone);
            i.putExtra("name", name);
            startActivity(i);
        }
    }

}
