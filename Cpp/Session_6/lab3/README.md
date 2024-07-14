## Transpose a 2D Matrix using 2D Vector



```c++
#include <iostream>
#include <vector>

// Function to transpose a 2D vector (matrix)
std::vector<std::vector<int>> transpose2DMatrix(std::vector<std::vector<int>> v, int row, int col) {
    // Initialize the transposed vector with dimensions col x row
    std::vector<std::vector<int>> vec(col, std::vector<int>(row));

    // Loop through each element of the original matrix
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            // Assign the transposed element
            vec[j][i] = v[i][j];
        }
    }

    // Return the transposed matrix
    return vec;
}

// Function to print a 2D vector (matrix)
void print(const std::vector<std::vector<int>>& v, int row, int col) {
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            // Print each element followed by a space
            std::cout << v[i][j] << " ";
        }
        // Move to the next line after each row
        std::cout << std::endl;
    }
}

int main() {
    int row = 0; // Variable to store the number of rows
    int col = 0; // Variable to store the number of columns

    // Prompt user to enter the number of rows
    std::cout << "Enter the Rows Number    : ";
    std::cin >> row;
    std::cout << "\n";

    // Prompt user to enter the number of columns
    std::cout << "Enter the Columns Number : ";
    std::cin >> col;
    std::cout << "\n";

    // Initialize a 2D vector with given dimensions
    std::vector<std::vector<int>> vec(row, std::vector<int>(col));

    // Prompt user to enter the elements of the 2D vector
    std::cout << "Enter the 2D Vector Elements \n\n";
    for (int rows = 0; rows < row; rows++) {
        for (int cols = 0; cols < col; cols++) {
            std::cout << "Element [" << rows << "][" << cols << "] : ";
            std::cin >> vec[rows][cols];
            std::cout << "\n";
        }
    }

    // Print the original 2D vector
    std::cout << "###########################################################\n";
    std::cout << "The Normal 2D Vector \n";
    std::cout << "###########################################################\n";
    print(vec, row, col);
    std::cout << "###########################################################\n";

    // Print the transposed 2D vector
    std::cout << "The Transposed 2D Vector:\n";
    std::cout << "###########################################################\n";
    print(transpose2DMatrix(vec, row, col), col, row);
    std::cout << "###########################################################\n";

    return 0;
}
```

### Detailed Explanation

1. **Include Headers**:

   ```c++
   #include <iostream>
   #include <vector>
   ```

   - `#include <iostream>`: This includes the standard input-output stream library for console input and output.
   - `#include <vector>`: This includes the vector library for using the `std::vector` container.

2. **Transpose Function**:

   ```c++
   std::vector<std::vector<int>> transpose2DMatrix(std::vector<std::vector<int>> v, int row, int col) {
       std::vector<std::vector<int>> vec(col, std::vector<int>(row));
       for (int i = 0; i < row; i++) {
           for (int j = 0; j < col; j++) {
               vec[j][i] = v[i][j];
           }
       }
       return vec;
   }
   ```

   - `std::vector<std::vector<int>> transpose2DMatrix(std::vector<std::vector<int>> v, int row, int col)`: This function takes a 2D vector `v` and its dimensions (`row` and `col`) as input and returns its transpose.
   - `std::vector<std::vector<int>> vec(col, std::vector<int>(row))`: Initializes the transposed vector with `col` rows and `row` columns.
   - The nested `for` loops iterate over each element of the original matrix and assign it to the transposed position in `vec`.

3. **Print Function**:

   ```c++
   void print(const std::vector<std::vector<int>>& v, int row, int col) {
       for (int i = 0; i < row; i++) {
           for (int j = 0; j < col; j++) {
               std::cout << v[i][j] << " ";
           }
           std::cout << std::endl;
       }
   }
   ```

   - `void print(const std::vector<std::vector<int>>& v, int row, int col)`: This function prints a 2D vector `v` with dimensions `row` and `col`.
   - The nested `for` loops iterate over each element and print it followed by a space. `std::cout << std::endl;` prints a newline after each row.

4. **Main Function**:

   ```c++
   int main() {
       int row = 0;
       int col = 0;
       std::cout << "Enter the Rows Number    : ";
       std::cin >> row;
       std::cout << "\n";
       std::cout << "Enter the Columns Number : ";
       std::cin >> col;
       std::cout << "\n";
       std::vector<std::vector<int>> vec(row, std::vector<int>(col));
       std::cout << "Enter the 2D Vector Elements \n\n";
       for (int rows = 0; rows < row; rows++) {
           for (int cols = 0; cols < col; cols++) {
               std::cout << "Element [" << rows << "][" << cols << "] : ";
               std::cin >> vec[rows][cols];
               std::cout << "\n";
           }
       }
       std::cout << "###########################################################\n";    
       std::cout << "The Normal 2D Vector \n";
       std::cout << "###########################################################\n";    
       print(vec, row, col);
       std::cout << "###########################################################\n";    
       std::cout << "The Transposed 2D Vector:\n";
       std::cout << "###########################################################\n";    
       print(transpose2DMatrix(vec, row, col), col, row);
       std::cout << "###########################################################\n";    
       return 0;
   }
   ```

   - `int main()`: The main function where the program execution starts.
   - `int row = 0; int col = 0;`: Declare and initialize variables for the number of rows and columns.
   - `std::cout << "Enter the Rows Number : "; std::cin >> row;`: Prompt the user to enter the number of rows and read it.
   - `std::cout << "Enter the Columns Number : "; std::cin >> col;`: Prompt the user to enter the number of columns and read it.
   - `std::vector<std::vector<int>> vec(row, std::vector<int>(col));`: Initialize a 2D vector with the specified number of rows and columns.
   - The nested `for` loops prompt the user to enter each element of the 2D vector.
   - Print the original 2D vector using the `print` function.
   - Transpose the 2D vector using the `transpose2DMatrix` function and print the transposed vector.
   - `return 0;`: Return 0 to indicate successful execution.
