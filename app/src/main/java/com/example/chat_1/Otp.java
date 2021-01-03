package com.example.chat_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Otp extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    TextView textView;
    EditText otp1;
    String name,code;
    String phone;
    String codeSent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);
        otp1 = findViewById(R.id.otp1);


        firebaseAuth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.textView);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        phone = i.getStringExtra("phone");
        String hello = "Hello, " + name + "\n" + phone;
        textView.setText(hello);
        sendVerificationCode();
    }

    private void sendVerificationCode(){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,
                30,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }
        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Otp.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent=s;
        }
    };
    public void Login(View v){

        //sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        //eSharedPreferences=sharedPreferences.edit();
        //eSharedPreferences.putString("name",name);
       // eSharedPreferences.putString("phone",phone);
      //  eSharedPreferences.putBoolean("loggedIn",true);
        //eSharedPreferences.apply();

        code=otp1.getText().toString();
        if(code.isEmpty()){
            otp1.setError("OTP is required");
            otp1.requestFocus();
            return;
        }
        verifyCode(code);
    }
    private void verifyCode(String c){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, c);
        signInWithPhoneAuthCredential(credential);
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();

                   FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                   String UserId=firebaseUser.getUid();

                   reference= FirebaseDatabase.getInstance().getReference("Users").child(UserId);

                   HashMap<String,String> hashMap=new HashMap<>();
                   hashMap.put("id",UserId);
                   hashMap.put("UserName",name);
                   hashMap.put("UserPhone",phone);
                   hashMap.put("status","offline");

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(Otp.this, Udisp.class);
                                intent.putExtra("phone", phone);
                                intent.putExtra("name", name);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(Otp.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(getApplicationContext(),"Incorrect OTP",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
