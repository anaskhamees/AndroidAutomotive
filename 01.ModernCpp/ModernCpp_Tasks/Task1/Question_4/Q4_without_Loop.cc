#include <iostream>
#include <cmath>

int main() {
    double Currpopulation = 162100;
    double population     = 1000000;
    const double growthRate = 0.065;
    int years = 0;

	std::cout << " The formula for future population:\n";
    std::cout << " P = P0 × (1 + r)^t\n";
    std::cout << " Where:\n";
    std::cout << "# P  is the future population\n";
    std::cout << "# P0 is the initial population\n";
    std::cout << "# r  is the annual growth rate\n";
    std::cout << "# y  is the number of years\n\n";
    
    std::cout << " Rearranging the formula to solve for t:\n";
    std::cout << " # y = log(P / P0) / log(1 + r)\n\n";
    years=static_cast<int>(std::ceil(std::log(population/Currpopulation)/std::log(1+growthRate)));
        std::cout << "It will take " << years << " years for the population to surpass one million.\n";

	
    return 0;
}

