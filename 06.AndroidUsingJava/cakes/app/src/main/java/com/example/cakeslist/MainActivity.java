package com.example.cakeslist;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adt;
    Cake[] cakes =new Cake[] {new Cake(R.drawable.vanilla,"vanilla","vanilla cake"),
                        new Cake(R.drawable.choclate,"choclate","choclate cake"),
            new Cake(R.drawable.rainbow,"rainbow","rainbow cake"),
            new Cake(R.drawable.vanilla,"vanilla","vanilla cake"),
            new Cake(R.drawable.choclate,"choclate","choclate cake"),
            new Cake(R.drawable.rainbow,"rainbow","rainbow cake"),
            new Cake(R.drawable.vanilla,"vanilla","vanilla cake"),
            new Cake(R.drawable.choclate,"choclate","choclate cake"),
            new Cake(R.drawable.rainbow,"rainbow","rainbow cake"),
            new Cake(R.drawable.vanilla,"vanilla","vanilla cake"),
            new Cake(R.drawable.choclate,"choclate","choclate cake"),
            new Cake(R.drawable.rainbow,"rainbow","rainbow cake")};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mgr = new LinearLayoutManager(getApplicationContext());
        mgr.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(mgr);
        adt = new MyAdapter(getApplicationContext(), Arrays.asList(cakes));
        recyclerView.setAdapter(adt);
    }
}