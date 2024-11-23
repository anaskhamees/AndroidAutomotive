#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <algorithm>
#include <iomanip>  // For std::setw and std::setprecision

int main() {
    /* Input and Output files*/
    std::string inputFileName = "input.txt";
    std::string outputFileName = "output.txt";

    /* open the input file for reading */
    std::ifstream inputFile(inputFileName);
    if (!inputFile) {
        std::cerr << "Failed to open " << inputFileName << std::endl;
        return 1;
    }

    /* read data from input file */
    std::vector<int> numbers;
    int number;
    while (inputFile >> number) {
        numbers.push_back(number);
    }
    /* close the input file */
    inputFile.close();

    /* process the data */
    int sum = 0;
    for (int &num : numbers) {
        sum += num;
    }

    /* get the max element */
    int max = *std::max_element(numbers.begin(), numbers.end());

    /* get the min element */
    int min = *std::min_element(numbers.begin(), numbers.end());

    /* get the avg */
    double avg = static_cast<double>(sum) / numbers.size();

    /* open the output file for writing */
    std::ofstream outputFile(outputFileName);
    if (!outputFile) {
        std::cerr << "Failed to open " << outputFileName << std::endl;
        return 1;
    }

    /* write the processed data in a tabulated format */
    outputFile << "+-----------+-----------+-----------+-----------+" << std::endl;
    outputFile << "|    Sum    |    Avg    |    Min    |    Max    |" << std::endl;
    outputFile << "+-----------+-----------+-----------+-----------+" << std::endl;
    outputFile << "| " << std::setw(6) << sum <<std::setw(6)<< " | "
               << std::setw(6) << std::fixed << std::setprecision(2) << avg <<std::setw(6)<<" | "
               << std::setw(6) << min <<std::setw(6)<< " | "
               << std::setw(6) << max <<std::setw(5)<< " |" << std::endl;
    outputFile << "+-----------+-----------+-----------+-----------+" << std::endl;

    /* close the output file */
    outputFile.close();
    std::cout << "Data processing complete...the Results written to " << outputFileName << std::endl;

    return 0;
}

