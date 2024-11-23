#include <iostream>
#include <cmath>

class Calculator {
private:
    double add(double a, double b) {
        return a + b;
    }
    
    double subtract(double a, double b) {
        return a - b;
    }
    
    double multiply(double a, double b) {
        return a * b;
    }
    
    double divide(double a, double b) {
        if (b == 0) {
            std::cout << "Error! Division by zero." << std::endl;
            return std::nan("");
        }
        return a / b;
    }
    
    double power(double a, double b) {
        return std::pow(a, b);
    }
    
    double squareRoot(double a) {
        if (a < 0) {
            std::cout << "Error! Square root of a negative number." << std::endl;
            return std::nan("");
        }
        return std::sqrt(a);
    }

public:
    void calculate() {
        double num1, num2;
        char op;
        
        std::cout << "Enter first number: ";
        std::cin >> num1;
        std::cout << "Enter operator (+, -, *, /, ^, S for sqrt): ";
        std::cin >> op;

        if (op == 'S') {
            std::cout << "Square root of " << num1 << " is " << squareRoot(num1) << std::endl;
            return;
        }

        std::cout << "Enter second number: ";
        std::cin >> num2;
        
        switch (op) {
            case '+':
                std::cout << "Result: " << add(num1, num2) << std::endl;
                break;
            case '-':
                std::cout << "Result: " << subtract(num1, num2) << std::endl;
                break;
            case '*':
                std::cout << "Result: " << multiply(num1, num2) << std::endl;
                break;
            case '/':
                std::cout << "Result: " << divide(num1, num2) << std::endl;
                break;
            case '^':
                std::cout << "Result: " << power(num1, num2) << std::endl;
                break;
            default:
                std::cout << "Invalid operator!" << std::endl;
                break;
        }
    }
};

int main() {
    Calculator calc;
    calc.calculate();
    return 0;
}

