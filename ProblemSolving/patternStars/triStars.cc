#include <iostream>

int main() {
    int n = 6; // Number of rows

    for (int i = 1; i <= n; ++i) {
        // Print leading stars
        for (int j = 1; j <= i; ++j) {
            std::cout << "*";
        }

        // Print spaces
        for (int j = 1; j <= (n - i) * 2; ++j) {
            std::cout << " ";
        }

        // Print trailing stars
        for (int j = 1; j <= i; ++j) {
            std::cout << "*";
        }

        // Move to the next line
        std::cout << std::endl;
    }

    return 0;
}
