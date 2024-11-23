#include "I2C.h"
#include <fstream>
#include <string>


#include <iostream>
#include <unistd.h>
#include <fcntl.h>
#include <sys/ioctl.h>
#include <linux/i2c-dev.h>
#include <stdint.h>
#include <stdexcept>

#define ADS1115_ADDRESS 0x49  // Default I2C address for ADS1115
#define ADS1115_CONVERSION_REGISTER 0x00
#define ADS1115_CONFIG_REGISTER 0x01

// Function to open I2C device and set slave address
int open_i2c(const char* device, int address) {
    int file = open(device, O_RDWR);
    if (file < 0) {
        std::cerr << "Failed to open the bus" << std::endl;
        return -1;
    }

    if (ioctl(file, I2C_SLAVE, address) < 0) {
        std::cerr << "Failed to acquire bus access and/or talk to slave" << std::endl;
        close(file);
        return -1;
    }
    return file;
}

// Function to configure the ADS1115 (set input channel)
void configure_ads1115(int file, int channel) {
    uint16_t config = 0xC383;  // Default settings for 16-bit, 128 SPS, ±4.096V range

    // Set MUX to the selected channel
    switch (channel) {
        case 0: config |= 0x4000; break;  // AIN0
        case 1: config |= 0x5000; break;  // AIN1
        case 2: config |= 0x6000; break;  // AIN2
        case 3: config |= 0x7000; break;  // AIN3
        default:break;
            //throw std::invalid_argument("Invalid channel. Choose from 0, 1, 2, 3.");
    }

    uint8_t data[3];
    data[0] = ADS1115_CONFIG_REGISTER;
    data[1] = (config >> 8) & 0xFF;  // High byte
    data[2] = config & 0xFF;         // Low byte
    if (write(file, data, 3) != 3) {
        std::cerr << "Failed to write to the configuration register" << std::endl;
        close(file);
        exit(1);
    }
}

// Function to read the conversion result from the ADS1115
int16_t read_ads1115(int file) {
    uint8_t buf[2];
    buf[0] = ADS1115_CONVERSION_REGISTER;
    if (write(file, buf, 1) != 1) {
        std::cerr << "Failed to set conversion register" << std::endl;
        close(file);
        exit(1);
    }

    if (read(file, buf, 2) != 2) {
        std::cerr << "Failed to read conversion result" << std::endl;
        close(file);
        exit(1);
    }

    // Swap byte order
    int16_t result = (buf[0] << 8) | buf[1];
    return result;
}

int getRpm() {
    const char* device = "/dev/i2c-1";  // Use the correct I2C bus (usually /dev/i2c-1 on Raspberry Pi)
    int file = open_i2c(device, ADS1115_ADDRESS);
    if (file < 0) return 1;

    int channel = 1;  // Set the channel to read (you can change this)
    
   // while (true) {
   
    configure_ads1115(file, channel);
  
    // Wait for the conversion to complete (typical delay 8 ms)
    usleep(10000);
    // Read the ADC value
    int value = read_ads1115(file);
    // Print the ADC value to the terminal
    std::cout << "ADC Value for channel " << channel << ": " << value << std::endl;
    // Optional delay before starting the next conversion (e.g., 1 second)
    sleep(1);
//}

    close(file);
    return value;
}
