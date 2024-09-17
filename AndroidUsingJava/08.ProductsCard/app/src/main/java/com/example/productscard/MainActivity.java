package com.example.productscard;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView title=findViewById(R.id.TitleValuID);
        TextView price=findViewById(R.id.priceValuID);
        TextView brand=findViewById(R.id.brandValuID);
        TextView description=findViewById(R.id.descriptionValuID);
        ImageButton nextBtn=findViewById(R.id.rightArrowID);
        ImageButton prevBtn=findViewById(R.id.leftArrowID);
        ImageView image=findViewById(R.id.imageID);


    }
}