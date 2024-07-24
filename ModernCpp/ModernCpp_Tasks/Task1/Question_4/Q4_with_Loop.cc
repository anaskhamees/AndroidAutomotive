#include <iostream>
#include <cmath>

int main() {
    double population = 162100;
    const double growthRate = 0.065;
    int years = 0;

    std::cout << "Year\tPopulation\n";
    std::cout << "----------------------\n";

    while (population <= 1000000) {
        years++;
        population += population * growthRate;
        std::cout << years << "\t" << static_cast<int>(population) << "\n";
    }

    std::cout << "----------------------\n";
    std::cout << "It will take " << years << " years for the population to surpass one million.\n";

    return 0;
}

