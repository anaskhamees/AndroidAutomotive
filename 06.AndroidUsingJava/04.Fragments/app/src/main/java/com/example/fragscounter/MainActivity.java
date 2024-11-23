package com.example.fragscounter;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

interface Communicator {
    public void setCounter(String str);
}

public class MainActivity extends AppCompatActivity implements Communicator{

    private static final String DYNAMICFRAG = "dynamicfragb";
    private DynamicFragment dynamicFrag;
    private FragmentManager mgr ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mgr = getSupportFragmentManager();
        if (savedInstanceState == null)
        {
            dynamicFrag = new DynamicFragment();
            FragmentTransaction tran = mgr.beginTransaction();
            tran.add(R.id.dynamicFragB,dynamicFrag,DYNAMICFRAG);
            tran.commit();
        }
        else
        {
          dynamicFrag = (DynamicFragment) mgr.findFragmentByTag(DYNAMICFRAG) ;
        }

    }

    @Override
    public void setCounter(String str) {
         mgr = getSupportFragmentManager();
        DynamicFragment fragB = (DynamicFragment) mgr.findFragmentById(R.id.dynamicFragB);
        Log.d("MainActivity", "DynamicFragment is: " + (fragB == null ? "null" : "not null"));

        if (fragB != null)
        {
            fragB.setText(str);

        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}