# The Implementation of Vector C++

```c++
#include <iostream>           
#include <initializer_list>   
#include <stdexcept>          
```

### 1. Libraries

1. **`#include <iostream>`**
   - This library is used for input-output operations, enabling the use of `std::cout` for printing to the console and `std::endl` for newline characters.
2. **`#include <initializer_list>`**
   - This library allows the use of `std::initializer_list`, which is used to initialize the vector with a list of values in a convenient way.
3. **`#include <stdexcept>`**
   - This library provides standard exception classes like `std::out_of_range`, which is used for throwing exceptions when an invalid index is accessed.

```c++
template<typename T>  
class vector
{
private:
    T* data;        
    int CurrSize;   
    int capacity;   
```

### 2. Class Definition

1. **Template Class Definition**
   - The class `vector` is defined as a template to support different data types (`T`).
2. **Private Members**
   - **`T* data`:** A pointer to the array that holds the elements of the vector.
   - **`int CurrSize`:** The current number of elements in the vector.
   - **`int capacity`:** The maximum number of elements the vector can hold without resizing.

```c++
public:

    vector()
    {
        capacity = 1;
        CurrSize = 0;          
        data = new T[capacity];
    }

    vector(int size)
    {
        capacity = size;
        CurrSize = size;
        data = new T[capacity];
    }


    vector(int size, T value)
    {
        capacity = size;
        CurrSize = size;
        data = new T[capacity];
        for(int i = 0; i < size; i++)
        {
            data[i] = value;  
        }
    }

    vector(std::initializer_list<T> list)
    {
        capacity = list.size();
        CurrSize = capacity;
        data = new T[capacity];
        int i = 0;
        for(const T& item : list)
        {
            data[i++] = item;  
        }
    }

    vector(const vector& newVec)
    {
        CurrSize = newVec.CurrSize;
        capacity = newVec.capacity;
        data = new T[capacity];
        for(int i = 0; i < CurrSize; i++)
        {
            data[i] = newVec.data[i];  
        }
    }

    ~vector()
    {
        delete[] data;
    }


    void resize()
    {
        capacity *= 2;
        T* newData = new T[capacity];
        for(int i = 0; i < CurrSize; i++)
        {
            newData[i] = data[i];  
        }
        delete[] data;  
        data = newData; 
    }

    void pushback(const T& value)
    {
        if(CurrSize >= capacity)
        {
            resize();  
        }
        data[CurrSize++] = value;  
    }

    T popback()
    {
        if(CurrSize > 0)
        {
            return data[--CurrSize];  
        }
        else
        {
            throw std::out_of_range("Attempt to pop from an empty array");  
        }
    }

    void insertAt(int index, const T& value)
    {
        if(index < 0 || index > CurrSize)
        {
            throw std::out_of_range("The index is out of range");
        }
        if(CurrSize >= capacity)
        {
            resize();  
        }
        for(int i = CurrSize; i > index; i--)
        {
            data[i] = data[i - 1];  
        }
        data[index] = value;  
        CurrSize++;
    }

    void removeAt(int index)
    {
        if(index < 0 || index >= CurrSize)
        {
            throw std::out_of_range("The index is out of range");
        }
        for(int i = index; i < CurrSize - 1; i++)
        {
            data[i] = data[i + 1];  
        }
        CurrSize--;
    }

    
    void insertMid(const T& value)
    {
        int mid = CurrSize / 2;
        insertAt(mid, value);  
    }

    void removeMid()
    {
        int mid = CurrSize / 2;
        removeAt(mid);  
    }

    int size() const
    {
        return CurrSize;
    }

    void print() const
    {
        std::cout << "The Vector: ";
        for(int i = 0; i < CurrSize; i++)
        {
            std::cout << data[i] << " ";  
        }
        std::cout << std::endl;
    }
};
```



### 3. Constructors

1. **Default Constructor**
   - Initializes the vector with a capacity of 1 and sets the current size to 0. Allocates memory for the array.
2. **Constructor with Initial Size**
   - Takes an integer `size` as a parameter, sets both `capacity` and `CurrSize` to this size, and allocates memory for the array.
3. **Constructor with Initial Size and Value**
   - Takes an integer `size` and a value of type `T`. Sets both `capacity` and `CurrSize` to this size, allocates memory for the array, and initializes all elements with the given value.
4. **Constructor with Initializer List**
   - Takes an `std::initializer_list<T>` to initialize the vector with a list of values. Sets `capacity` and `CurrSize` to the size of the list, allocates memory for the array, and initializes the elements with the values from the list.
5. **Copy Constructor**
   - Takes a reference to another vector object. Copies the `CurrSize`, `capacity`, and elements from the given vector to the new vector.
6. **Destructor**
   - Frees the allocated memory for the array when the vector object is destroyed.

### 4. Member Functions

1. **Resize Function**
   - Doubles the current capacity of the vector, allocates a new array with the new capacity, copies the elements from the old array to the new array, and frees the memory of the old array.
2. **Pushback Function**
   - Adds a value to the end of the array. If the current size reaches the capacity, it calls the `resize` function to double the capacity before adding the new value.
3. **Popback Function**
   - Removes and returns the last element from the array. If the array is empty, it throws an `std::out_of_range` exception.
4. **InsertAt Function**
   - Inserts a value at a specified index. If the index is out of range, it throws an `std::out_of_range` exception. If the current size reaches the capacity, it calls the `resize` function. Shifts the elements to the right to make space for the new value and then inserts it.
5. **RemoveAt Function**
   - Removes the element at a specified index. If the index is out of range, it throws an `std::out_of_range` exception. Shifts the elements to the left to fill the gap left by the removed element.
6. **InsertMid Function**
   - Inserts a value in the middle of the array. Uses the `insertAt` function to insert the value at the middle index.
7. **RemoveMid Function**
   - Removes the middle element from the array. Uses the `removeAt` function to remove the element at the middle index.
8. **Size Function**
   - Returns the current size of the vector.
9. **Print Function**
   - Prints the elements of the vector to the console.

```c++
int main()
{
    vector<int> v{1,2,3,4,5};  // Create a vector with initial values using initializer list
    v.print();                 // Print the vector
    v.pushback(10);            // Add 10 to the end of the vector
    v.pushback(20);            // Add 20 to the end of the vector
    v.insertAt(2, 15);         // Insert 15 at index 2
    v.print();                 // Print the vector
    v.removeAt(1);             // Remove element at index 1
    v.print();                 // Print the vector
    v.insertMid(25);           // Insert 25 in the middle of the vector
    v.print();                 // Print the vector
    v.removeMid();             // Remove the middle element of the vector
    v.print();                 // Print the vector
    std::cout << "Size: " << v.size() << std::endl;  // Print the size of the vector

    return 0;  // End of the program
}
```

### 5. Main Function

1. Main Function
   - Demonstrates the usage of the `vector` class.
   - Creates a vector `v` with initial values `{1, 2, 3, 4, 5}`.
   - Prints the vector.
   - Adds the values `10` and `20` to the end of the vector.
   - Inserts the value `15` at index 2.
   - Prints the vector.
   - Removes the element at index 1.
   - Prints the vector.
   - Inserts the value `25` in the middle of the vector.
   - Prints the vector.
   - Removes the middle element from the vector.
   - Prints the vector.
   - Prints the current size of the vector.
