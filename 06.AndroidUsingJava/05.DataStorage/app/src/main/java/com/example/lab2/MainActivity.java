package com.example.data.db;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    public static final String MESSAGE = "Message";
    public static final String PHONE="Phone";
    private Button btnClose;
    private Button btnNext;
    private EditText edttxtMessage;
    private EditText edtTxtPhone;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnClose = findViewById(R.id.btnClose);
        btnNext = findViewById(R.id.btnNext);
        edttxtMessage = findViewById(R.id.edtTxtMessage);
        edtTxtPhone = findViewById(R.id.edtTxtPhone);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent outIntent = new Intent(MainActivity.this ,Activity2.class);
                                           String ph = edtTxtPhone.getText().toString();
                                           String mssg = edttxtMessage.getText().toString();
                                           outIntent.putExtra(MainActivity.MESSAGE,mssg);
                                           outIntent.putExtra(MainActivity.PHONE,ph);
                                           startActivity(outIntent);
                                       }
                                   }

        );
        }

    }
