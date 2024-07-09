# Modern C++

- Struct
- Enum
- Function Overloading
- Templates
- Strings
- Array
- Name Mangling



















## 1. Struct in C++

- Can create Constructor and Destructor.
- can declare inside it methods/functions.
- can use access specifier (private-public-protected).
- by default any variable inside the struct is **public**  (inverse of class)

**In modern C++, structs have evolved to become much more powerful and flexible compared to their earlier counterparts in C and C++. They are essentially the same as classes in C++ with a few differences in default access specifiers and intended use. Here's a detailed overview of structs in modern C++:**

### Basics of Structs

In C++, a struct is a user-defined data type that allows the grouping of variables of different types under a single name. The primary difference between `struct` and `class` in C++ is the default access specifier:

- In a `struct`, members are public by default.
- In a `class`, members are private by default.

#### Example:

```c++
struct Person {
    std::string name;
    int age;

    void display() const {
        std::cout << "Name: " << name << ", Age: " << age << std::endl;
    }
};

int main() {
    Person person = {"John Doe", 30};
    person.display();
    return 0;
}
```

### Modern C++ Features with Structs

#### 1. **Constructors and Member Initialization**

Structs can have constructors, including default, parameterized, copy, and move constructors. Member initialization using initializer lists is also possible.

```c++
struct Person {
    std::string name;
    int age;

    // Default constructor
    Person() : name(""), age(0) {}

    // Parameterized constructor
    Person(const std::string& name, int age) : name(name), age(age) {}

    // Copy constructor
    Person(const Person& other) = default;

    // Move constructor
    Person(Person&& other) noexcept = default;
};

int main() {
    Person person1; // Default constructor
    Person person2("Jane Doe", 25); // Parameterized constructor
    Person person3(person2); // Copy constructor
    Person person4(std::move(person2)); // Move constructor

    return 0;
}
```

#### 2. **Member Functions and Methods**

Structs can have member functions, including const methods, static methods, and overloaded operators.

```c++
struct Point {
    int x;
    int y;

    void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    void display() const {
        std::cout << "(" << x << ", " << y << ")" << std::endl;
    }

    // Static method
    static Point origin() {
        return Point{0, 0};
    }

    // Operator overloading
    Point operator+(const Point& other) const {
        return Point{x + other.x, y + other.y};
    }
};

int main() {
    Point p1 = {2, 3};
    Point p2 = {4, 5};
    Point p3 = p1 + p2; // Uses operator overloading

    p3.display(); // Output: (6, 8)

    Point origin = Point::origin();
    origin.display(); // Output: (0, 0)

    return 0;
}
```

#### 3. **Access Specifiers and Inheritance**

Structs can use public, protected, and private access specifiers, and can also participate in inheritance.

```c++
struct Base {
protected:
    int id;
public:
    Base(int id) : id(id) {}

    virtual void show() const {
        std::cout << "Base id: " << id << std::endl;
    }
};

struct Derived : public Base {
    std::string name;

    Derived(int id, const std::string& name) : Base(id), name(name) {}

    void show() const override {
        std::cout << "Derived id: " << id << ", Name: " << name << std::endl;
    }
};

int main() {
    Derived d(1, "Derived Example");
    d.show(); // Output: Derived id: 1, Name: Derived Example

    return 0;
}
```

#### 4. **Move Semantics**

Modern C++ introduces move semantics which can be used with structs to optimize performance.

```c++
struct Buffer {
    int* data;
    size_t size;

    // Constructor
    Buffer(size_t size) : data(new int[size]), size(size) {}

    // Destructor
    ~Buffer() { delete[] data; }

    // Move constructor
    Buffer(Buffer&& other) noexcept : data(other.data), size(other.size) {
        other.data = nullptr;
        other.size = 0;
    }

    // Move assignment operator
    Buffer& operator=(Buffer&& other) noexcept {
        if (this != &other) {
            delete[] data;
            data = other.data;
            size = other.size;
            other.data = nullptr;
            other.size = 0;
        }
        return *this;
    }
};

int main() {
    Buffer buffer1(100);
    Buffer buffer2 = std::move(buffer1); // Move constructor

    return 0;
}
```

### Summary

Structs in modern C++ offer a powerful way to organize and manage data, combining the simplicity of traditional C structs with the advanced features of C++ classes. They support:

- Constructors and destructors
- Member functions and methods
- Access specifiers and inheritance
- Move semantics for performance optimization
- Operator overloading and static methods





## 2. Enum in C++

In C++, enumerations (enums) are user-defined types that consist of a set of named integral constants. C++ provides two types of enums: unscoped enums and scoped enums (enum class). Each has its own use cases and characteristics. Let's dive into each type and understand the differences with examples.

### Unscoped Enum

An unscoped enum is the traditional C-style enum, where the enumerator names are placed in the global scope (or the scope in which the enum is declared).

#### Characteristics:

- The enumerator names are accessible without qualification.
- The underlying type is usually `int`, but this can be specified.
- Implicit conversions to and from the underlying integral type are allowed.

#### Example:

```c++
#include <iostream>

enum Color {
    Red,
    Green,
    Blue
};

int main() {
    Color color = Green;
    std::cout << "Color value: " << color << std::endl; // Output: Color value: 1

    // Implicit conversion to int
    int colorValue = color;
    std::cout << "Color value as int: " << colorValue << std::endl; // Output: Color value as int: 1

    // Assigning integer to enum (not type-safe)
    color = static_cast<Color>(2);
    std::cout << "Updated color value: " << color << std::endl; // Output: Updated color value: 2

    return 0;
}
```

### Scoped Enum (enum class)

A scoped enum (enum class) was introduced in C++11 to address some of the limitations of unscoped enums. Scoped enums provide better type safety and scoping.

#### Characteristics:

- The enumerator names are scoped within the enum class, and must be accessed with the scope operator.
- The underlying type must be explicitly specified or defaults to `int`.
- Implicit conversions to and from the underlying integral type are not allowed, enhancing type safety.

#### Example:

```c++
#include <iostream>

enum class Color {
    Red,
    Green,
    Blue
};

int main() {
    Color color = Color::Green;
    std::cout << "Color value: " << static_cast<int>(color) << std::endl; // Output: Color value: 1

    // Implicit conversion to int is not allowed
    // int colorValue = color; // Error: cannot convert 'Color' to 'int'

    // Explicit conversion to int is required
    int colorValue = static_cast<int>(color);
    std::cout << "Color value as int: " << colorValue << std::endl; // Output: Color value as int: 1

    // Assigning integer to enum (requires explicit cast)
    color = static_cast<Color>(2);
    std::cout << "Updated color value: " << static_cast<int>(color) << std::endl; // Output: Updated color value: 2

    return 0;
}
```

### Differences Between Unscoped and Scoped Enums

| Feature                    | Unscoped Enum                 | Scoped Enum (enum class)             |
| -------------------------- | ----------------------------- | ------------------------------------ |
| Scope of enumerator names  | Global (or enclosing scope)   | Within the enum class                |
| Implicit conversion to int | Allowed                       | Not allowed                          |
| Underlying type            | Usually int, can be specified | Must be specified or defaults to int |
| Type safety                | Less type-safe                | More type-safe                       |
| Usage syntax               | `Color color = Green;`        | `Color color = Color::Green;`        |

### Specifying Underlying Types

Both unscoped and scoped enums can have explicitly specified underlying types.

#### Example of Unscoped Enum with Specified Underlying Type:

```c++
enum Color : unsigned char {
    Red,
    Green,
    Blue
};
```

#### Example of Scoped Enum with Specified Underlying Type:

```c++
enum class Color : unsigned char {
    Red,
    Green,
    Blue
};
```



## 3. Containers



In C++, arrays are a fundamental type of container that allow you to store a fixed-size sequence of elements of the same type. There are three main types of arrays in C++:

1. **C-style arrays** (also known as raw arrays)
2. **`std::array`** (introduced in C++11)
3. **`std::vector`** (a dynamic array provided by the Standard Template Library)

### 1. C-style Arrays

C-style arrays are the simplest form of arrays in C++. Their size must be specified at compile time and cannot be changed. They do not provide many of the safety features and functionalities of modern C++ containers.

#### Example:

```c++
#include <iostream>

int main() {
    int arr[5] = {1, 2, 3, 4, 5};  // Declare an array of 5 integers

    // Access elements using the index
    for (int i = 0; i < 5; ++i) {
        std::cout << arr[i] << " ";
    }

    return 0;
}
```

### 2. `std::array`

`std::array` is a container that encapsulates fixed-size arrays and provides more functionalities and safety compared to C-style arrays. It is part of the Standard Library and requires the `<array>` header.

#### Example:

```c++
#include <iostream>
#include <array>

int main() {
    std::array<int, 5> arr = {1, 2, 3, 4, 5};  // Declare an array of 5 integers

    // Access elements using the index
    for (int i = 0; i < arr.size(); ++i) {
        std::cout << arr[i] << " ";
    }

    return 0;
}
```

### 3. `std::vector`

`std::vector` is a dynamic array provided by the Standard Template Library (STL). It can grow and shrink in size dynamically, providing more flexibility. It is part of the Standard Library and requires the `<vector>` header.

#### Example:

```c++
#include <iostream>
#include <vector>

int main() {
    std::vector<int> vec = {1, 2, 3, 4, 5};  // Declare a vector of integers

    // Add an element to the end
    vec.push_back(6);

    // Access elements using the index
    for (int i = 0; i < vec.size(); ++i) {
        std::cout << vec[i] << " ";
    }

    return 0;
}
```

### Array vs. Vector vs. `std::array`

- **C-style Array**:
  - Fixed size.
  - No size or boundary checks.
  - Simple and efficient for small, fixed-size arrays.
- **`std::array`**:
  - Fixed size, like C-style arrays.
  - Provides member functions and iterators.
  - Safer and more feature-rich than C-style arrays.
  - Part of the Standard Library.
- **`std::vector`**:
  - Dynamic size (can grow and shrink).
  - Provides member functions and iterators.
  - More overhead due to dynamic memory management.
  - Part of the Standard Library.



To convert an integer to a string in C++, you can use `std::to_string()` from the `<string>` header. Here's an example of how you can modify your `Pair` class to include a method that converts the integers to strings:

```c++
#include <iostream>
#include <string> // for std::to_string

class Pair {
private:
    int first;
    int second;

public:
    int get_first() { return first; }
    int get_second() { return second; }

    void set_first(int x) { first = x; }
    void set_second(int y) { second = y; }

    void set_pairs(int x, int y) { first = x; second = y; }

    void swap() {
        int temp = first;
        first = second;
        second = temp;
    }

    void print_pairs() {
        std::cout << "First: " << first << std::endl;
        std::cout << "Second: " << second << std::endl;
    }

    std::string to_string() {
        return "(" + std::to_string(first) + ", " + std::to_string(second) + ")";
    }
};

int main() {
    Pair p;
    p.set_first(5);
    p.set_second(9);
    p.print_pairs();
    std::cout << "Pair as string: " << p.to_string() << std::endl;

    p.set_pairs(3, 2);
    p.print_pairs();
    std::cout << "Pair as string: " << p.to_string() << std::endl;

    p.swap();
    p.print_pairs();
    std::cout << "Pair as string: " << p.to_string() << std::endl;

    return 0;
}
```

In this modified code:

- The `to_string()` method is added to the `Pair` class, which converts the `first` and `second` integers to strings using `std::to_string()`.
- Inside `main()`, after printing the pairs using `print_pairs()`, `p.to_string()` is called to convert and print the pair as a string.

This will output the pairs both as integers and as strings:

```
vbnetCopy codeFirst: 5
Second: 9
Pair as string: (5, 9)
First: 3
Second: 2
Pair as string: (3, 2)
First: 2
Second: 3
Pair as string: (2, 3)
```

This demonstrates how you can convert integers to strings within your `Pair` class in C++.



## 3. String in C++

In modern C++, strings are represented by the `std::string` class from the `<string>` header in the standard library (`<string>`). Here are some key features and advantages of using `std::string` in modern C++:

1. **Dynamic Size**: `std::string` manages the memory allocation for the string dynamically, so you don't need to worry about buffer sizes or manual memory management.
2. **Efficiency**: `std::string` provides efficient memory management and operations, such as concatenation, appending, and substring extraction. It typically uses a copy-on-write (COW) optimization strategy for efficient copying.
3. **Unicode Support**: `std::string` supports Unicode characters through UTF-8 encoding, making it suitable for handling internationalization and localization requirements.
4. **Rich Interface**: `std::string` provides a rich set of member functions and overloaded operators for various string manipulations, comparisons, and conversions. Some common operations include:
   - Accessing individual characters (`operator[]`, `at()`)
   - Finding substrings (`find()`, `rfind()`, `find_first_of()`, etc.)
   - Modifying the string (`insert()`, `erase()`, `replace()`, `append()`, `substr()`, etc.)
   - Comparing strings (`operator==`, `compare()`, `starts_with()`, `ends_with()`, etc.)
   - Converting to and from C-style strings (`c_str()`, `data()`)
5. **Interoperability**: `std::string` can be easily converted to C-style strings (`const char*`) using `c_str()` or `data()` methods, allowing compatibility with functions that expect traditional null-terminated strings.
6. **Standard Library Integration**: Many standard library functions and classes support `std::string` directly, facilitating seamless integration with other parts of the standard library.

### Example Usage:

Here's a brief example demonstrating the use of `std::string` in modern C++:

```c++
#include <iostream>
#include <string>

int main() {
    // Initializing std::string
    std::string str = "Hello, ";
    
    // Appending to std::string
    str += "world!";
    
    // Length of std::string
    std::cout << "Length: " << str.length() << std::endl; // Output: Length: 13
    
    // Accessing individual characters
    std::cout << "First character: " << str[0] << std::endl; // Output: First character: H
    
    // Finding substrings
    std::size_t pos = str.find("world");
    if (pos != std::string::npos) {
        std::cout << "Substring found at position " << pos << std::endl; // Output: Substring found at position 7
    }
    
    // Iterating over characters
    for (char ch : str) {
        std::cout << ch << " ";
    }
    std::cout << std::endl; // Output: H e l l o ,   w o r l d !
    
    return 0;
}
```

### Tips for Using `std::string`:

- **Avoid Premature Optimization**: `std::string` handles memory management efficiently, so focus on clear and readable code rather than optimizing for string operations unless performance profiling indicates a bottleneck.
- **Use Range-based for Loop**: When iterating over characters or substrings, prefer the range-based for loop (`for (char ch : str)`) for simplicity and safety.
- **Take Advantage of Member Functions**: Explore the various member functions (`append()`, `substr()`, etc.) provided by `std::string` to simplify common string manipulations.
- **Consider `std::string_view`**: For non-owning references to strings (especially substrings), consider using `std::string_view` to avoid unnecessary copying and improve performance in certain scenarios.



## 4. Function Overloading

Function overloading in modern C++ allows you to define multiple functions with the same name but different parameter lists. This feature enables you to create functions that perform similar tasks but can accept different types of parameters or different numbers of parameters. Here are the key aspects and benefits of function overloading:

### Key Aspects of Function Overloading:

1. **Parameter List Variation**: Functions that are overloaded must differ either in the number of parameters or in the types of parameters. Return type alone does not differentiate overloaded functions.

2. **Same Name**: All overloaded functions must have the same name. The compiler distinguishes between them based on the parameters provided during the function call.

3. **Compile-Time Resolution**: The decision on which overloaded function to call is made by the compiler at compile-time, based on the arguments passed in the function call.

4. **Flexibility**: Function overloading provides flexibility and improves code readability by allowing you to use the same function name for logically related tasks.

5. **Example**:

   ```c++
   #include <iostream>
   
   // Function to calculate square of an integer
   int square(int x) {
       return x * x;
   }
   
   // Function to calculate square of a double
   double square(double x) {
       return x * x;
   }
   
   int main() {
       int intResult = square(5);       // Calls int square(int)
       double doubleResult = square(5.5); // Calls double square(double)
   
       std::cout << "Square of 5 (int): " << intResult << std::endl;
       std::cout << "Square of 5.5 (double): " << doubleResult << std::endl;
   
       return 0;
   }
   ```

   In this example:

   - `square(int x)` computes the square of an integer.
   - `square(double x)` computes the square of a double.
   - Both functions are overloaded with the name `square`, but they accept different parameter types (`int` and `double`).

### Benefits of Function Overloading:

- **Code Reusability**: You can reuse function names for different data types or different numbers of arguments, reducing redundancy in function naming.
- **Readability**: Using the same function name for related operations improves code readability and maintainability, especially in libraries and large projects.
- **Polymorphism**: Function overloading is a form of compile-time polymorphism (static polymorphism), where the appropriate function to call is determined by the compiler based on the parameters provided.

### Considerations:

- **Ambiguity**: Care must be taken to avoid ambiguous function overloads. If the compiler cannot determine which function to call based on the provided arguments, it will result in a compilation error.

- **Implicit Conversions**: Overloading can sometimes lead to unexpected behavior when implicit conversions between types are involved. It's important to consider potential conversions and their implications.

  

## 5. Templates

**Function Templates**:

- Function templates allow you to define a function that can operate with generic types. You define the function using `template<typename T>` or `template<class T>` syntax, where `T` is a placeholder for the type parameter.

- Example of a function template:

  ```c++
  #include <iostream>
  
  // Function template to swap two values of any type
  template<typename T>
  void swap_values(T& a, T& b) {
      T temp = a;
      a = b;
      b = temp;
  }
  
  int main() {
      int x = 5, y = 10;
      swap_values(x, y);  // Calls swap_values<int>(int&, int&)
      std::cout << "x: " << x << ", y: " << y << std::endl; // Output: x: 10, y: 5
  
      double a = 3.5, b = 8.2;
      swap_values(a, b);  // Calls swap_values<double>(double&, double&)
      std::cout << "a: " << a << ", b: " << b << std::endl; // Output: a: 8.2, b: 3.5
  
      return 0;
  }
  ```



### Example 1: Generic Swap Function

```c++
#include <iostream>

// Generic swap function template
template<typename T>
void swap_values(T& a, T& b) {
    T temp = a;
    a = b;
    b = temp;
}

int main() {
    int x = 5, y = 10;
    double a = 3.5, b = 8.2;

    // Implicit instantiation with integers
    std::cout << "Before swapping: x = " << x << ", y = " << y << std::endl;
    swap_values(x, y);
    std::cout << "After swapping: x = " << x << ", y = " << y << std::endl;

    // Explicit instantiation with doubles
    std::cout << "Before swapping: a = " << a << ", b = " << b << std::endl;
    swap_values<double>(a, b);
    std::cout << "After swapping: a = " << a << ", b = " << b << std::endl;

    return 0;
}
```

- Explanation

  :

  - **Implicit Instantiation**: `swap_values(x, y);` implicitly instantiates the `swap_values<int>(int&, int&)` template specialization for `int` types.
  - **Explicit Instantiation**: `swap_values<double>(a, b);` explicitly specifies `double` as the template argument, invoking the `swap_values<double>(double&, double&)` template specialization.

### Example 2: Generic Maximum Function

```c++
#include <iostream>

// Generic function template to find maximum of two values
template<typename T>
T max_value(const T& a, const T& b) {
    return (a > b) ? a : b;
}

int main() {
    int x = 5, y = 10;
    double a = 3.5, b = 8.2;

    // Implicit instantiation with integers
    std::cout << "Max of " << x << " and " << y << " is: " << max_value(x, y) << std::endl;

    // Explicit instantiation with doubles
    std::cout << "Max of " << a << " and " << b << " is: " << max_value<double>(a, b) << std::endl;

    return 0;
}
```

- Explanation

  :

  - **Implicit Instantiation**: `max_value(x, y);` implicitly instantiates the `max_value<int>(const int&, const int&)` template specialization for `int` types.
  - **Explicit Instantiation**: `max_value<double>(a, b);` explicitly specifies `double` as the template argument, invoking the `max_value<double>(const double&, const double&)` template specialization.

### Example 3: Generic Print Function

```c++
#include <iostream>
#include <vector>
#include <string>

// Generic function template to print any iterable container
template<typename Container>
void print_container(const Container& container) {
    for (const auto& elem : container) {
        std::cout << elem << " ";
    }
    std::cout << std::endl;
}

int main() {
    std::vector<int> numbers = {1, 2, 3, 4, 5};
    std::vector<std::string> names = {"Alice", "Bob", "Charlie"};

    // Implicit instantiation with vector<int>
    std::cout << "Numbers: ";
    print_container(numbers);

    // Explicit instantiation with vector<string>
    std::cout << "Names: ";
    print_container<std::vector<std::string>>(names);

    return 0;
}
```

- Explanation

  :

  - **Implicit Instantiation**: `print_container(numbers);` implicitly instantiates the `print_container<std::vector<int>>(const std::vector<int>&)` template specialization for `std::vector<int>` types.
  - **Explicit Instantiation**: `print_container<std::vector<std::string>>(names);` explicitly specifies `std::vector<std::string>` as the template argument, invoking the `print_container<std::vector<std::string>>(const std::vector<std::string>&)` template specialization.

### Example 4: Generic Arithmetic Function

```c++
#include <iostream>

// Generic function template for arithmetic operations
template<typename T>
T arithmetic_operation(const T& a, const T& b) {
    return a + b; // Example of arithmetic operation; can be +, -, *, /
}

int main() {
    int x = 5, y = 3;
    double a = 3.5, b = 2.1;

    // Implicit instantiation with integers
    std::cout << "Sum of " << x << " and " << y << " is: " << arithmetic_operation(x, y) << std::endl;

    // Explicit instantiation with doubles
    std::cout << "Sum of " << a << " and " << b << " is: " << arithmetic_operation<double>(a, b) << std::endl;

    return 0;
}
```

- Explanation

  :

  - **Implicit Instantiation**: `arithmetic_operation(x, y);` implicitly instantiates the `arithmetic_operation<int>(const int&, const int&)` template specialization for `int` types.
  - **Explicit Instantiation**: `arithmetic_operation<double>(a, b);` explicitly specifies `double` as the template argument, invoking the `arithmetic_operation<double>(const double&, const double&)` template specialization.



## 6. Name Mangling

### How Name Mangling Works in Function Overloading

In C++, function overloading allows you to define multiple functions with the same name but different parameter types. To distinguish these functions during compilation and linking, the compiler mangles their names by incorporating type information into the function name.

### Example 1: Function Overloading

```c++
#include <iostream>

// Function to calculate the area of a square (int)
int area(int side) {
    return side * side;
}

// Function to calculate the area of a rectangle (double)
double area(double length, double width) {
    return length * width;
}

int main() {
    int side = 5;
    double length = 4.5, width = 3.2;

    std::cout << "Area of square with side " << side << ": " << area(side) << std::endl;
    std::cout << "Area of rectangle with length " << length << " and width " << width << ": " << area(length, width) << std::endl;

    return 0;
}
```

### Understanding Name Mangling in the Example

- **Function `area()`**: There are two `area()` functions defined in this example:
  - `int area(int side)` calculates the area of a square.
  - `double area(double length, double width)` calculates the area of a rectangle.
- **Name Mangling**: When compiled, the compiler generates unique mangled names for each `area()` function based on their parameter types (`int` and `double`).

### Viewing Mangled Names

To see the mangled names, you can use the `nm` command on Unix/Linux systems:

```bash
$ g++ -o example example.cpp
$ nm -C example
```

This will display the mangled symbols in the `example` executable, showing how each function's name has been encoded by the compiler.

### Benefits of Name Mangling

- **Function Overloading**: Allows you to define multiple functions with the same name but different parameter types, enhancing code clarity and reusability.
- **Namespace and Scope**: Ensures that functions defined in different namespaces or scopes don't clash by encoding namespace or class information into the function names.
