package com.example.orientationcounts;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;

    /* Key for saving instance state*/
    private static final String COUNTER_KEY = "counter";

    /* TextView to display the counter value*/
    private TextView counterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Get the reference to the TextView */
        counterTextView = findViewById(R.id.counterTextView);

        /* Check if there is a saved instance state and retrieve the counter value*/
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER_KEY, 0);
        }

        /* Increment the counter on each rotation*/
        counter++;

        /* Update the TextView with the current counter value*/
        counterTextView.setText("Rotations: " + counter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        /*Save the counter value to the instance state*/
        outState.putInt(COUNTER_KEY, counter);
    }
}

