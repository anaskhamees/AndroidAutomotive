package com.example.data.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab2.db.DataBaseAdapter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Activity2 extends AppCompatActivity {

    private static final String PREFSNAME = "sharedfile";
    private static final String FILENAME = "file";
    private Button btnBack ;
    private Button btnWrShr;
    private Button btnRdShr;
    private Button btnWrStr;
    private Button btnRdStr;
    private Button btnWrSql;
    private Button btnRdSql;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        TextView phone = findViewById(R.id.txtph);
        TextView message = findViewById(R.id.txtmssg);
        Intent commingIntent = getIntent();
        phone.setText(commingIntent.getStringExtra(MainActivity.PHONE));
        message.setText(commingIntent.getStringExtra(MainActivity.MESSAGE));
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnWrShr = findViewById(R.id.btnWrSh);
        btnRdShr = findViewById(R.id.btnRdSh);
        btnWrShr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences shared = getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString(MainActivity.MESSAGE,message.getText().toString());
                editor.putString(MainActivity.PHONE,phone.getText().toString());
                editor.commit();
                message.setText("");
                phone.setText("");
            }
        });
        btnRdShr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences shared = getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE);
                message.setText(shared.getString(MainActivity.MESSAGE,"N/A"));
                phone.setText(shared.getString(MainActivity.PHONE,"N/A"));

            }
        });
        btnWrStr = findViewById(R.id.btnWrStr);
        btnRdStr = findViewById(R.id.btnRdStr);
        btnWrStr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos = openFileOutput(FILENAME,Context.MODE_PRIVATE);
                    DataOutputStream dos = new DataOutputStream(fos);
                    dos.writeUTF(message.getText().toString());
                    dos.writeUTF(phone.getText().toString());
                    dos.close();
                    fos.close();
                    message.setText("");
                    phone.setText("");

                } catch (FileNotFoundException e) {
                    Toast.makeText(Activity2.this, "", Toast.LENGTH_SHORT).show();;
                } catch (IOException e) {
                    Toast.makeText(Activity2.this, "", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnRdStr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fis = openFileInput(FILENAME);
                    DataInputStream dis = new DataInputStream(fis);
                    message.setText(dis.readUTF());
                    phone.setText(dis.readUTF());
                    fis.close();
                    dis.close();
                } catch (FileNotFoundException e) {
                    Toast.makeText(Activity2.this, "", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(Activity2.this, "", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnWrSql = findViewById(R.id.btnWrSql);
        btnRdSql = findViewById(R.id.btnRdSql);
        btnWrSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(message.getText().toString().isEmpty())&&!(phone.getText().toString().isEmpty()))
                {
                    Message messagee = new Message(message.getText().toString(),phone.getText().toString());
                    DataBaseAdapter dbad = new DataBaseAdapter(getApplicationContext());
                    dbad.insertMessage(messagee);
                    message.setText("");
                }

            }
        });
        btnRdSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = phone.getText().toString();
                DataBaseAdapter dbad = new DataBaseAdapter(getApplicationContext());
                Message msg = dbad.getMessageByPhone(str);
                message.setText(msg.getMessage());
            }
        });
        }
    }
