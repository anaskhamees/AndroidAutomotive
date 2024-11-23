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
