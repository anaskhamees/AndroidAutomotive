#pragma once
#include<string>

class GPIO_HAL
{
	public :
		bool exportGPIO(int pin);
		bool setGPIODirection(int pin,const std::string &pinDirection);
		bool setGPIOValue(int pin, bool pinValue);
		bool getGPIOValue(int pin, bool *pinValue);


};
