## Complex Class Explanation



### Including the iostream Header

```c++
#include<iostream>
```

This line includes the standard input-output stream library in C++. It allows us to use `std::cout` for outputting data to the console.

### 2. Class Definition: complex

The `complex` class represents complex numbers with real and imaginary parts.

#### Private Members

```c++
private:
    int real;
    int img;
```

- `real`: Represents the real part of the complex number.
- `img`: Represents the imaginary part of the complex number.

These members are private, meaning they cannot be accessed directly from outside the class.

#### Public Members

The public section contains constructors, member functions (methods), and the destructor.

##### Constructors

1. **Default Constructor**

   ```c++
   complex()
   {
       real = 0;
       img = 0;
   }
   ```

   This constructor initializes the real and imaginary parts of the complex number to 0.

2. **Single-Parameter Constructor**

   ```c++
   complex(int num)
   {
       real = img = num;
   }
   ```

   This constructor initializes both the real and imaginary parts to the same value, `num`.

3. **Two-Parameter Constructor**

   ```c++
   complex(int r, int i)
   {
       real = r;
       img = i;
   }
   ```

   This constructor initializes the real part to `r` and the imaginary part to `i`.

##### Setter Functions

1. **setReal**

   ```c++
   void setReal(int real)
   {
       this->real = real;
   }
   ```

   This function sets the real part of the complex number. The `this` pointer is used to refer to the calling object, differentiating the member variable `real` from the parameter `real`.

2. **setImg**

   ```c++
   void setImg(int img)
   {
       this->img = img;
   }
   ```

   This function sets the imaginary part of the complex number.

3. **setComplex**

   ```c++
   void setComplex(int real, int img)
   {
       this->real = real;
       this->img = img;
   }
   ```

   This function sets both the real and imaginary parts of the complex number.

##### Getter Functions

1. **getReal**

   ```c++
   int getReal() const
   {
       return real;
   }
   ```

   This function returns the real part of the complex number. The `const` keyword indicates that this function does not modify the object.

2. **getImg**

   ```c++
   int getImg() const
   {
       return img;
   }
   ```

   This function returns the imaginary part of the complex number.

##### printComplex Function

```c++
void printComplex()
{
    std::cout << "Complex number: " << real << " + " << img << "i" << std::endl;
}
```

This function prints the complex number in the form `a + bi` to the console, where `a` is the real part and `b` is the imaginary part.

### 3. Main Function

The `main` function demonstrates how to use the `complex` class.

```c++
int main()
{
    complex c1(3, 4);
    c1.printComplex();

    complex c2(5);
    c2.printComplex();

    complex c3;
    c3.setComplex(7, 8);
    c3.printComplex();

    return 0;
}
```

1. **Creating Objects and Printing Complex Numbers**

   - `complex c1(3, 4);`: Creates an object `c1` with the real part as `3` and the imaginary part as `4`.
   - `c1.printComplex();`: Prints `c1` as "Complex number: 3 + 4i".

2. **Creating an Object with a Single-Parameter Constructor**

   - `complex c2(5);`: Creates an object `c2` with both the real and imaginary parts as `5`.
   - `c2.printComplex();`: Prints `c2` as "Complex number: 5 + 5i".

3. **Using the Default Constructor and Setter Methods**

   - `complex c3;`: Creates an object `c3` with the default constructor (real and imaginary parts are 0).
   - `c3.setComplex(7, 8);`: Sets the real part to `7` and the imaginary part to `8`.
   - `c3.printComplex();`: Prints `c3` as "Complex number: 7 + 8i".

4. **Return Statement**

   ```c++
   return 0;
   ```
