package com.luxoft.vhallapp;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CarPropertyManager mCarPropertyManager;
    private Car mCar;
    private CarPropertyManager.CarPropertyEventCallback mPropertyCallback;
    private Button btnGpio;
    private TextView txtGetFuelValue;
    private TextView txtGetTempValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnGpio = findViewById(R.id.btnGpio);
        txtGetFuelValue = findViewById(R.id.txtGetGPIOValue);
        txtGetTempValue = findViewById(R.id.txtGetRPMValue);

        
        mCar = Car.createCar(this);
        mCarPropertyManager = (CarPropertyManager) mCar.getCarManager(CarPropertyManager.class);

       
        int fuelTankPropertyId = 557842944;
        int tempPropertyId = 557842945;
        int areaId = 0; 

        mPropertyCallback = new CarPropertyManager.CarPropertyEventCallback() {
            @Override
            public void onChangeEvent(CarPropertyValue carPropertyValue) {
                if (carPropertyValue.getPropertyId() == fuelTankPropertyId) {
                    try {
                        int fuelLevel = (Integer) carPropertyValue.getValue();
                        txtGetFuelValue.setText("Fuel Tank Level: " + fuelLevel + "%");
                        Log.d("MainActivity", "Fuel tank updated to: " + fuelLevel + "%");
                    } catch (Exception e) {
                        Log.e("MainActivity", "Error processing FuelTank property value", e);
                    }
                }
                else if (carPropertyValue.getPropertyId() == tempPropertyId) {
                    try {
                        int temp = (Integer) carPropertyValue.getValue();
                        txtGetTempValue.setText("Temp: " + temp + "c");
                        Log.d("MainActivity", "Temp updated to: " + temp + "c");
                    } catch (Exception e) {
                        Log.e("MainActivity", "Error processing Temp property value", e);
                    }
                }
            }

            @Override
            public void onErrorEvent(int propertyId, int zone) {
                Toast.makeText(MainActivity.this, "Error: Property " + propertyId + " in zone " + zone, Toast.LENGTH_SHORT).show();
            }
        };

        mCarPropertyManager.registerCallback(mPropertyCallback, fuelTankPropertyId, CarPropertyManager.SENSOR_RATE_ONCHANGE);
        mCarPropertyManager.registerCallback(mPropertyCallback, tempPropertyId, CarPropertyManager.SENSOR_RATE_ONCHANGE);

        btnGpio.setOnClickListener(v -> {
            try {
                CarPropertyValue<Integer> fuelTankValue = mCarPropertyManager.getProperty(Integer.class, fuelTankPropertyId, areaId);
                CarPropertyValue<Integer> TempValue = mCarPropertyManager.getProperty(Integer.class, tempPropertyId, areaId);
                int TempValue2= TempValue.getValue();
                int currentFuelLevel = fuelTankValue.getValue();
                
                if (currentFuelLevel == 1) {
                    mCarPropertyManager.setProperty(Integer.class, fuelTankPropertyId, areaId, 0);
                } else {
                    mCarPropertyManager.setProperty(Integer.class, fuelTankPropertyId, areaId, 1);
                }
                
                currentFuelLevel = fuelTankValue.getValue();
                txtGetFuelValue.setText("Fuel Tank Level:::: " + currentFuelLevel + "%");
                txtGetTempValue.setText("Temp: " + TempValue2 + "c");

            } catch (SecurityException e) {
                Toast.makeText(MainActivity.this, "Permission denied for FuelTank property.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Error fetching FuelTank value.", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCarPropertyManager != null && mPropertyCallback != null) {
            mCarPropertyManager.unregisterCallback(mPropertyCallback);
        }
    }
}
