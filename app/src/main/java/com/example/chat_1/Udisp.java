package com.example.chat_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Udisp extends AppCompatActivity {
    TextView ph,na,nxt;
    String n,p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        ph=findViewById(R.id.Uph);
        na=findViewById(R.id.Uname);
        nxt=findViewById(R.id.nxt1);
        Intent i=getIntent();
        n=i.getStringExtra("name");
        p=i.getStringExtra("phone");
        ph.setText(n);
        na.setText(p);
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Udisp.this, UsersScreen.class);
                startActivity(intent);
            }
        });
    }
}
