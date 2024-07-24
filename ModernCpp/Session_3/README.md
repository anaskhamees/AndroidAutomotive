## Modern Cpp

### Table of Contents 

--------------------

- C++ standards

- Namespaces and type alias
- variable initialization
- For-ranged
- dynamic allocation
- linking - scope
- type inference (auto)
- structured binding 

-----------------------------------------------------------------





### 2. NameSpace

```c++
using namespace std;
```

in C++, a namespace is a declarative region that provides a scope to the identifiers (the names of types, functions, variables, etc.) inside it. Namespaces are used to organize code into logical groups and to prevent name collisions that can occur especially when your code base includes multiple libraries.

Here's a simple example to illustrate namespaces:

```c++
#include <iostream>

// Define a namespace called 'MyNamespace'
namespace MyNamespace {
    int myFunction() {
        return 5;
    }
}

// Another namespace
namespace AnotherNamespace {
    int myFunction() {
        return 10;
    }
}

int main() {
    // Accessing the functions from their respective namespaces
    std::cout << "MyNamespace function returns: " << MyNamespace::myFunction() << std::endl;
    std::cout << "AnotherNamespace function returns: " << AnotherNamespace::myFunction() << std::endl;

    return 0;
}
```

In this example:

- `MyNamespace` and `AnotherNamespace` are two different namespaces.
- Both namespaces contain a function named `myFunction`.
- Using the scope resolution operator `::`, you can specify which `myFunction` to call.

Namespaces are especially useful in larger projects to avoid naming conflicts and to group related code together. They are also commonly used in the standard library (e.g., `std` for the Standard Library).

### Benefits of Using Namespaces

1. **Avoiding Name Conflicts**: By placing your code in namespaces, you can avoid conflicts with code from other libraries or parts of a project that may use the same names.
2. **Code Organization**: Namespaces help in organizing your code into logical sections, making it easier to manage and understand.
3. **Modularity**: They support the modularization of code, which is beneficial for large projects.

### Nested Namespaces

You can also nest namespaces within each other:

```c++
namespace Outer {
    namespace Inner {
        int nestedFunction() {
            return 20;
        }
    }
}
```

You can access `nestedFunction` using `Outer::Inner::nestedFunction()`.

### Using Namespace

To avoid repeatedly typing the namespace, you can use the `using` directive:

```c++
using namespace MyNamespace;

int main() {
    std::cout << "MyNamespace function returns: " << myFunction() << std::endl;  // No need for MyNamespace::
    return 0;
}
```



-  You can use an unnamed or anonymous namespace in C++. An unnamed namespace is a way to define namespace-scope variables and functions that are only accessible within the translation unit where they are defined, providing internal linkage by default.

Here's an example of an unnamed namespace:

```c++
#include <iostream>

namespace {
    // This function and variable are only accessible within this translation unit
    int internalFunction() {
        return 42;
    }

    int internalVariable = 10;
}

int main() {
    std::cout << "Internal function returns: " << internalFunction() << std::endl;
    std::cout << "Internal variable is: " << internalVariable << std::endl;

    return 0;
}
```

In this example:

- The `internalFunction` and `internalVariable` are defined inside an unnamed namespace.
- They can only be accessed within the same translation unit (the same source file).

### Benefits of Unnamed Namespaces

1. **Encapsulation**: You can encapsulate helper functions and variables that should not be accessible from other translation units.
2. **Avoiding Global Scope Pollution**: By using unnamed namespaces, you avoid polluting the global namespace with names that might clash with other parts of your code or libraries.

### Alternative: `static`

Before unnamed namespaces, the `static` keyword was often used to achieve similar effects by limiting the linkage of functions and variables to the file scope:

```c++
static int internalFunction() {
    return 42;
}

static int internalVariable = 10;
```

While the `static` keyword still works, using unnamed namespaces is the preferred modern approach for achieving internal linkage in C++ .



### 3. Type Alias

Type aliases in C++ allow you to create new names for existing types. This can make your code more readable, easier to manage, and help with the abstraction of complex types. There are two primary ways to create type aliases in C++: using the `typedef` keyword and using the `using` keyword (introduced in C++11).

### Using `typedef`

The `typedef` keyword allows you to create a new name for an existing type. Here's an example:

```c++
#include <iostream>
#include <vector>

// Using typedef to create type aliases
typedef unsigned int UInt;
typedef std::vector<int> IntVector;

int main() {
    UInt a = 10; // UInt is now an alias for unsigned int
    IntVector vec = {1, 2, 3, 4, 5}; // IntVector is now an alias for std::vector<int>

    std::cout << "a: " << a << std::endl;
    std::cout << "vec: ";
    for (int i : vec) {
        std::cout << i << " ";
    }
    std::cout << std::endl;

    return 0;
}
```

### Using `using`

The `using` keyword provides a more modern and flexible way to create type aliases. It's generally preferred over `typedef` due to its readability and support for templates. Here's an example:

```c++
#include <iostream>
#include <vector>

// Using 'using' to create type aliases
using UInt = unsigned int;
using IntVector = std::vector<int>;

int main() {
    UInt a = 10; // UInt is now an alias for unsigned int
    IntVector vec = {1, 2, 3, 4, 5}; // IntVector is now an alias for std::vector<int>

    std::cout << "a: " << a << std::endl;
    std::cout << "vec: ";
    for (int i : vec) {
        std::cout << i << " ";
    }
    std::cout << std::endl;

    return 0;
}
```

### Type Aliases for Templates

Using `using`, you can also create type aliases for templates, which is more cumbersome with `typedef`. Here's an example:

```c++
#include <iostream>
#include <vector>
#include <string>

// Creating type aliases for template types using 'using'
template <typename T>
using Vec = std::vector<T>;

int main() {
    Vec<int> intVec = {1, 2, 3, 4, 5}; // Vec<int> is now an alias for std::vector<int>
    Vec<std::string> stringVec = {"Hello", "World"}; // Vec<std::string> is now an alias for std::vector<std::string>

    std::cout << "intVec: ";
    for (int i : intVec) {
        std::cout << i << " ";
    }
    std::cout << std::endl;

    std::cout << "stringVec: ";
    for (const std::string& str : stringVec) {
        std::cout << str << " ";
    }
    std::cout << std::endl;

    return 0;
}
```

### Summary

- **`typedef`**: Older method, works well for basic types, but less readable and flexible for templates.
- **`using`**: Modern method, introduced in C++11, preferred for its readability, flexibility, and ease of use with templates.

----------------------------------------------------------------------------------------------------------

### Namespace Aliasing

You can create an alias for a namespace using the `namespace` keyword followed by the alias name and the original namespace. Here’s an example:

```c++
#include <iostream>

namespace VeryLongNamespaceName {
    void function() {
        std::cout << "Function in VeryLongNamespaceName" << std::endl;
    }
}

// Creating an alias for the namespace
namespace VLN = VeryLongNamespaceName;

int main() {
    // Using the alias
    VLN::function(); // Calls VeryLongNamespaceName::function()
    return 0;
}
```

### Example with Versioned Namespaces

Namespace aliasing can be particularly handy when dealing with versioned namespaces. For instance:

```c++
#include <iostream>

namespace Library_v1 {
    void function() {
        std::cout << "Function in Library_v1" << std::endl;
    }
}

namespace Library_v2 {
    void function() {
        std::cout << "Function in Library_v2" << std::endl;
    }
}

// Alias for the current version of the library
namespace CurrentLibrary = Library_v2;

int main() {
    // Using the alias to refer to the latest version of the library
    CurrentLibrary::function(); // Calls Library_v2::function()
    return 0;
}
```

### Benefits of Namespace Aliasing

1. **Shortening Long Namespace Names**: Makes the code cleaner and easier to read.
2. **Managing Versioned Libraries**: Allows easy switching between different versions of a library by changing the alias.
3. **Improving Code Maintainability**: Simplifies code updates and maintenance by centralizing namespace references.

### Summary

Namespace aliasing is a powerful feature in C++ that can help manage and simplify the use of namespaces, especially in large codebases or when dealing with long and complex namespace names. It enhances code readability and maintainability by allowing you to use shorter and more meaningful names for namespaces.



### 3. Variable Initialization

- Copy init

```c++
int x=5
```



- Direct init

  ```c++
  int x(5);
  ```

  

- List or uniform init

  ```c++
  int x{7};
  int x={7};
  // prevent casting 
  int x=80.8;
  int x{80.8}; //ERROR
  ```

  

- Aggregate init

```c++
{
	.name=;
	.var=;
}
```

**1. Copy Initialization**

Copy initialization uses the assignment operator to initialize a variable. It is typically used in assignment expressions and when initializing a variable with an existing object.

```c++
int a = 10; // Copy initialization
std::string str = "Hello"; // Copy initialization
```

**2. Direct Initialization**

Direct initialization uses parentheses to initialize a variable. It is generally more efficient than copy initialization and can prevent unnecessary copying.

```c++
int a(10); // Direct initialization
std::string str("Hello"); // Direct initialization
```

**3. List or Uniform Initialization (Introduced in C++11)**

List initialization uses curly braces `{}` to initialize variables. This type of initialization is uniform and can be used for all types of variables, including built-in types, classes, arrays, and aggregates. It prevents narrowing conversions (e.g., converting a larger type to a smaller type) and is often preferred for its consistency.

```c++
int a{10}; // Uniform initialization
std::string str{"Hello"}; // Uniform initialization

int arr[3] = {1, 2, 3}; // List initialization of an array

struct Point {
    int x;
    int y;
};

Point p{1, 2}; // Uniform initialization of a struct
```

**4. Aggregate Initialization**

Aggregate initialization is used for initializing aggregates (arrays and structs) directly. This can be seen as a special case of list initialization but is more specific to aggregates.

```c++
int arr[3] = {1, 2, 3}; // Aggregate initialization of an array

struct Point {
    int x;
    int y;
};

Point p = {1, 2}; // Aggregate initialization of a struct
```

###  Example

Here’s a summary example that includes all four types of initialization:

```c++
#include <iostream>
#include <vector>
#include <string>

struct Point {
    int x;
    int y;
};

int main() {
    // Copy initialization
    int a = 10;
    std::string str = "Hello";

    // Direct initialization
    int b(20);
    std::string str2("World");

    // List or uniform initialization
    int c{30};
    std::string str3{"C++"};
    std::vector<int> vec{1, 2, 3, 4, 5}; // Also prevents narrowing conversions

    // Aggregate initialization
    int arr[3] = {1, 2, 3};
    Point p = {4, 5};

    // Output the values to verify
    std::cout << "a: " << a << "\n";
    std::cout << "str: " << str << "\n";
    std::cout << "b: " << b << "\n";
    std::cout << "str2: " << str2 << "\n";
    std::cout << "c: " << c << "\n";
    std::cout << "str3: " << str3 << "\n";

    std::cout << "vec: ";
    for (int i : vec) {
        std::cout << i << " ";
    }
    std::cout << "\n";

    std::cout << "arr: ";
    for (int i : arr) {
        std::cout << i << " ";
    }
    std::cout << "\n";

    std::cout << "Point p: (" << p.x << ", " << p.y << ")\n";

    return 0;
}
```



### 4. For-ranged

The range-based `for` loop, introduced in C++11, provides a more concise and readable way to iterate over elements in a container (like arrays, vectors, lists, etc.) or any range-supporting object. It simplifies the syntax compared to the traditional `for` loop and reduces the potential for errors.

### Basic Syntax

The range-based `for` loop has the following syntax:

```c++
for (declaration : range) {
    // loop body
}
```

- **`declaration`**: This is the type and variable name that will take on the value of each element in the range during each iteration.
- **`range`**: This is the container or range to iterate over.

### Example with a Standard Library Container

Here is an example using a `std::vector`:

```c++
#include <iostream>
#include <vector>

int main() {
    std::vector<int> numbers = {1, 2, 3, 4, 5};

    // Range-based for loop
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    return 0;
}
```

### Using References

If you need to modify the elements of the container, you should use a reference in the declaration:

```c++
#include <iostream>
#include <vector>

int main() {
    std::vector<int> numbers = {1, 2, 3, 4, 5};

    // Modify elements using a reference
    for (int& num : numbers) {
        num *= 2;
    }

    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    return 0;
}
```

### Using `const` References

If you are not modifying the elements and want to avoid copying them, you can use `const` references:

```c++
#include <iostream>
#include <vector>

int main() {
    std::vector<int> numbers = {1, 2, 3, 4, 5};

    // Use const reference to avoid copying elements
    for (const int& num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    return 0;
}
```

### Range-Based `for` Loop with Arrays

The range-based `for` loop also works with arrays:

```c++
#include <iostream>

int main() {
    int arr[] = {1, 2, 3, 4, 5};

    for (int num : arr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    return 0;
}
```

### Range-Based `for` Loop with Initializer Lists

You can also use range-based `for` loops with initializer lists:

```c++
#include <iostream>

int main() {
    for (int num : {1, 2, 3, 4, 5}) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    return 0;
}
```

### Custom Iterators

Range-based `for` loops work with any class that has `begin()` and `end()` member functions returning iterators, which includes all standard library containers.

### Summary

The range-based `for` loop simplifies iterating over elements in a container, improving code readability and reducing the potential for errors. Here’s a summary example combining various use cases:

```c++
#include <iostream>
#include <vector>
#include <list>
#include <string>

int main() {
    std::vector<int> vec = {1, 2, 3, 4, 5};
    std::list<std::string> strList = {"Hello", "World", "C++"};
    int arr[] = {10, 20, 30, 40, 50};

    // Vector
    for (int num : vec) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    // List with const reference
    for (const std::string& str : strList) {
        std::cout << str << " ";
    }
    std::cout << std::endl;

    // Array
    for (int num : arr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    // Initializer list
    for (int num : {100, 200, 300}) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    return 0;
}
```



### 5. Dynamic Allocation

Dynamic allocation in C++ refers to the process of allocating memory at runtime using pointers. This allows you to allocate memory when you don't know the size or amount of memory needed at compile time. Dynamic memory allocation in C++ is typically done using the `new` and `delete` operators for single variables and arrays.

### Single Variable Allocation

To allocate memory dynamically for a single variable, use the `new` operator. You should release the memory using the `delete` operator.

#### Example:

```c++
#include <iostream>

int main() {
    // Dynamic allocation of a single integer
    int* p = new int; // p is a pointer to an integer

    // Assign a value to the dynamically allocated integer
    *p = 42;

    std::cout << "Value of *p: " << *p << std::endl;

    // Free the allocated memory
    delete p;

    return 0;
}
```

### Array Allocation

To allocate memory dynamically for an array, use the `new` operator followed by the type and the number of elements in square brackets. Use the `delete[]` operator to free the allocated memory.

#### Example:

```c++
#include <iostream>

int main() {
    // Dynamic allocation of an array of integers
    int* arr = new int[5]; // arr is a pointer to the first element of an array of 5 integers

    // Assign values to the dynamically allocated array
    for (int i = 0; i < 5; ++i) {
        arr[i] = i + 1;
    }

    // Print the values
    for (int i = 0; i < 5; ++i) {
        std::cout << "arr[" << i << "] = " << arr[i] << std::endl;
    }

    // Free the allocated memory
    delete[] arr;

    return 0;
}
```



To initialize an array with specific values using `new`, you should provide the initializer list directly after the allocation. This feature is supported in C++11 and later.

Here is the correct way to initialize a dynamically allocated array with specific values:

```c++
#include <iostream>

int main() {
    // Dynamic allocation and initialization of an array with specific values
    int* arr = new int[5]{1, 2, 3, 4, 5};

    // Print the values to verify the initialization
    for (int i = 0; i < 5; ++i) {
        std::cout << "arr[" << i << "] = " << arr[i] << std::endl;
    }

    // Free the allocated memory
    delete[] arr;

    return 0;
}
```

### Explanation:

- **`int\* arr = new int[5]{1, 2, 3, 4, 5};`**: This line dynamically allocates an array of 5 integers and initializes it with the values `1, 2, 3, 4, 5`. This syntax is valid in C++11 and later.

### Notes:

1. **C++11 Requirement**: The ability to initialize arrays this way requires C++11 or later. Ensure your compiler is set to use at least C++11.
2. **Uninitialized Values**: If you provide fewer values than the size of the array, the remaining elements will be default-initialized (zero for fundamental types like `int`).

### Example with Partial Initialization:

```c++
#include <iostream>

int main() {
    // Dynamic allocation and partial initialization of an array
    int* arr = new int[5]{1, 2}; // Remaining elements will be initialized to 0

    // Print the values to verify the initialization
    for (int i = 0; i < 5; ++i) {
        std::cout << "arr[" << i << "] = " << arr[i] << std::endl;
    }

    // Free the allocated memory
    delete[] arr;

    return 0;
}
```

This will output:

```c++
arr[0] = 1
arr[1] = 2
arr[2] = 0
arr[3] = 0
arr[4] = 0
```

### Dynamic Allocation of Objects

Dynamic allocation can also be used for objects of user-defined types (classes). When using `new` to create an object, the constructor is called, and when using `delete`, the destructor is called.

#### Example:

```c++
#include <iostream>

class MyClass {
public:
    MyClass(int val) : value(val) {
        std::cout << "Constructor called with value: " << value << std::endl;
    }

    ~MyClass() {
        std::cout << "Destructor called for value: " << value << std::endl;
    }

    int getValue() const {
        return value;
    }

private:
    int value;
};

int main() {
    // Dynamic allocation of a single object
    MyClass* obj = new MyClass(42);

    std::cout << "Value of obj: " << obj->getValue() << std::endl;

    // Free the allocated memory
    delete obj;

    return 0;
}
```

### Best Practices

1. **Always Free Allocated Memory**: Every `new` should be matched with a `delete`, and every `new[]` should be matched with a `delete[]` to avoid memory leaks.
2. **Use Smart Pointers**: Consider using smart pointers (`std::unique_ptr`, `std::shared_ptr`) introduced in C++11 to automatically manage dynamic memory and reduce the risk of memory leaks and dangling pointers.
3. **Check for Allocation Failures**: Although `new` throws an exception if memory allocation fails, in older code or specific circumstances, you might encounter `NULL` pointers and should check for successful allocation.

### Using Smart Pointers

#### Example:

```c++
#include <iostream>
#include <memory> // Include memory for smart pointers

class MyClass {
public:
    MyClass(int val) : value(val) {
        std::cout << "Constructor called with value: " << value << std::endl;
    }

    ~MyClass() {
        std::cout << "Destructor called for value: " << value << std::endl;
    }

    int getValue() const {
        return value;
    }

private:
    int value;
};

int main() {
    // Using a smart pointer for automatic memory management
    std::unique_ptr<MyClass> obj = std::make_unique<MyClass>(42);

    std::cout << "Value of obj: " << obj->getValue() << std::endl;

    // No need to manually delete the object, as the smart pointer will handle it

    return 0;
}
```



### what is the difference between malloc Vs new

`malloc` and `new` are both used for dynamic memory allocation in C++, but they have significant differences in functionality, usage, and the type of memory they allocate. Here’s a detailed comparison:

### `malloc`

1. **Origin**: `malloc` is a function from the C standard library, but it can also be used in C++.

2. **Syntax**: `void* malloc(size_t size);`

   - You need to specify the number of bytes to allocate.
   - `malloc` returns a `void*` pointer, which you need to cast to the appropriate type.

   ```c++
   int* p = (int*)malloc(sizeof(int) * 5); // Allocates memory for an array of 5 integers
   ```

3. **Initialization**: `malloc` does not initialize the allocated memory. The content of the allocated memory is indeterminate (contains garbage values).

4. **Deallocation**: Memory allocated with `malloc` must be freed using `free`.

   ```c++
   free(p);
   ```

5. **Placement in the Memory**: Allocates memory from the heap.

6. **Error Handling**: Returns `NULL` if the memory allocation fails.

### `new`

1. **Origin**: `new` is a C++ operator specifically designed for dynamic memory allocation in C++.

2. **Syntax**: `type* new type;` or `type* new type[size];`

   - Automatically determines the size of the type being allocated.
   - Returns a pointer of the appropriate type, no casting needed.

   ```c++
   int* p = new int[5]; // Allocates memory for an array of 5 integers
   ```

3. **Initialization**: **new** initializes the allocated memory.

   - For fundamental types, it uses default initialization.

   - **For classes, it calls the constructor.**

     >Note that : 
     >
     >- **malloc** don't call the constructor but **new** call it (**interview Q**)
     >- **free** don't call the  destructor but **delete** call it.

   ```c++
   int* p = new int(5); // Allocates memory for a single int and initializes it to 5
   MyClass* obj = new MyClass(); // Allocates memory and calls the default constructor
   ```

4. **Deallocation**: Memory allocated with `new` must be freed using `delete` (for single objects) or `delete[]` (for arrays).

   ```c++
   delete p; // For single object
   delete[] p; // For arrays
   ```

5. **Placement in the Memory**: Allocates memory from the heap.

6. **Error Handling**: Throws a `std::bad_alloc` exception if the memory allocation fails, unless `nothrow` is used.

   ```c++
   int* p = nullptr;
   try {
       p = new int[5];
   } catch (std::bad_alloc& e) {
       std::cerr << "Memory allocation failed: " << e.what() << std::endl;
   }
   ```

### Key Differences

1. **Type Safety**: `new` returns a pointer of the correct type, whereas `malloc` returns `void*`, which needs to be explicitly cast to the correct type.
2. **Initialization**: `new` initializes memory (calls constructors for objects), while `malloc` does not initialize memory.
3. **Error Handling**: `new` throws an exception on failure, while `malloc` returns `NULL`.
4. **Usage**: `new` is preferred in C++ for its type safety and initialization features, while `malloc` is more common in C and can still be used in C++ for compatibility with C libraries.

### Example Comparison

#### Using `malloc`:

```c++
#include <iostream>
#include <cstdlib>

struct MyClass {
    int value;
    MyClass() : value(42) {}
};

int main() {
    // Allocate memory for an array of 5 integers
    int* p = (int*)malloc(sizeof(int) * 5);
    if (p == nullptr) {
        std::cerr << "Memory allocation failed" << std::endl;
        return 1;
    }
    // Initialize memory manually
    for (int i = 0; i < 5; ++i) {
        p[i] = i;
    }

    for (int i = 0; i < 5; ++i) {
        std::cout << p[i] << " ";
    }
    std::cout << std::endl;

    // Free the allocated memory
    free(p);

    return 0;
}
```

#### Using `new`:

```c++
#include <iostream>

struct MyClass {
    int value;
    MyClass() : value(42) {}
};

int main() {
    // Allocate memory for an array of 5 integers
    int* p = new int[5];
    // Memory is automatically initialized to zero if it's an array of fundamental types

    for (int i = 0; i < 5; ++i) {
        p[i] = i;
    }

    for (int i = 0; i < 5; ++i) {
        std::cout << p[i] << " ";
    }
    std::cout << std::endl;

    // Free the allocated memory
    delete[] p;

    return 0;
}
```



## Difference between NULL Vs nullptr

In C++, `NULL` and `nullptr` are both used to represent a null pointer, but they have important differences in terms of type safety and clarity.

### `NULL`

1. **Origin**: `NULL` is a macro defined in several standard headers, such as `<cstddef>`, and is inherited from C.
2. **Type**: `NULL` is typically defined as `0` or `((void*)0)`, depending on the compiler and platform.
3. **Type Safety**: `NULL` is an integer constant (usually `0`), which can lead to type ambiguity and potential errors when distinguishing between pointers and integers.

#### Example of Potential Issue:

```c++
void foo(int i) {
    std::cout << "Integer version: " << i << std::endl;
}

void foo(void* p) {
    std::cout << "Pointer version" << std::endl;
}

int main() {
    foo(NULL); // Ambiguous, could call either foo(int) or foo(void*)
    return 0;
}
```

### `nullptr`

1. **Origin**: Introduced in C++11 to provide a more type-safe and clear way to represent a null pointer.
2. **Type**: `nullptr` is of type `std::nullptr_t`, which can implicitly convert to any pointer type or to a pointer-to-member type but not to an integer type.
3. **Type Safety**: `nullptr` provides better type safety by ensuring that it can only be used in a context where a pointer is expected, eliminating the ambiguity present with `NULL`.

#### Example:

```c++
void foo(int i) {
    std::cout << "Integer version: " << i << std::endl;
}

void foo(void* p) {
    std::cout << "Pointer version" << std::endl;
}

int main() {
    foo(nullptr); // Unambiguously calls foo(void*)
    return 0;
}
```

### Comparison and Use Cases

#### Type Safety and Ambiguity

- `NULL` can be ambiguous because it is an integer literal that can be converted to any pointer type or stay as an integer. This can cause unexpected behavior or errors.
- `nullptr` is unambiguous and can only be converted to a pointer type, making it the safer and clearer choice.

## 6. Linkage

1. **Internal Linkage**:

   - **Description**: A symbol (variable or function) with internal linkage is only visible within the translation unit (source file) where it is defined.

   - **Usage**: Typically, this is achieved using the `static` keyword in C/C++.

   - Example

     :

     ```c++
     static int count = 0; // internal linkage
     
     static void increment() { // internal linkage
         count++;
     }
     ```

2. **External Linkage**:

   - **Description**: A symbol with external linkage is visible across different translation units. This means it can be accessed from other source files.

   - **Usage**: By default, global variables and functions have external linkage unless specified otherwise. The `extern` keyword is often used to declare external variables/functions in other files.

   - Example

     :

     ```c++
     int count = 0; // external linkage
     
     void increment() { // external linkage
         count++;
     }
     
     // In file2.c
     extern int count; // declaring external variable
     
     void reset() {
         count = 0;
     }
     ```

3. **No Linkage**:

   - **Description**: Symbols with no linkage are unique to each scope and cannot be referred to from other scopes. Typically, these are local variables inside functions.

   - Example

     :

     ```c++
     void function() {
         int local_count = 0; // no linkage
         local_count++;
     }
     ```

### Scope

1. **Global Scope**:

   - **Description**: Symbols defined outside any function or block. They can be accessed from anywhere in the program (if they have external linkage) or within the file (if they have internal linkage).

   - Example

     :

     ```c++
     int global_var = 0; // global scope, external linkage
     
     static int file_global_var = 1; // global scope, internal linkage
     ```

2. **Local Scope**:

   - **Description**: Symbols defined within a function or block. They can only be accessed within that function or block.

   - Example

     :

     ```c++
     void myFunction() {
         int local_var = 0; // local scope, no linkage
     }
     ```

3. **Block Scope**:

   - **Description**: Symbols defined within a block (enclosed by `{}`) inside a function. They are only accessible within that block.

   - Example

     :

     ```c++
     void myFunction() {
         if (1) {
             int block_var = 0; // block scope, no linkage
         }
         // block_var is not accessible here
     }
     ```

### Summary

- **Internal Linkage**: Visible only within the same file (static global variables/functions).
- **External Linkage**: Visible across different files (global variables/functions).
- **No Linkage**: Local to the block or function where defined.
- **Global Scope**: Outside of all functions, accessible globally if external linkage.
- **Local Scope**: Inside a function, accessible only within that function.
- **Block Scope**: Inside a block, accessible only within that block.



## 7. Type Inference

In C++, type inference refers to the ability of the compiler to deduce the data type of a variable based on the context in which it is declared and initialized, without explicitly specifying the type. This feature helps reduce verbosity in code while maintaining strong typing.

### Type Inference in C++:

1. **auto Keyword**:

   - Introduced in C++11, the `auto` keyword allows the compiler to automatically deduce the type of a variable from its initializer. This is particularly useful when the type can be complex or when using iterators or lambda expressions.

   ```c++
   auto x = 10;         // x is deduced as int
   auto name = "Alice"; // name is deduced as const char*
   auto pi = 3.14159;   // pi is deduced as double
   ```

2. **decltype Keyword**:

   - The `decltype` keyword deduces the type of a variable or expression. It can be used to declare a variable with the same type as another variable or to capture the return type of a function.

   ```c++
   int a = 10;
   decltype(a) b = 20;  // b is deduced as int
   
   double square(double x) {
       return x * x;
   }
   decltype(square(3.0)) result;  // result is deduced as double
   ```

3. **Template Type Inference**:

   - Templates in C++ rely heavily on type inference to allow functions and classes to operate with generic types. Type deduction in templates allows functions to be written once but operate on multiple types without explicitly specifying those types each time.

   ```c++
   template<typename T>
   T add(T a, T b) {
       return a + b;
   }
   
   int sum = add(3, 5);       // T deduced as int
   double total = add(3.5, 2.5); // T deduced as double
   ```

4. **Lambda Expressions**:

   - Lambda expressions in C++ also benefit from type inference, allowing the compiler to deduce the type of parameters and return type based on the context in which the lambda is used.

   ```c++
   auto lambda = [](int x) { return x * 2; };
   ```

5. **typeid  Keyword**

   #### Example 1: Using `typeid` with Variables

```c++
#include <iostream>
#include <typeinfo>

int main() {
    int number = 10;
    double pi = 3.14159;
    const char* message = "Hello, World!";

    // Using typeid with variables
    const std::type_info& typeInfoInt = typeid(number);
    const std::type_info& typeInfoDouble = typeid(pi);
    const std::type_info& typeInfoCharPointer = typeid(message);

    std::cout << "Type of number is: " << typeInfoInt.name() << std::endl;
    std::cout << "Type of pi is: " << typeInfoDouble.name() << std::endl;
    std::cout << "Type of message is: " << typeInfoCharPointer.name() << std::endl;

    return 0;
}
```



#### Example 2: Using `typeid` with Expressions

```c++
#include <iostream>
#include <typeinfo>

int main() {
    int a = 10, b = 20;
    double c = 3.5;

    // Using typeid with expressions
    const std::type_info& typeInfoA = typeid(a);
    const std::type_info& typeInfoB = typeid(a + b);
    const std::type_info& typeInfoC = typeid(c * 2);

    std::cout << "Type of a is: " << typeInfoA.name() << std::endl;
    std::cout << "Type of a + b is: " << typeInfoB.name() << std::endl;
    std::cout << "Type of c * 2 is: " << typeInfoC.name() << std::endl;

    return 0;
}
```

### Explanation:

- **Example 1**:
  - Demonstrates using `typeid` with variables of different types (`int`, `double`, `const char*`).
  - `typeInfoInt.name()`, `typeInfoDouble.name()`, and `typeInfoCharPointer.name()` are used to retrieve the names of their respective types (`int`, `double`, `const char*`).
- **Example 2**:
  - Uses `typeid` with expressions involving basic arithmetic operations (`+`, `*`).
  - `typeid(a + b)` and `typeid(c * 2)` show how `typeid` can be used to get the type of expressions, not just variables.

### Output:

For both examples, the output typically shows the mangled name of the type, which might not be very human-readable but is unique and consistent:

```c++
Type of number is: int
Type of pi is: double
Type of message is: PKc
Type of a is: int
Type of a + b is: int
Type of c * 2 is: double
```

### Notes:

- **Type Names**: The names returned by `type_info::name()` are implementation-defined and may not be the same across different compilers or platforms.
- **Expression Type Deduction**: `typeid` allows you to determine the exact type of expressions dynamically at runtime, which can be useful for debugging or for writing generic code that handles different types.



### Benefits of Type Inference:

- **Reduced Typing**: It reduces the need for developers to explicitly specify types, making the code more concise and readable.
- **Maintained Strong Typing**: Despite type inference, C++ remains a strongly typed language, ensuring type safety at compile-time.
- **Support for Generic Programming**: Enables generic programming with templates and lambdas, facilitating code reuse and flexibility.

### Considerations:

- While type inference can improve code readability and maintainability, it's essential to strike a balance between explicit type declarations and relying on inference, especially in cases where clarity and documentation are crucial.
- Type inference might not always be suitable for every situation, particularly when dealing with complex types or where the type might not be immediately obvious.



### 8. Pair

In C++, `std::pair` is a template class that allows you to store a pair of heterogeneous objects as a single entity. It's part of the Standard Template Library (STL) and provides a convenient way to manage pairs of values without defining a custom struct or class. Here's an overview of `std::pair` with examples:

### Syntax and Usage:

To use `std::pair`, you need to include the `<utility>` header file:

```c++
#include <utility>
#include <iostream> // for std::cout and std::endl

int main() {
    // Example 1: Creating a pair of integers
    std::pair<int, int> pair1 = std::make_pair(1, 2);
    
    // Example 2: Creating a pair of different types
    std::pair<int, double> pair2(3, 4.5);
    
    // Example 3: Accessing elements of a pair
    std::cout << "Pair 1: (" << pair1.first << ", " << pair1.second << ")" << std::endl;
    std::cout << "Pair 2: (" << pair2.first << ", " << pair2.second << ")" << std::endl;
    
    // Example 4: Modifying elements of a pair
    pair1.first = 10;
    pair1.second = 20;
    std::cout << "Modified Pair 1: (" << pair1.first << ", " << pair1.second << ")" << std::endl;
    
    // Example 5: Pair with different types and default constructor
    std::pair<std::string, bool> pair3;
    pair3.first = "Hello";
    pair3.second = true;
    std::cout << "Pair 3: (" << pair3.first << ", " << pair3.second << ")" << std::endl;
    
    return 0;
}
```

### Explanation of Examples:

1. **Example 1**: Creating a pair of integers using `std::make_pair()`:
   - `std::make_pair(1, 2)` creates a pair where the first element (`first`) is `1` and the second element (`second`) is `2`.
2. **Example 2**: Creating a pair of different types:
   - `std::pair<int, double> pair2(3, 4.5)` creates a pair where the first element is an `int` (`3`) and the second element is a `double` (`4.5`).
3. **Example 3**: Accessing elements of a pair:
   - `pair.first` and `pair.second` are used to access the first and second elements of the pair, respectively.
4. **Example 4**: Modifying elements of a pair:
   - You can modify the elements of a pair using assignment (`pair.first = ...` and `pair.second = ...`).
5. **Example 5**: Pair with different types and default constructor:
   - `std::pair<std::string, bool> pair3;` creates an empty pair where the first element is a `std::string` (`"Hello"`) and the second element is a `bool` (`true`).

### Key Features and Use Cases:

- **Convenience**: `std::pair` provides a convenient way to bundle two values together without defining a separate class or struct.

- **Function Return Types**: Often used as a return type for functions that need to return two values.

  Example:

  ```c++
  std::pair<int, int> divide(int dividend, int divisor) {
      return std::make_pair(dividend / divisor, dividend % divisor);
  }
  ```

- **STL Algorithms**: Pairs can be used with various algorithms from the STL, such as `std::sort()` or `std::find_if()`, to process pairs of elements.

  Example:

  ```c++
  std::vector<std::pair<int, std::string>> vec = {{1, "one"}, {2, "two"}, {3, "three"}};
  
  // Sort pairs based on the first element
  std::sort(vec.begin(), vec.end(), [](const auto& lhs, const auto& rhs) {
      return lhs.first < rhs.first;
  });
  ```

- **Tuple-like Interface**: Pairs behave similarly to `std::tuple` but are limited to exactly two elements (`first` and `second`).

### Additional Notes:

- **Pair Comparison**: Pairs support comparison operators (`==`, `!=`, `<`, `<=`, `>`, `>=`) based on lexicographical order of their elements.
- **Initialization**: Pairs can be initialized using various constructors, including default, copy, and move constructors.
- **Type Aliases**: In C++11 and later, `std::pair` provides type aliases (`first_type` and `second_type`) to access the types of its elements.



### 9. Structured Binding

Structured bindings in C++ provide a convenient way to decompose objects (such as tuples, pairs, or user-defined types) into their individual members directly into variables. This feature was introduced in C++17 to simplify code and improve readability when working with structured data.

### Syntax and Usage:

To use structured bindings, you typically declare variables that correspond to the members of a structured object using a declaration that mimics the structure of the object itself.

#### Example 1: Structured Binding with `std::pair`

```c++
#include <iostream>
#include <tuple>
#include <utility> // for std::pair

int main() {
    std::pair<int, double> myPair(10, 3.14);

    auto [num, value] = myPair;  // Structured binding declaration

    std::cout << "Pair elements: " << num << " and " << value << std::endl;

    return 0;
}
```

**Explanation**:

- `auto [num, value] = myPair;`: Here, `num` and `value` are variables that are automatically initialized with the elements of `myPair`.
- `num` will be initialized to `10` (the first element of the pair).
- `value` will be initialized to `3.14` (the second element of the pair).

#### Example 2: Structured Binding with `std::tuple`

```c++
#include <iostream>
#include <tuple>

int main() {
    std::tuple<int, double, std::string> myTuple(1, 2.5, "Hello");

    auto [num, value, text] = myTuple;  // Structured binding declaration

    std::cout << "Tuple elements: " << num << ", " << value << ", " << text << std::endl;

    return 0;
}
```

**Explanation**:

- `auto [num, value, text] = myTuple;`: Here, `num`, `value`, and `text` are variables that correspond to the elements of `myTuple`.
- `num` will be initialized to `1`, `value` to `2.5`, and `text` to `"Hello"`.

#### Example 3: Structured Binding with User-Defined Types

```c++
#include <iostream>
#include <string>

struct Person {
    std::string name;
    int age;
};

int main() {
    Person p1 = {"Alice", 30};

    auto [name, age] = p1;  // Structured binding declaration

    std::cout << "Person: " << name << ", Age: " << age << std::endl;

    return 0;
}
```

**Explanation**:

- `auto [name, age] = p1;`: Here, `name` and `age` are variables that directly correspond to the members of the `Person` structure `p1`.
- `name` will be initialized to `"Alice"` and `age` to `30`.

### Benefits of Structured Bindings:

- **Improved Readability**: Avoids repetitive access via member functions or indexing, making the code more concise and readable.
- **Simplifies Code**: Reduces the need for temporary variables or manual extraction of data from structured types.
- **Supports Various Types**: Works with standard library containers like `std::pair`, `std::tuple`, and user-defined types that have accessible members.

### Considerations:

- **Immutable Bindings**: Structured bindings introduce immutable bindings (`const`-qualified by default), meaning you cannot modify the original elements through the structured binding variables.
- **Compatibility**: Requires C++17 or later to use this feature. Ensure your compiler supports C++17 or later standards.
