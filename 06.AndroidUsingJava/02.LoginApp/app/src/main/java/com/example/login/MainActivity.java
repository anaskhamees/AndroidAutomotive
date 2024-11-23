package com.example.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    final static String TAG="MainActivity";
    private EditText edtPhone;
    private EditText edtMsg;
    private Button btnNext;
    private Button btnClose;
    public static final String PHONE="PHONE";
    public static final String MESSAGE="MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edtMsg=findViewById(R.id.msgedit);
        edtPhone=findViewById(R.id.editphone);
        btnNext=findViewById(R.id.nextBtn);
        btnClose=findViewById(R.id.btnClose);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent=new Intent(MainActivity.this,MainActivity2.class);
                String phone=edtPhone.getText().toString();
                String msg=edtMsg.getText().toString();
                outIntent.putExtra(MainActivity.PHONE,phone);
                outIntent.putExtra(MainActivity.MESSAGE,msg);
                startActivity(outIntent);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume: ");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause: ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy: ");

    }

    public void onClose(View view) {
        finish();
    }
}