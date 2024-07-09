#include <iostream>

class Pair {
private:
    std::string first;
    std::string second;

public:
    std::string get_first() { return first; }
    std::string get_second() { return second; }

    void set_first(std::string x) { first = x; }
    void set_second(std::string y) { second = y; }

    void set_pairs(std::string x, std::string y) { first = x; second = y; }

    void swap() {
        std::string temp = first;
        first = second;
        second = temp;
    }

    void print_pairs() {
        std::cout << "First: " << first << std::endl;
        std::cout << "Second: " << second << std::endl;
    }
};

int main() {
    Pair p;
    p.set_first("anas");
    p.set_second("belal");
    p.print_pairs(); // Corrected the function call
    p.set_pairs("ahmed","mohamed" );
    p.print_pairs(); // Corrected the function call
    p.swap();
    p.print_pairs(); // Corrected the function call

    return 0;
}

