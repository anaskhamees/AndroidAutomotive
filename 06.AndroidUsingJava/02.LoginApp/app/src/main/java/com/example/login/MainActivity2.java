package com.example.login;

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

import com.example.login.db.DataBaseAdaptor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    private static final String PREFS_NAME = "PREFS_NAME";
    private static final String FILE_NAME = "FILE_NAME";

    Button btnWriteSharedP;
    Button btnReadSharedP;
    Button btnWriteInStorage;
    Button btnReadInStorage;
    Button btnWriteSQL;
    Button btnReadSQL;
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

        btnWriteSharedP=findViewById(R.id.btnWSP_ID);
        btnReadSharedP=findViewById(R.id.btnRshP_ID);

        btnWriteSharedP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs=getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= prefs.edit();
                editor.putString(MainActivity.PHONE,txtPhone.getText().toString());
                editor.putString(MainActivity.MESSAGE,txtMsg.getText().toString());
                editor.commit();
                txtPhone.setText("");
                txtMsg.setText("");

            }
        });

        btnReadSharedP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs=getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
                txtPhone.setText(prefs.getString(MainActivity.PHONE,"N/A"));
                txtMsg.setText(prefs.getString(MainActivity.MESSAGE,"N/A"));
            }
        });


        btnWriteInStorage=findViewById(R.id.btnWIS_ID);
        btnReadInStorage=findViewById(R.id.btnRIS_ID);

        btnWriteInStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                    DataOutputStream dos=new DataOutputStream(fos);
                    dos.writeUTF(txtPhone.getText().toString());
                    dos.writeUTF(txtMsg.getText().toString());
                    txtPhone.setText("");
                    txtMsg.setText("");
                    fos.close();
                    dos.close();
                } catch (FileNotFoundException e) {
                    Toast.makeText(MainActivity2.this, "Could not create a file", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(MainActivity2.this, "Could not write in the file", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnReadInStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream fis= null;
                try {
                    fis = openFileInput(FILE_NAME);
                    DataInputStream dis=new DataInputStream(fis);
                    txtPhone.setText(dis.readUTF());
                    txtMsg.setText(dis.readUTF());
                    dis.close();
                    fis.close();
                } catch (FileNotFoundException e) {
                    Toast.makeText(MainActivity2.this, "Could not Locate the file", Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    Toast.makeText(MainActivity2.this, "Could not read the file", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnWriteSQL=findViewById(R.id.SQLW_ID);
        btnReadSQL=findViewById(R.id.SqlR_ID);

        btnWriteSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(txtMsg.getText().toString().isEmpty())&&!(txtPhone.getText().toString().isEmpty()))
                {
                    Message message =new Message(txtMsg.getText().toString(),txtPhone.getText().toString());
                    DataBaseAdaptor dataBaseAdaptor=new DataBaseAdaptor(getApplicationContext());
                    dataBaseAdaptor.insertMessage(message);
                    txtMsg.setText("");
                }
            }
        });

        btnReadSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneKey=txtPhone.getText().toString();
                DataBaseAdaptor dataBaseAdaptor=new DataBaseAdaptor(getApplicationContext());
                Message message=dataBaseAdaptor.getMessageByPhone(phoneKey);
                txtMsg.setText(message.getMessage());
            }
        });
    }
}