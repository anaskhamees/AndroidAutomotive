## CAR Service

- **Go to this Path to Create the HAL Drivers**

  ```c++
  /media/iti/468469bc-700b-47e2-ad57-0efb9731e806/android-14.0.0_r67/hardware/interfaces/automotive/vehicle/aidl/impl/fake_impl/hardware/src
  ```

  ### 1. Create GPIO Driver 

  #### 1.1. GPIO Header File 

  ```c++
  #pragma once
  #include<string>
  
  class GPIO_HAL
  { 
          public :
                  bool exportGPIO(int pin);
                  bool setGPIODirection(int pin,const std::string &pinDirection);
                  bool setGPIOValue(int pin, bool value);
                  bool getGPIOValue(int pin, bool *value);
  
  
  };
  ```

  #### 1.2. GPIO Source File 



```c++
#include "GPIO.h"
#include <fstream>
#include <string>

bool GPIO_HAL::exportGPIO(int pin)
{
	std::ofstream exportFile("/sys/class/gpio/export");
	if(!exportFile)
	{
		printf("Faild To Open The file [Export File]");
		return false;
	}

	exportFile <<pin;
	return exportFile.good();

}

bool GPIO_HAL::setGPIODirection(int pin, const std::string &pinDirection)
{
	std::string pinDirectionPath = "/sys/class/gpio/gpio" + std::to_string(pin) + "/direction";
	std::ofstream directionFile(pinDirectionPath);
	if(!directionFile)
		return false;
	directionFile<<pinDirection;
	return directionFile.good();

}


bool GPIO_HAL::setGPIOValue(int pin, bool pinValue)
{
	 std::string pinValuePath="/sys/class/gpio/gpio"+std::to_string(pin)+"/value";
	std::ofstream valueFile(pinValuePath);
        if(!valueFile)
                return false;
        valueFile <<(pinValue?"1":"0");
        return valueFile.good();
}

bool GPIO_HAL::getGPIOValue(int pin,bool *pinValue)
{
	std::string pinValuePath="/sys/class/gpio/gpio"+std::to_string(pin)+"/value";
        std::ifstream valueFile(pinValuePath);
        if(!valueFile)
                return false;

	int gpioValue;
	valueFile>>gpioValue;
	*pinValue=(gpioValue==1);
	return valueFile.good();

}
```



### Explanation of The GPIO Code 

```c++
bool GPIO_HAL::exportGPIO(int pin)
{ 
        std::ofstream exportFile("/sys/class/gpio/export");
        if(!exportFile)
        { 
                printf("Faild To Open The file [Export File]");
                return false;
        }

        exportFile <<pin;
        return exportFile.good();

}

```

>### Purpose of the Function
>
>This function is designed to "export" a specific GPIO pin for use by a user-space application on Linux. Exporting a GPIO pin makes it accessible through the Linux GPIO sysfs interface, located under `/sys/class/gpio`.
>
>------
>
>### Line-by-Line Explanation
>
>#### **Line: `std::ofstream exportFile("/sys/class/gpio/export");`**
>
>- **What it does:**
>  This creates an output file stream (`std::ofstream`) object named `exportFile`. It opens the file `/sys/class/gpio/export` for writing.
>- **Purpose of `/sys/class/gpio/export`:**
>  In Linux, the `export` file in the GPIO sysfs interface is used to "export" a GPIO pin, making it accessible through sysfs. Writing the GPIO pin number to this file tells the kernel to expose the corresponding GPIO directory under `/sys/class/gpio/gpio<pin>`.
>- **Example:** If you write `22` to `/sys/class/gpio/export`, the kernel will create the directory `/sys/class/gpio/gpio22/`, where you can interact with the GPIO pin (e.g., setting its direction, value).
>- **Potential Error:**
>  If the `export` file cannot be opened (e.g., due to missing permissions or incorrect path), this line will fail, and `exportFile` will be in a failed state.
>
>------
>
>#### **Line: `exportFile << pin;`**
>
>- **What it does:**
>  This writes the `pin` number (an integer) to the `exportFile`.
>
>- **How it works:**
>  The `<<` operator is used in C++ to write data to a file. In this case, the `pin` number is written as plain text to `/sys/class/gpio/export`.
>
>- **Purpose:**
>  Writing the `pin` number to the `exportFile` tells the Linux kernel to export the specified GPIO pin. For example, if `pin = 17`, this operation effectively executes:
>
>  ```
>  
>  ```
>
>



```c++
bool GPIO_HAL::setGPIODirection(int pin, const std::string &pinDirection) 
{ 
        std::string pinDirectionPath="/sys/class/gpio/gpio"+std::to_string(pin)+"/direction";
        std::ofstream directionFile(pinDirectionPath);
        if(!directionFile)
                return false;
        directionFile<<pinDirection;
        return directionFile.good();

}

```

>### **Purpose of the Function**
>
>This function sets the direction of a specific GPIO pin (input or output) in the Linux GPIO sysfs interface.
>
>- **GPIO Direction:**
>  Each GPIO pin can typically be configured as an input (to read signals) or output (to send signals). This configuration is done by writing `"in"` or `"out"` to the `direction` file associated with the GPIO pin.
>
>------
>
>### **Line-by-Line Explanation**
>
>#### **Line: `std::string pinDirectionPath="/sys/class/gpio/gpio"+std::to_string(pin)+"/direction";`**
>
>- **What it does:**
>  This creates the file path to the `pinDirection` file for the specified GPIO pin.
>
>- **Details:**
>
>  - The directory `/sys/class/gpio/gpio<pin>/` is created when the GPIO pin is successfully exported.
>  - Inside this directory, the `direction` file (`pinDirection`) is used to configure the pin direction (input or output).
>
>- **Example:**
>  If `pin = 22`, the resulting path will be:
>
>  ```
>  
>  ```
>
>



```c++
bool GPIO_HAL::getGPIOValue(int pin, bool *pinValue)
{
        std::string pinValuePath = "/sys/class/gpio/gpio" + std::to_string(pin) + "/value";
        std::ifstream valueFile(pinValuePath);
        if (!valueFile)
                return false;

        int gpioValue;
        valueFile >> gpioValue;
        *pinValue = (gpioValue == 1);
        return valueFile.good();
}
```

>### **Line-by-Line Explanation**
>
>#### **Line: `std::string pinValuePath = "/sys/class/gpio/gpio" + std::to_string(pin) + "/value";`**
>
>- **What it does:**
>  Constructs the file path for the `value` file of the specified GPIO pin.
>
>- Purpose:
>
>  The 
>
>  ```
>  value
>  ```
>
>   file in the GPIO sysfs interface contains the current value of the GPIO pin. It is:
>
>  - `0` for low (logic level 0).
>  - `1` for high (logic level 1).
>
>------
>
>#### **Line: `std::ifstream valueFile(pinValuePath);`**
>
>- **What it does:**
>  Creates an input file stream (`std::ifstream`) to open the `value` file for reading.
>- **Purpose:**
>  This allows reading the current value of the GPIO pin.
>- **Error Handling:**
>  If the file does not exist (e.g., the GPIO pin was not properly exported), or if permissions are insufficient, the stream will fail to open, and `valueFile` will be in a failed state.
>
>------
>
>#### **Line: `if (!valueFile)`**
>
>- **What it does:**
>  Checks if the file stream failed to open.
>- **Purpose:**
>  If the file cannot be opened (e.g., due to missing permissions or an incorrect path), the function returns `false`, signaling failure.
>
>------
>
>#### **Line: `valueFile >> gpioValue;`**
>
>- **What it does:**
>  Reads the integer value from the `value` file into the variable `gpioValue`.
>- **Purpose:**
>  This retrieves the current state of the GPIO pin:
>  - `0` for low (logic level 0).
>  - `1` for high (logic level 1).
>
>------
>
>#### **Line: `\*pinValue = (gpioValue == 1);`**
>
>- **What it does:**
>  Sets the value of `*pinValue` based on whether `gpioValue` is `1` (true) or `0` (false).
>- **Purpose:**
>  Converts the integer read from the file into a `bool` type for use in the program.
>
>------
>
>#### **Line: `return valueFile.good();`**
>
>- **What it does:**
>  Checks whether the file stream is still in a good state after reading.
>- **Purpose:**
>  Ensures that the read operation was successful and no errors occurred during the process.
>
>



### 2. Create I2c Driver

```c++
#pragma once
class I2C_HAL
{
	public:
		bool getADCValue(int channel,int *ADCValue);

};
```

 

```c++
#include "I2C.h"
#include <fstream>
#include <string>

bool I2C_HAL::getADCValue(int channel,int *ADCValue)
{
	std::string valuePath = "/dev/i2c-1/";
std::ofstream valueFile(valuePath);
    if (!valueFile) return false;
    int adcValue = 0x4801C383 ;
    switch (channel)
    {
    case 0:
    break;
    case 1:adcValue = 0x4801D383;
    break;
    case 2:adcValue = 0x4801E383;
    break;
    case 3:adcValue = 0x4801F383;
    break;
    default:
    return false;
    }
    valueFile << adcValue  ;
    adcValue = 0x4800;
    valueFile << adcValue;
    valueFile.close();

    std::ifstream valueIn(valuePath);
    if (!valueIn) return false;
    int gpioValue;
    valueIn >> gpioValue;
    *value = (gpioValue == 1);
     return valueIn.good();

}
```



## 2. Create properties for GPIO [LED] and I2c [Temperature Value] 

- Go to this path 

  ```
  /media/iti/468469bc-700b-47e2-ad57-0efb9731e806/android-14.0.0_r67/hardware/interfaces/automotive/vehicle/aidl/impl/utils/test_vendor_properties/android/hardware/automotive/vehicle/TestVendorProperty.aidl
  ```

  ![image-20241121233750688](README.assets/image-20241121233750688.png)

- Go to this path to configure your properties in the JSON File

  ```
  /media/iti/468469bc-700b-47e2-ad57-0efb9731e806/android-14.0.0_r67/hardware/interfaces/automotive/vehicle/aidl/impl/default_config/config/TestProperties.json
  ```

  ![image-20241121234111593](README.assets/image-20241121234111593.png)

- Edit The Fake Vehicle HAL (switch case)

- MaybeSet

  ```c++
  case toInt(TestVendorProperty::LED):
      {
      	GPIO_HAL gpio;
      	int pin=17;
      	gpio.exportGPIO(pin);
      	gpio.setGPIODirection(pin,"out");
      	
      	// Get current GPIO state
          bool currentState;
  
          if (value.value.int32Values[0] == 1)
          {
              gpio.setGPIOValue(gpioPin, true);
          }
          else
          {
              gpio.setGPIOValue(gpioPin, false);
          }
      	//gpio.setGPIOValue(pin,1);
      	
      	    return {};
      	
      }
  ```

  

- MaybeGet

  ```c++
   case toInt(TestVendorProperty::LED): {
              GPIO_HAL gpio; 
              int pin=17; 
              bool value;  // Declare a variable to store the GPIO value
              ALOGD("readvalue");
              bool state = gpio.getGPIOValue(pin, &value);
              ALOGD("readvalue + ")     ;   
              result = mValuePool->obtainInt32(value);
              return result;  // Return the status of the GPIO read operation
          }
          
           case toInt(TestVendorProperty::TemperatureAnasValue): {
                *isSpecialValue = true;
              int rpm = getRpm();  // Call your function to retrieve the RPM value
              result = mValuePool->obtainInt32(rpm);
              ALOGD("RPM %d",rpm);
              result.value()->prop = propId;
              result.value()->areaId = 0;
              //result.value()->timestamp = elapsedRealtimeNano();
              result.value()->timestamp = getRpm();
              return result;
   
          }
  
  ```

  

-----------------------------------

### Configure the Name and Permissions of the costume properties 

----------------------------------

- Go to the following path to name our properties ID and set the property permissions

```
/media/iti/468469bc-700b-47e2-ad57-0efb9731e806/android-14.0.0_r67/packages/services/Car/car-lib/src/android/car/VehiclePropertyIds.java
```

- I2C  ans GPIO Properties :

  ![image-20241122015203748](README.assets/image-20241122015203748.png)

- Create My properties Permissions, go to this path 

  ```
  /media/iti/468469bc-700b-47e2-ad57-0efb9731e806/android-14.0.0_r67/packages/services/Car/car-lib/src/android/car/Car.java
  ```

  ![image-20241122020350164](README.assets/image-20241122020350164.png)



- Create the Application Layer

  - Main Activity 

  ```java
  package com.luxoft.vhallapp;
  
  import android.car.Car;
  import android.car.hardware.CarPropertyValue;
  import android.car.hardware.property.CarPropertyManager;
  import android.os.Bundle;
  import android.util.Log;
  import android.widget.Button;
  import android.widget.TextView;
  import android.widget.Toast;
  import android.os.Handler;
  import android.os.Looper;
  import android.os.Message;
  import androidx.appcompat.app.AppCompatActivity;
  
  public class MainActivity extends AppCompatActivity {
  
      private CarPropertyManager mCarPropertyManager;
      private Car mCar;
      private CarPropertyManager.CarPropertyEventCallback mPropertyCallback;
      private Button btnGpio;
      private TextView txtGetRPMValue;
      private TextView txtLed;
  
      private Handler handler;  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          btnGpio = findViewById(R.id.btnGpio);
          txtLed=findViewById(R.id.LEDtxt);
          txtGetRPMValue = findViewById(R.id.txtGetRPMValue);
  handler = new Handler(Looper.getMainLooper()){
              @Override
              public void handleMessage( Message msg) {
                  super.handleMessage(msg);
                  System.out.println("entered Handler");
                  txtGetRPMValue.setText(msg.arg1+"");
  
                  txtLed.setText(msg.arg1+"");
  
              }
          };
          
          mCar = Car.createCar(this);
          mCarPropertyManager = (CarPropertyManager) mCar.getCarManager(CarPropertyManager.class);
  
         
         int gpioPropertyID = 557842944;
          int areaId = 0; 
  
          int temperaturePropertyID=557842945;
         // int gpioPropertyID=557842944;
  
  
          mPropertyCallback = new CarPropertyManager.CarPropertyEventCallback() {
              @Override
              public void onChangeEvent(CarPropertyValue carPropertyValue) {
                  if (carPropertyValue.getPropertyId() == gpioPropertyID) {
                      try {
                          int fuelLevel = (Integer) carPropertyValue.getValue();
                          txtLed.setText("Fuel Level: " + fuelLevel + "%");
  
                          Log.d("MainActivity", "Fuel tank updated to: " + fuelLevel + "%");
                      } catch (Exception e) {
                          Log.e("MainActivity", "Error processing FuelTank property value", e);
                      }
                  }
                 else if (carPropertyValue.getPropertyId() == temperaturePropertyID) {
                      try {
                          int tempLevel = (Integer) carPropertyValue.getValue();
                          txtGetRPMValue.setText("Temp Level: " + tempLevel);
                          Log.d("MainActivity", "Temp  updated to: " + tempLevel);
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
  
          mCarPropertyManager.registerCallback(mPropertyCallback, gpioPropertyID, CarPropertyManager.SENSOR_RATE_ONCHANGE);
          mCarPropertyManager.registerCallback(mPropertyCallback, temperaturePropertyID, CarPropertyManager.SENSOR_RATE_ONCHANGE);
  
          btnGpio.setOnClickListener(v -> {
              try {
                  CarPropertyValue<Integer> fuelTankValue = mCarPropertyManager.getProperty(Integer.class, gpioPropertyID, areaId);
                  int currentFuelLevel = fuelTankValue.getValue();
                  
                  if (currentFuelLevel == 1) {
                      mCarPropertyManager.setProperty(Integer.class, gpioPropertyID, areaId, 0);
                  } else {
                      mCarPropertyManager.setProperty(Integer.class, gpioPropertyID, areaId, 1);
                  }
                  
  
              } catch (SecurityException e) {
                  Toast.makeText(MainActivity.this, "Permission denied for FuelTank property.", Toast.LENGTH_SHORT).show();
              } catch (Exception e) {
                  Toast.makeText(MainActivity.this, "Error fetching FuelTank value.", Toast.LENGTH_SHORT).show();
                  e.printStackTrace();
              }
          });
  
  
           new Thread(){
              @Override
              public void run() {
                  super.run();
                  while (true){
  
                      Message msg = handler.obtainMessage();
                      
  
                      CarPropertyValue<Integer> propertyValue = mCarPropertyManager.getProperty(Integer.class, temperaturePropertyID, areaId);
                 msg.arg1=  propertyValue.getValue();
                      try {
                          sleep(500);
                      } catch (InterruptedException e) {
                          throw new RuntimeException(e);
                      }
                  }
              }
          }.start();
  
      }
  
      @Override
      protected void onDestroy() {
          super.onDestroy();
          if (mCarPropertyManager != null && mPropertyCallback != null) {
              mCarPropertyManager.unregisterCallback(mPropertyCallback);
          }
      }
  
      
         
       
  }
  
  ```

  - XML File

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
       package="com.luxoft.vhallapp">
        <uses-permission android:name="android.car.permission.READ_CAR_PROPERTY" />
        <uses-permission android:name="android.car.permission.WRITE_CAR_PROPERTY" />
            <uses-permission android:name="android.car.permission.FuelTank" />
        <uses-permission android:name="android.car.permission.CAR_VENDOR_EXTENSION"/>
        <uses-permission android:name="com.vendor.permission.FUELTANK" />
    
        <!--/******************************anas***********************************/    -->
        <uses-permission android:name="android.car.permission.TEMPERATURE"/>
        <uses-permission android:name="android.car.permission.GPIO"/>
        <!--/******************************anas***********************************/    -->
    
        <uses-permission android:name="android.car.permission.FUELDOOR"/>
        <uses-permission android:name="com.vendor.permission.FUELDOOR" />
        <uses-permission android:name="android.car.permission.BATTERY"/>
        <uses-permission android:name="com.vendor.permission.BATTERY" />
    
    
        
    
        
    
            
            <uses-permission android:name="android.car.hardware.property.VehicleVendorPermission.PERMISSION_GET_CAR_VENDOR_CATEGORY_INFO" />
            <uses-permission android:name="android.car.hardware.property.VehicleVendorPermission.PERMISSION_SET_CAR_VENDOR_CATEGORY_SEAT" />
    
    
    
        <application
            android:allowBackup="true"
            android:dataExtractionRules="@xml/data_extraction_rules"
            android:fullBackupContent="@xml/backup_rules"
            android:icon="@mipmap/ic_launcher"
            android:label="@string/app_name"
            android:roundIcon="@mipmap/ic_launcher_round"
            android:supportsRtl="true"
            android:theme="@style/Theme.VHallApp"
            tools:targetApi="31">
            <activity
                android:name=".MainActivity"
                android:exported="true">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />
    
                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </activity>
        </application>
    
    </manifest>
    ```

    - Manifest File 

      ```xml
      <?xml version="1.0" encoding="utf-8"?>
      <manifest xmlns:android="http://schemas.android.com/apk/res/android"
          xmlns:tools="http://schemas.android.com/tools"
         package="com.luxoft.vhallapp">
          <uses-permission android:name="android.car.permission.READ_CAR_PROPERTY" />
          <uses-permission android:name="android.car.permission.WRITE_CAR_PROPERTY" />
              <uses-permission android:name="android.car.permission.FuelTank" />
          <uses-permission android:name="android.car.permission.CAR_VENDOR_EXTENSION"/>
          <uses-permission android:name="com.vendor.permission.FUELTANK" />
      
          <!--/******************************anas***********************************/    -->
          <uses-permission android:name="android.car.permission.TEMPERATURE"/>
          <uses-permission android:name="android.car.permission.GPIO"/>
          <!--/******************************anas***********************************/    -->
      
          <uses-permission android:name="android.car.permission.FUELDOOR"/>
          <uses-permission android:name="com.vendor.permission.FUELDOOR" />
          <uses-permission android:name="android.car.permission.BATTERY"/>
          <uses-permission android:name="com.vendor.permission.BATTERY" />
      
      
          
      
          
      
              
              <uses-permission android:name="android.car.hardware.property.VehicleVendorPermission.PERMISSION_GET_CAR_VENDOR_CATEGORY_INFO" />
              <uses-permission android:name="android.car.hardware.property.VehicleVendorPermission.PERMISSION_SET_CAR_VENDOR_CATEGORY_SEAT" />
      
      
      
          <application
              android:allowBackup="true"
              android:dataExtractionRules="@xml/data_extraction_rules"
              android:fullBackupContent="@xml/backup_rules"
              android:icon="@mipmap/ic_launcher"
              android:label="@string/app_name"
              android:roundIcon="@mipmap/ic_launcher_round"
              android:supportsRtl="true"
              android:theme="@style/Theme.VHallApp"
              tools:targetApi="31">
              <activity
                  android:name=".MainActivity"
                  android:exported="true">
                  <intent-filter>
                      <action android:name="android.intent.action.MAIN" />
      
                      <category android:name="android.intent.category.LAUNCHER" />
                  </intent-filter>
              </activity>
          </application>
      
      </manifest>
      ```

       

![image-20241122024046066](README.assets/image-20241122024046066.png)

- Add the name of your App in rpi4 Make file , go to this path and add your app

  ```
  /media/iti/468469bc-700b-47e2-ad57-0efb9731e806/android-14.0.0_r67/device/brcm/rpi4/aosp_rpi4_car.mk
  ```

  

![image-20241122034945543](README.assets/image-20241122034945543.png)



- Go to AOSP Path and Run 

  ```
  source build/envsetup.sh
  ```

  ```
  lunch aosp_rpi4_car-ap2a-userdebug
  ```

  ```
  make bootimage systemimage vendorimage -j$(nproc)
  ```

  ```
  ./rpi4-mkimg.sh 
  ```

  

then Flash your image