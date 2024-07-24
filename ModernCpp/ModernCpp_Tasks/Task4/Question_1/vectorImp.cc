#include <iostream>           // For input-output operations (std::cout, std::endl)
#include <initializer_list>   // For using std::initializer_list to initialize the vector with a list of values
#include <stdexcept>          // For using std::out_of_range exception

template<typename T>  // Template class to create a vector that can hold any data type T
class vector
{
private:
    T* data;        // Pointer to the array that holds the elements
    int CurrSize;   // Current number of elements in the vector
    int capacity;   // Current capacity of the vector (maximum elements it can hold without resizing)
public:
    // Default constructor with capacity of 1
    vector()
    {
        capacity = 1;
        CurrSize = 0;          // Start with an empty vector
        data = new T[capacity];
    }

    // Constructor with a given initial size
    vector(int size)
    {
        capacity = size;
        CurrSize = size;
        data = new T[capacity];
    }

    // Constructor with a given initial size and default value for all elements
    vector(int size, T value)
    {
        capacity = size;
        CurrSize = size;
        data = new T[capacity];
        for(int i = 0; i < size; i++)
        {
            data[i] = value;  // Initialize all elements with the given value
        }
    }

    // Constructor with initializer list
    vector(std::initializer_list<T> list)
    {
        capacity = list.size();
        CurrSize = capacity;
        data = new T[capacity];
        int i = 0;
        for(const T& item : list)
        {
            data[i++] = item;  // Initialize vector with values from the initializer list
        }
    }

    // Copy constructor
    vector(const vector& newVec)
    {
        CurrSize = newVec.CurrSize;
        capacity = newVec.capacity;
        data = new T[capacity];
        for(int i = 0; i < CurrSize; i++)
        {
            data[i] = newVec.data[i];  // Copy elements from the given vector
        }
    }

    // Destructor to free allocated memory
    ~vector()
    {
        delete[] data;
    }

    // Function to resize the array to double the current capacity
    void resize()
    {
        capacity *= 2;
        T* newData = new T[capacity];
        for(int i = 0; i < CurrSize; i++)
        {
            newData[i] = data[i];  // Copy old data to new array
        }
        delete[] data;  // Free old memory
        data = newData; // Point data to the new array
    }

    // Function to add a value to the end of the array
    void pushback(const T& value)
    {
        if(CurrSize >= capacity)
        {
            resize();  // Resize if current size exceeds capacity
        }
        data[CurrSize++] = value;  // Add value and increment size
    }

    // Function to remove the last element from the array
    T popback()
    {
        if(CurrSize > 0)
        {
            return data[--CurrSize];  // Decrease size and return last element
        }
        else
        {
            throw std::out_of_range("Attempt to pop from an empty array");  // Error if array is empty
        }
    }

    // Function to insert a value at a given index
    void insertAt(int index, const T& value)
    {
        if(index < 0 || index > CurrSize)
        {
            throw std::out_of_range("The index is out of range");
        }
        if(CurrSize >= capacity)
        {
            resize();  // Resize if necessary
        }
        for(int i = CurrSize; i > index; i--)
        {
            data[i] = data[i - 1];  // Shift elements to the right
        }
        data[index] = value;  // Insert new value
        CurrSize++;
    }

    // Function to remove the element at a given index
    void removeAt(int index)
    {
        if(index < 0 || index >= CurrSize)
        {
            throw std::out_of_range("The index is out of range");
        }
        for(int i = index; i < CurrSize - 1; i++)
        {
            data[i] = data[i + 1];  // Shift elements to the left
        }
        CurrSize--;
    }

    // Function to insert a value in the middle of the array
    void insertMid(const T& value)
    {
        int mid = CurrSize / 2;
        insertAt(mid, value);  // Reuse insertAt function to insert in the middle
    }

    // Function to remove the middle element from the array
    void removeMid()
    {
        int mid = CurrSize / 2;
        removeAt(mid);  // Reuse removeAt function to remove the middle element
    }

    // Function to return the current size of the array
    int size() const
    {
        return CurrSize;
    }

    // Function to print the array
    void print() const
    {
        std::cout << "The Vector: ";
        for(int i = 0; i < CurrSize; i++)
        {
            std::cout << data[i] << " ";  // Print each element
        }
        std::cout << std::endl;
    }
};

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


