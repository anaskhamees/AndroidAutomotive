#include <iostream>
#include <vector>

// Function to print vector using iterator
void printUsingIterator(std::vector<int>& vec) {
    std::cout << "Using iterator:" << std::endl;
    for (std::vector<int>::iterator it = vec.begin(); it != vec.end(); ++it) {
        std::cout << *it << " ";
    }
    std::cout << std::endl;
}

// Function to print vector using const_iterator
void printUsingConstIterator(const std::vector<int>& vec) {
    std::cout << "Using const_iterator:" << std::endl;
    for (std::vector<int>::const_iterator it = vec.cbegin(); it != vec.cend(); ++it) {
        std::cout << *it << " ";
    }
    std::cout << std::endl;
}

// Function to print vector using reverse_iterator
void printUsingReverseIterator(std::vector<int>& vec) {
    std::cout << "Using reverse_iterator:" << std::endl;
    for (std::vector<int>::reverse_iterator it = vec.rbegin(); it != vec.rend(); ++it) {
        std::cout << *it << " ";
    }
    std::cout << std::endl;
}

// Function to print vector using const_reverse_iterator
void printUsingConstReverseIterator(const std::vector<int>& vec) {
    std::cout << "Using const_reverse_iterator:" << std::endl;
    for (std::vector<int>::const_reverse_iterator it = vec.crbegin(); it != vec.crend(); ++it) {
        std::cout << *it << " ";
    }
    std::cout << std::endl;
}

int main() {
    std::vector<int> vec = {1, 2, 3, 4, 5};

    printUsingIterator(vec); // Using begin() and end()
    printUsingConstIterator(vec); // Using cbegin() and cend()
    printUsingReverseIterator(vec); // Using rbegin() and rend()
    printUsingConstReverseIterator(vec); // Using crbegin() and crend()

    return 0;
}
