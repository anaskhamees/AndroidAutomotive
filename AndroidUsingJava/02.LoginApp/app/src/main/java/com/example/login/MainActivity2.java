package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        TextView txtPhone=findViewById(R.id.txtPhone);
        TextView txtMsg=findViewById(R.id.txtMsg);
        Intent incomingIntent=getIntent();
        txtPhone.setText(incomingIntent.getStringExtra(MainActivity.PHONE));
        txtMsg.setText(incomingIntent.getStringExtra(MainActivity.MESSAGE));

    }

    public void onBack(View view) {
        finish();
    }
}
