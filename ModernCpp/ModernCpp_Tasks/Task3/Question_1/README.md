### Include Necessary Headers

```c++
#include <iostream>
#include <cstdlib>
#include <ctime>
```

- `#include <iostream>`: This header file includes the input/output stream library, which is used for handling standard input and output operations (e.g., `std::cout` for printing to the console).
- `#include <cstdlib>`: This header file includes the standard library functions, including random number generation functions such as `std::rand()` and `std::srand()`.
- `#include <ctime>`: This header file includes functions related to time, such as `std::time()`, which is used to get the current calendar time.

### Define the Vertex Structure

```c++
struct vertex {
    int x;
    int y;
};
```

- This defines a structure named `vertex` with two integer components: `x` and `y`. This structure represents a point on a 2D plane with coordinates `x` and `y`.



### Seed the Random Number Generator

```c++
/* Seed the random number with the current time */
std::srand(static_cast<unsigned>(std::time(0)));
```

- `std::srand()`: Seeds the pseudo-random number generator used by `std::rand()`. Seeding ensures that the sequence of random numbers generated is different each time the program is run.
- `std::time(0)`: Returns the current time as the number of seconds since the Unix epoch (00:00:00 UTC, January 1, 1970). This value changes every second.
- `static_cast<unsigned>`: Converts the `std::time(0)` result from `std::time_t` to `unsigned int`. This is necessary because `std::srand` expects an `unsigned int`.

### Declare Array of Vertices

```c++
 vertex vertices[5];
```

- This declares an array named `vertices` of size 5, where each element is of type `vertex`. This array will store 5 randomly generated vertices.

### Generate 5 Random Vertices

```c++
/* Generate 5 random vertices */
    for(int i = 0; i < 5; i++) {
        vertices[i].x = std::rand() % 201 - 100;
        vertices[i].y = std::rand() % 201 - 100;
    }
```

>1. **Generate a Random Number:**
>   - `std::rand()` produces a pseudo-random integer. This value is potentially very large, up to `RAND_MAX`.
>2. **Limit the Range to [0, 200]:**
>   - `std::rand() % 201` takes the random integer and reduces it to a number within the range [0, 200]. The maximum value (200) comes from 201 possible remainders (0 through 200).
>3. **Shift the Range to [-100, 100]:**
>   - By subtracting 100 from the result of `std::rand() % 201`, we shift the entire range down by 100.
>   - This means:
>     - A result of 0 from `std::rand() % 201` becomes -100 (0 - 100).
>     - A result of 200 from `std::rand() % 201` becomes 100 (200 - 100).
>     - A result of 100 from `std::rand() % 201` remains 0 (100 - 100).
>
>### Example Calculations
>
>Let's go through a few example calculations to see how the random numbers are transformed:
>
>1. **Example 1:**
>   - `std::rand()` returns 12345.
>   - `12345 % 201` results in 123.
>   - `123 - 100` results in 23.
>   - So, `vertices[i].x` (or `vertices[i].y`) is set to 23.
>2. **Example 2:**
>   - `std::rand()` returns 67890.
>   - `67890 % 201` results in 189.
>   - `189 - 100` results in 89.
>   - So, `vertices[i].x` (or `vertices[i].y`) is set to 89.
>3. **Example 3:**
>   - `std::rand()` returns 54321.
>   - `54321 % 201` results in 78.
>   - `78 - 100` results in -22.
>   - So, `vertices[i].x` (or `vertices[i].y`) is set to -22.
>
>

### Print the Generated Vertices

```c++
std::cout << "Random vertices (x, y):" << std::endl;
    for (int i = 0; i < 5; ++i) {
        std::cout << "(" << vertices[i].x << ", " << vertices[i].y << ")" << std::endl;
    }
```

- `std::cout << "Random vertices (x, y):" << std::endl;`: Prints the header line to the console.
- This `for` loop iterates over the array of vertices and prints each vertex's `x` and `y` coordinates in the format `(x, y)`.

