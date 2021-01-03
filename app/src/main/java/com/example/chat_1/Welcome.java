package com.example.chat_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Welcome extends AppCompatActivity {
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser !=null){
            Intent intent = new Intent(Welcome.this, UsersScreen.class);
            startActivity(intent);
        }


        TextView nxt=findViewById(R.id.nxt);
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this, Verification.class);
                startActivity(intent);
            }
        });
    }
}
