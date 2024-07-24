#include <iostream>

void decimalToBinary(int decimalNumber) {
    int binaryArray[32];
    int index = 0;
    
    // Convert decimal number to binary
    while (decimalNumber > 0) {
        binaryArray[index] = decimalNumber % 2;
        index++;
        decimalNumber /= 2;
    }
    
    // Display the binary number
    std::cout << "############################################################\n";
    std::cout << "The binary number is: ";
    for (int i = index - 1; i >= 0; i--) {
        std::cout << binaryArray[i];
    }
    std::cout << "\n";
    std::cout << "############################################################\n";
}

void binaryToDecimal(int binaryNumber) {
    int tempBinary = binaryNumber;
    int decimalNumber = 0;
    int base = 1;
    
    // Convert binary number to decimal
    while (tempBinary > 0) {
        int lastDigit = tempBinary % 10;
        decimalNumber += lastDigit * base;
        base *= 2;
        tempBinary /= 10;
    }
    
    std::cout << "############################################################\n";
    std::cout << "The decimal number is: " << decimalNumber << "\n";
    std::cout << "############################################################\n";
}

int main() {
    int decimalNumber = 0;
    int binaryNumber = 0;

    std::cout << "Enter a decimal number: ";
    std::cin >> decimalNumber;
    decimalToBinary(decimalNumber);

    std::cout << "\n############################################################\n";

    std::cout << "Enter a binary number: ";
    std::cin >> binaryNumber;
    binaryToDecimal(binaryNumber);

    return 0;
}

