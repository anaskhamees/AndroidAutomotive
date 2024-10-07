package com.example.fragscounter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragA extends Fragment {

    private static final String COUNTER = "counter";
    private Button counterButton ;
    private int counter=0;
    private Communicator comm;
    public FragA() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        comm = (Communicator) getActivity();

        if (savedInstanceState != null)
        {
            counter = savedInstanceState.getInt(FragA.COUNTER);
            comm.setCounter("0");
        }


        counterButton= view.findViewById(R.id.btnCount);
        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter ++;
            comm.setCounter(""+counter);
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(FragA.COUNTER,counter);
    }
}