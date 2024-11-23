# Table of Content

1. **How to execute scripts**
2. **Variables in bash scripting**
3. **Conditions in bash scripting**
4. **Case in bash scripting**
5. **Select in bash scripting**
6. **Operators in bash scripting**
7. **loops in bash scripting**
8. **Arrays in bash scripting**
9. **Script Arguments**
10. **strings in bash scripting**
11. **Functions in bash scripting**

------------------------------------------------------------------------------------------------------------------------------------------------

# Bash Scripting



## 1. How to Execute bash script

#### 1.1. `./file.sh`

- **Creates a child process to execute the script**.

- The script runs in a new shell instance, separate from the current shell.

- Any variables or changes made in the script do not affect the current shell.

- You need to ensure the script has execute permissions (`chmod +x file.sh`).

  ```bash
  #!/bin/bash
  myvar="Hello from script"
  echo $myvar
  ```

  Run the script:

  ```bash
  chmod +x file.sh
  ./file.sh
  ```

  - If you run the above script and then try to access `myvar` in your current shell, it will not be available because it was set in a child process.

#### 1.2. `bash file.sh`

- **Executes the script using the `bash` command**.

- Similar to `./file.sh`, it runs the script in a new shell instance.

- Useful when the script does not have execute permissions or when you want to explicitly use `bash`.

  ```bash
  bash file.sh
  ```

  - Like `./file.sh`, any variables or changes made in the script do not affect the current shell.

#### 1.3.  `. file.sh` (dot space filename)

- **Executes the script in the same terminal**.

- The script runs in the current shell, not a child process.

- Any variables or changes made in the script affect the current shell environment.

  ```bash
  #!/bin/bash
  myvar="Hello from script"
  echo $myvar
  ```

  Run the script:

  ```bash
  . file.sh
  ```

  - After running the script, `myvar` will be available in your current shell.

#### 1.4. `source file.sh`

- **Executes the script in the same terminal**.

- The behavior is identical to `. file.sh`.

- It is more readable and is often preferred for sourcing files.

  ```bash
  source file.sh
  ```

  - Like `. file.sh`, any variables or changes made in the script affect the current shell environment.

###  Differences:

- **Child Process vs. Current Shell**:
  - `./file.sh` and `bash file.sh` run the script in a new shell instance (child process).
  - `. file.sh` and `source file.sh` run the script in the current shell.
- **Impact on Current Shell**:
  - `./file.sh` and `bash file.sh` do not affect the current shell environment.
  - `. file.sh` and `source file.sh` can modify the current shell environment (e.g., variables, working directory).
- **Usage**:
  - Use `./file.sh` or `bash file.sh` when you want to run a script independently of the current shell.
  - Use `. file.sh` or `source file.sh` when you need the script to modify the current shell environment.

### Example : Let's consider a script that sets an environment variable:

```bash
#!/bin/bash
export MYVAR="Hello from script"
echo "Inside script: MYVAR=$MYVAR"
```

1. **Using `./file.sh` or `bash file.sh`**:

   ```bash
   chmod +x file.sh
   ./file.sh
   # or
   bash file.sh
   ```

   - Output: `Inside script: MYVAR=Hello from script`
   - After running the script: `echo $MYVAR` will output nothing (variable not set in the current shell).

2. **Using `. file.sh` or `source file.sh`**:

   ```bash
   . file.sh
   # or
   source file.sh
   ```

   - Output: `Inside script: MYVAR=Hello from script`
   - After running the script: `echo $MYVAR` will output `Hello from script` (variable set in the current shell).





## 2. How to Write Bash Script

### 2.1. Variables

#### 2.1.1. Rules for Naming Variables

- **Not Allowed to Start with a Number or Special Character**:

  - Correct:

    ```bash
    myVar="value"
    _myVar="value"
    var123="value"
    ```


  - Incorrect:

    ```bash
    1var="value"  # Error: variable name cannot start with a number
    @var="value"  # Error: variable name cannot start with a special character
    ```

- **No Spaces Around the Equal Sign (`=`)**:

  - Correct:

    ```bash
    var=name
    ```


  - Incorrect:

    ```bash
    var = name  # Error: spaces around '=' are not allowed
    ```

- **Use Quotes for String with Spaces**:

  - Correct:

    ```bash
    var="ahmed mohamed"
    ```


  - Incorrect:

    ```bash
    var=ahmed mohamed  # Error: unquoted string with spaces
    ```

### 2.1.2. Accessing Variable Values

- Access Variable Value using `$name` or `${name}`:

  ```bash
  name="John"
  echo $name       # Outputs: John
  echo ${name}     # Outputs: John
  ```

### 2.1.3. Appending to Variables

- Use `${name}` for Appending:

  ```bash
  var="John"
  echo ${var}1     # Outputs: John1
  ```

- Incorrect:

  ```bash
  var="John"
  echo $var1       # Error if var1 is not defined
  ```

### 2.1.4. Single Quotes vs. Double Quotes

- **Difference Between Single and Double Quotes**:

  ```bash
  var="iti"
  var2="hello $var"
  echo $var2       # Outputs: hello iti
  ```

  ```bash
  var3='hello $var'
  echo $var3       # Outputs: hello $var
  ```

- **Explanation**:

  - **Single Quotes (`'`)**: Treat everything inside as a literal string.
  - **Double Quotes (`"`)**: Allow variable expansion and command substitution.

### 2.1.5. Examples

- **Basic Variable Usage**:

```bash
name="Alice"
greeting="Hello, $name"
echo $greeting   # Outputs: Hello, Alice
```

- **Appending to Variable**:

```bash
base="file"
extension="txt"
filename="${base}.${extension}"
echo $filename   # Outputs: file.txt
```

#### 2.1.6. Getting Input Data from User

- #### Using `read` to Get Input

```bash
read -p "Enter your name: " username
echo "Hello, $username"
```

- `-p "prompt"`: Display a prompt message before reading the input.

- **Hiding Input (e.g., for Passwords) Using `-s` to Hide Input**:

```bash
read -s -p "Enter password: " password
echo
echo "Your password is $password"
```

- `-s`: Silent mode (input will not be displayed).

#### 2.1.7. Command Substitution

- **Storing Command Output in a Variable**:

  ```bash
  current_date=$(date)
  echo "Today's date is: $current_date"
  ```

  - `$(command)`: Executes the command and stores its output in the variable.

### Examples

1. **Variable Assignment**:

   ```bash
   var="value"            # Correct
   var = value            # Incorrect (spaces around '=' not allowed)
   name="John Doe"        # Use quotes for strings with spaces
   ```

2. **Accessing and Appending**:

   ```bash
   echo $var              # Access variable value
   echo ${var}            # Another way to access variable value
   var="Hello"
   echo ${var} World!     # Outputs: Hello World!
   ```

3. **Single vs. Double Quotes**:

   ```bash
   var="world"
   echo "Hello $var"      # Outputs: Hello world
   echo 'Hello $var'      # Outputs: Hello $var
   ```

4. **User Input**:

   ```bash
   read -p "Enter your name: " username
   echo "Hello, $username"
   ```

5. **Command Substitution**:

   ```bash
   output=$(date)
   echo $output           # Outputs current date and time
   ```



#### 2.1.8. Operations on Variables

- **C-Style Increment in Bash**

```bash
((Var++))
```

- This is a C-style increment operation. It means increment the value of `Var` by 1.
- `Var++` will increase the value of `Var` after its current value is used.
- `++Var` will increase the value of `Var` before its current value is used.

```bash
Var=5
((Var++))  # Now Var is 6
```

- **Using `expr` for Arithmetic Operations**

```bash
var2=$(expr $var + 1)
```

- `expr` is a command-line utility that evaluates expressions. Here, it takes the current value of `var`, adds 1 to it, and stores the result in `var2`.
- Note the use of `$()` to capture the output of `expr`.

```bash
var=5
var2=$(expr $var + 1)  # var2 is now 6
```

```bash
var3=$(expr 2 + 2)
```

- Similarly, this line calculates the sum of 2 and 2, and stores the result in `var3`.

```bash
var3=$(expr 2 + 2)  # var3 is now 4
```

- **Declaring Variables as Integers**

```bash
declare -i var=5
```

- The `declare` command in Bash allows you to explicitly declare variables and their attributes.
- The `-i` option specifies that the variable should be treated as an integer.
- Once declared as an integer, arithmetic operations can be performed directly without needing `expr`.

```bash
declare -i var=5
var=var+1  # Now var is 6
```

### Example Script

```bash
#!/bin/bash

# C-style increment
Var=5
echo "Initial Var: $Var"
((Var++))
echo "Var after increment: $Var"

# Using expr for arithmetic
var=5
var2=$(expr $var + 1)
echo "var2: $var2"

var3=$(expr 2 + 2)
echo "var3: $var3"

# Declaring variables as integers
declare -i var=5
var=var+1
echo "var after declare and increment: $var"
```

### Notes

>1. **`expr`**:
>   - It can be less efficient and less readable compared to the C-style arithmetic.
>   - Requires careful handling of spaces around operators.
>2. **C-style Arithmetic**:
>   - More concise and efficient.
>   - Syntax similar to other programming languages like C or JavaScript.
>3. **`declare -i`**:
>   - Useful for enforcing integer arithmetic.
>   - Makes scripts more readable by explicitly showing intent to work with integers.



### 2.2. Test Command

The `test` command in Bash is used to evaluate conditional expressions. It can test for integer conditions, string conditions, and file conditions. Additionally, logical operators can be used to combine multiple conditions.

#### 2.2.1. Types of Conditions

1. **Integer Condition**

   - **Equal (`=` or `-eq`)**: Checks if two integers are equal.
   - **Not equal (`-ne`)**: Checks if two integers are not equal.
   - **Greater than (`-gt`)**: Checks if the first integer is greater than the second.
   - **Less than (`-lt`)**: Checks if the first integer is less than the second.
   - **Greater or equal (`-ge`)**: Checks if the first integer is greater than or equal to the second.
   - **Less or equal (`-le`)**: Checks if the first integer is less than or equal to the second.

   **Examples:**

   ```bash
   test 1 -eq 1  # Returns 0 (true)
   test 2 -gt 1  # Returns 0 (true)
   test 2 -lt 1  # Returns 1 (false)
   ```

   These examples are equivalent to:

   ```bash
   [ 1 -eq 1 ]   # Returns 0 (true)
   [ 2 -gt 1 ]   # Returns 0 (true)
   [ 2 -lt 1 ]   # Returns 1 (false)
   ```

2. **String Condition**

   - **Equal (`=`)**: Checks if two strings are equal.
   - **Not equal (`!=`)**: Checks if two strings are not equal.
   - **Zero characters (`-z`)**: Checks if a string is empty.
   - **Non-zero characters (`-n`)**: Checks if a string is not empty.

   **Examples:**

   ```bash
   test "hello" = "hello"  # Returns 0 (true)
   test "hello" != "world" # Returns 0 (true)
   test -z ""              # Returns 0 (true)
   test -n "hello"         # Returns 0 (true)
   ```

   These examples are equivalent to:

   ```bash
   [ "hello" = "hello" ]   # Returns 0 (true)
   [ "hello" != "world" ]  # Returns 0 (true)
   [ -z "" ]               # Returns 0 (true)
   [ -n "hello" ]          # Returns 0 (true)
   ```

3. **File Condition**

   - **File exists (`-f <file path>`)**: Checks if a file exists.
   - **Directory exists (`-d <dir path>`)**: Checks if a directory exists.
   - **File is executable (`-x <file path>`)**: Checks if a file is executable.

   **Examples:**

   ```bash
   test -f /path/to/file   # Returns 0 (true) if the file exists
   test -d /path/to/dir    # Returns 0 (true) if the directory exists
   test -x /path/to/file   # Returns 0 (true) if the file is executable
   ```

   These examples are equivalent to:

   ```bash
   [ -f /path/to/file ]    # Returns 0 (true) if the file exists
   [ -d /path/to/dir ]     # Returns 0 (true) if the directory exists
   [ -x /path/to/file ]    # Returns 0 (true) if the file is executable
   ```

#### 2.2.2. Logical Operators

Logical operators are used to combine multiple conditions.

- **Logical AND (`&&` or `-a`)**: Returns true if both conditions are true.
- **Logical OR (`||` or `-o`)**: Returns true if at least one condition is true.

**Examples:**

1. Using `&&`(AND):

   ```bash
   [ 1 -eq 1 ] && [ 2 -eq 2 ]  # Both conditions are true, returns 0 (true)
   [ 1 -eq 1 ] && [ 2 -ne 2 ]  # Second condition is false, returns 1 (false)
   ```

2. Using  `-a` (AND):

   ```bash
   [ 1 -eq 1 -a 2 -eq 2 ]      # Both conditions are true, returns 0 (true)
   [ 1 -eq 1 -a 2 -ne 2 ]      # Second condition is false, returns 1 (false)
   ```

3. Using `||`  (OR):

   ```bash
   [ 1 -eq 1 ] || [ 2 -ne 2 ]  # First condition is true, returns 0 (true)
   [ 1 -ne 1 ] || [ 2 -eq 2 ]  # Second condition is true, returns 0 (true)
   ```

4. Using `-o` (OR):

   ```bash
   [ 1 -eq 1 -o 2 -ne 2 ]      # First condition is true, returns 0 (true)
   [ 1 -ne 1 -o 2 -eq 2 ]      # Second condition is true, returns 0 (true)
   ```



### Logical Operators with `test` Command

#### Examples:

1. **Using `&&` (AND)**:

   ```bash
   # Example 1: Both conditions are true
   test 1 -eq 1 && test 2 -eq 2  # Returns 0 (true)
   # Equivalent using brackets
   [ 1 -eq 1 ] && [ 2 -eq 2 ]    # Returns 0 (true)
   
   # Example 2: Second condition is false
   test 1 -eq 1 && test 2 -ne 2  # Returns 1 (false)
   # Equivalent using brackets
   [ 1 -eq 1 ] && [ 2 -ne 2 ]    # Returns 1 (false)
   ```

2. **Using `-a` (AND)**:

   ```bash
   # Example 1: Both conditions are true
   test 1 -eq 1 -a 2 -eq 2  # Returns 0 (true)
   # Equivalent using brackets
   [ 1 -eq 1 -a 2 -eq 2 ]   # Returns 0 (true)
   
   # Example 2: Second condition is false
   test 1 -eq 1 -a 2 -ne 2  # Returns 1 (false)
   # Equivalent using brackets
   [ 1 -eq 1 -a 2 -ne 2 ]   # Returns 1 (false)
   ```

3. **Using `||` (OR)**:

   ```bash
   # Example 1: First condition is true
   test 1 -eq 1 || test 2 -ne 2  # Returns 0 (true)
   # Equivalent using brackets
   [ 1 -eq 1 ] || [ 2 -ne 2 ]    # Returns 0 (true)
   
   # Example 2: Second condition is true
   test 1 -ne 1 || test 2 -eq 2  # Returns 0 (true)
   # Equivalent using brackets
   [ 1 -ne 1 ] || [ 2 -eq 2 ]    # Returns 0 (true)
   ```

4. **Using `-o` (OR)**:

   ```bash
   # Example 1: First condition is true
   test 1 -eq 1 -o 2 -ne 2  # Returns 0 (true)
   # Equivalent using brackets
   [ 1 -eq 1 -o 2 -ne 2 ]   # Returns 0 (true)
   
   # Example 2: Second condition is true
   test 1 -ne 1 -o 2 -eq 2  # Returns 0 (true)
   # Equivalent using brackets
   [ 1 -ne 1 -o 2 -eq 2 ]   # Returns 0 (true)
   ```



#### Special Notes

- In Bash, `true` is represented as `0` and `false` is represented as `1`.

- The `$?` variable holds the exit status of the last executed command. You can use it to check the result of the `test` command.

  **Example:**

  ```bash
  test 1 -eq 1
  echo $?  # Outputs 0 (true)
  
  test 1 -ne 1
  echo $?  # Outputs 1 (false)
  ```



#### 2.3. Basic If Statement

The `if` statement in Bash is used to execute a block of code based on whether a condition is true or false. The basic syntax of an `if` statement in Bash is:

```bash
if [ condition ]; then
  # Code to execute if condition is true
fi
```

```bash
if [ 1 -eq 1 ]; then
  echo "1 is equal to 1"
fi
```

**2.3.1. Basic if Example** :

```bash
#!/bin/bash

echo "Enter a number:"
read number

if [ $number -gt 10 ]; then
    echo "The number is greater than 10."
else
    echo "The number is 10 or less."
fi
```

**Explanation**

- `#!/bin/bash`: This is the shebang line, which tells the system that this script should be run using the Bash shell.
- `echo "Enter a number:"`: This prints a prompt asking the user to enter a number.
- `read number`: This reads the user's input and stores it in the variable `number`.
- `if [ $number -gt 10 ]; then`: This checks if the number is greater than 10. The condition `[ $number -gt 10 ]` uses the `-gt` operator, which stands for "greater than".
- `echo "The number is greater than 10."`: This is executed if the condition is true.
- `else`: This is executed if the condition is false.
- `echo "The number is 10 or less."`: This is the action taken if the condition is false.
- `fi`: This marks the end of the if statement.

**2.3.2. If-Elif-Else Statement Example :**

You can also use `elif` to check multiple conditions.

```bash
#!/bin/bash

echo "Enter a number:"
read number

if [ $number -gt 10 ]; then
    echo "The number is greater than 10."
elif [ $number -eq 10 ]; then
    echo "The number is exactly 10."
else
    echo "The number is less than 10."
fi
```

>1. `#!/bin/bash` :
>
>- **Shebang Line**: This line tells the system to execute the script using the Bash shell.
>
>2. `echo "Enter a number:"`
>
>- **Prompt for Input**: This line prints the message "Enter a number:" to prompt the user to enter a number.
>
>3. `read number`
>
>- **Read User Input**: This line reads the input provided by the user and stores it in a variable named `number`.
>
>4. `if [ $number -gt 10 ]; then`
>
>- **If Condition**: This line checks if the value stored in the variable `number` is greater than 10 (`-gt` stands for "greater than"). If this condition is true, the following block of code will be executed.
>
>5. `echo "The number is greater than 10."`
>
>- **True Condition Action**: If the condition `number > 10` is true, this line will print the message "The number is greater than 10."
>
>6. `elif [ $number -eq 10 ]; then`
>
>- **Elif Condition**: If the initial `if` condition is false, this line checks if the value of `number` is equal to 10 (`-eq` stands for "equal to"). If this condition is true, the following block of code will be executed.
>
>7. `echo "The number is exactly 10."`
>
>- **True Elif Action**: If the condition `number == 10` is true, this line will print the message "The number is exactly 10."
>
>8. `else`
>
>- **Else Condition**: If none of the above conditions (`number > 10` or `number == 10`) are true, this block of code will be executed.
>
>9. `echo "The number is less than 10."`
>
>- **Else Action**: If both previous conditions are false (meaning `number < 10`), this line will print the message "The number is less than 10."
>
>10. `fi`
>
>- **End of If Statement**: This line marks the end of the `if` statement block.



**2.3.3. Using Logical Operators**

You can combine conditions using logical operators like `&&` (AND) and `||` (OR).

```bash
#!/bin/bash

echo "Enter a number:"
read number

if [ $number -gt 10 ] && [ $number -lt 20 ]; then
    echo "The number is between 10 and 20."
else
    echo "The number is outside the range of 10 to 20."
fi
```

**Explanation**

- `[ $number -gt 10 ] && [ $number -lt 20 ]`: This checks if the number is greater than 10 and less than 20.
- `echo "The number is between 10 and 20."`: This is executed if both conditions are true.

**2.3.4. Using String Comparison**

Bash also allows string comparison in if conditions.

```bash
#!/bin/bash

echo "Enter your name:"
read name

if [ "$name" == "Anas" ]; then
    echo "Hello, Anas!"
else
    echo "Hello, Belal!"
fi
```

**Explanation**

- `[ "$name" == "Anas" ]`: This checks if the value of `name` is "Anas". Note the use of double quotes to handle cases where the variable might be empty or contain spaces.
- `echo "Hello, Anas!"`: This is executed if the name is "Anas".
- `echo "Hello, Belal!"`: This is executed if the name is not "Anas".



### 2.4. Loops in Bash Scripting

#### 2.4.1.  `for` Loop

The `for` loop iterates over a list of items or a range of numbers.

#### Example 1: Iterating Over a List

```bash
#!/bin/bash

# Define a list of items
items=("apple" "banana" "cherry")

# Iterate over the list
for item in "${items[@]}"; do
    echo "Item: $item"
done
```

#### Explanation

- `items=("apple" "banana" "cherry")`: This defines an array with three elements.
- `for item in "${items[@]}"; do`: This starts the loop, iterating over each element in the array.
- `echo "Item: $item"`: This prints each item.

#### Example 2: Iterating Over a Range

```bash
#!/bin/bash

# Iterate over a range of numbers from 1 to 5
for i in {1..5}; do
    echo "Number: $i"
done
```

#### Explanation

- `for i in {1..5}; do`: This starts the loop, iterating over the numbers 1 to 5.
- `echo "Number: $i"`: This prints each number.

#### 2.4.2. `while` Loop

The `while` loop continues as long as a specified condition is true.

#### Example

```bash
#!/bin/bash

# Initialize a counter
counter=1

# Loop while the counter is less than or equal to 5
while [ $counter -le 5 ]; do
    echo "Counter: $counter"
    counter=$((counter + 1))
done
```

#### Explanation

- `counter=1`: This initializes the counter variable.
- `while [ $counter -le 5 ]; do`: This starts the loop, which continues as long as `counter` is less than or equal to 5.
- `echo "Counter: $counter"`: This prints the current value of the counter.
- `counter=$((counter + 1))`: This increments the counter by 1.

#### 2.4.3. `until` Loop

The `until` loop continues until a specified condition is true (the opposite of `while`).

#### Example

```bash
#!/bin/bash

# Initialize a counter
counter=1

# Loop until the counter is greater than 5
until [ $counter -gt 5 ]; do
    echo "Counter: $counter"
    counter=$((counter + 1))
done
```

#### Explanation

- `counter=1`: This initializes the counter variable.
- `until [ $counter -gt 5 ]; do`: This starts the loop, which continues until `counter` is greater than 5.
- `echo "Counter: $counter"`: This prints the current value of the counter.
- `counter=$((counter + 1))`: This increments the counter by 1.

#### 2.4.4. `break` and `continue` Statements

You can control loop execution with `break` and `continue`.

#### Example: Using `break`

```bash
#!/bin/bash

for i in {1..10}; do
    if [ $i -eq 5 ]; then
        break
    fi
    echo "Number: $i"
done
```

#### Explanation

- `if [ $i -eq 5 ]; then break; fi`: This breaks out of the loop when `i` equals 5.
- Only numbers 1 to 4 are printed.

#### Example: Using `continue`

```bash
#!/bin/bash

for i in {1..5}; do
    if [ $i -eq 3 ]; then
        continue
    fi
    echo "Number: $i"
done
```

#### Explanation

- `if [ $i -eq 3 ]; then continue; fi`: This skips the current iteration when `i` equals 3.
- Numbers 1, 2, 4, and 5 are printed, but not 3.



### 2.5. Array

Arrays in Bash are a powerful feature for managing lists of items. Here’s a detailed breakdown of how to work with arrays in Bash, including the various methods to access and manipulate elements:

#### 2.5.1. **Creating an Array**

You can create an array by specifying the elements inside parentheses, separated by spaces.

```bash
arr=("apple" "banana" "cherry")
```

#### 2.5.2. **Accessing Elements by Index**

Array indices in Bash start at 0. You can access elements using the `${array[index]}` syntax.

```bash
echo ${arr[0]}  # Outputs: apple
echo ${arr[1]}  # Outputs: banana
echo ${arr[2]}  # Outputs: cherry
```

#### 2.5.3. **Adding New Elements**

To add a new element to the array, specify the index and assign the value.

```bash
arr[3]="melon"  # Adds "melon" as the fourth element
```

#### 2.5.4. **Printing All Elements**

To print all elements in the array:

- **Using `${arr[@]}`**: This will expand to a list of all elements as separate arguments.

  ```bash
  echo ${arr[@]}  # Outputs: apple banana cherry melon
  ```

- **Using `"${arr[@]}"`**: Quotes preserve the elements as separate words, useful for handling elements with spaces.

  ```bash
  echo "${arr[@]}"  # Outputs: apple banana cherry melon
  ```

- **Using `${arr[\*]}`**: This combines all elements into a single string, with each element separated by the first character of the `IFS` (Internal Field Separator) variable (which defaults to a space).

  ```bash
  echo ${arr[*]}  # Outputs: apple banana cherry melon
  ```

  To change the separator, modify `IFS` before printing:

  ```bash
  IFS=','; echo "${arr[*]}"  # Outputs: apple,banana,cherry,melon
  ```

#### 2.5.5. **Getting the Number of Elements**

To find out how many elements are in the array, use the `${#array[@]}` syntax.

```bash
echo ${#arr[@]}  # Outputs: 4
```

#### 2.5.6. **Looping Through Array Elements**

You can loop through the elements using a `for` loop:

```bash
for element in "${arr[@]}"; do
  echo $element
done
```

#### 2.5.7. **Array Slicing**

You can get a subset of the array using slicing:

```bash
echo ${arr[@]:1:2}  # Outputs: banana cherry
```

- `1` is the starting index (0-based).
- `2` is the number of elements to include.

#### 2.5.8. **Removing Elements**

To remove an element from the array, use the `unset` command:

```bash
unset arr[1]  # Removes "banana" from the array
```



### 2.5.9. Difference between `@` and `*` in Quotes

```bash
#!/bin/bash

# Declare an array
numbers=("one" "two" "three")

# Print all elements using @ without quotes
echo "Using @ without quotes:"
echo ${numbers[@]}
# Output: one two three

# Print all elements using @ with quotes
echo "Using @ with quotes:"
echo "${numbers[@]}"
# Output: one two three

# Print all elements using * with quotes
echo "Using * with quotes:"
echo "${numbers[*]}"
# Output: one two three
```

- Using `&&` (logical AND) and `||` (logical OR) allows combining multiple conditions in Bash scripts.
- Quotes around `${arr[@]}` and `${arr[*]}` affect how array elements are interpreted and printed.
- `${arr[@]}` without quotes expands each element as a separate word.
- `"${arr[@]}"` with quotes expands each element as a separate quoted word.
- `"${arr[*]}"` with quotes treats the entire array as a single string, with each element separated by the first character of the IFS (Internal Field Separator).

In summary:

- `echo "${numbers[@]}"` prints each array element as a separate argument, resulting in `one two three`.
- `echo "${numbers[*]}"` prints the entire array as a single string with spaces separating the elements, also resulting in `one two three`.

Here are some more detailed examples combining arrays, logical operators, and quotes in Bash:

### 2.5.10. Arrays, Logical Operators, and Quotes Examples

```bash
#!/bin/bash

# Declare and initialize an array
fruits=("apple" "banana" "cherry")

# Print all elements without quotes
echo "All elements without quotes: ${fruits[@]}"
# Output: apple banana cherry

# Print all elements with quotes
echo "All elements with quotes: ${fruits[@]}"
# Output: apple banana cherry

# Print all elements using * with quotes
echo "All elements using * with quotes: ${fruits[*]}"
# Output: apple banana cherry

# Access individual elements
echo "First element: ${fruits[0]}"
echo "Second element: ${fruits[1]}"
echo "Third element: ${fruits[2]}"
```

- **Modifying Arrays and Using Logical Operators**

```bash
#!/bin/bash

# Declare and initialize an array
colors=("red" "green" "blue")

# Modify an element
colors[1]="yellow"

# Add a new element
colors+=("purple")

# Print all elements
echo "Modified array: ${colors[@]}"
# Output: red yellow blue purple

# Check if the first element is "red" and the second is "yellow"
if [ "${colors[0]}" = "red" ] && [ "${colors[1]}" = "yellow" ]; then
    echo "The first two colors are red and yellow."
else
    echo "The colors are different."
fi
# Output: The first two colors are red and yellow.

# Check if the array contains "purple" or "orange"
if [[ " ${colors[@]} " =~ " purple " ]] || [[ " ${colors[@]} " =~ " orange " ]]; then
    echo "The array contains purple or orange."
else
    echo "The array does not contain purple or orange."
fi
# Output: The array contains purple or orange.
```

**Example :**

```bash
#!/bin/bash

# Declare an array and initialize it with three elements
arr=("one" "two" "three")

# Add an element at index 6
arr[6]="seven"

# Print all elements with indices
for i in "${!arr[@]}"; do
    echo "Index $i: ${arr[$i]}"
done
```

**Output:**

```bash
Index 0: one
Index 1: two
Index 2: three
Index 6: seven
```

> **As you can see, indices 3, 4, and 5 are not initialized or populated with any values.**

- **Checking for Empty Indices**

You can check for and handle empty indices if needed. Here’s how you can do that:

```bash
#!/bin/bash

# Declare an array and initialize it with three elements
arr=("one" "two" "three")

# Add elements at specific indices
arr[4]="five"
arr[6]="seven"

# Print all elements with indices, indicating empty spots
for i in {0..6}; do
    if [ -z "${arr[$i]}" ]; then
        echo "Index $i: <empty>"
    else
        echo "Index $i: ${arr[$i]}"
    fi
done
```

Output:

```bash
Index 0: one
Index 1: two
Index 2: three
Index 3: <empty>
Index 4: five
Index 5: <empty>
Index 6: seven
```

> **In this example, indices 3 and 5 are shown as `<empty>`, indicating that they have not been initialized with any value.**

- **Handling Sparse Arrays**

If you want to ensure that every index between two populated indices is initialized, you can manually initialize the empty indices. Here’s an example:

```bash
#!/bin/bash

# Declare an array and initialize it with three elements
arr=("one" "two" "three")

# Add elements at specific indices
arr[4]="five"
arr[6]="seven"

# Initialize empty indices with a default value, e.g., "empty"
for i in {0..6}; do
    if [ -z "${arr[$i]}" ]; then
        arr[$i]="empty"
    fi
done

# Print all elements with indices
for i in "${!arr[@]}"; do
    echo "Index $i: ${arr[$i]}"
done
```

Output:

```bash
Index 0: one
Index 1: two
Index 2: three
Index 3: empty
Index 4: five
Index 5: empty
Index 6: seven
```

- **Example of Array Slicing in Bash**

Let's go through an example to understand array slicing better:

```bash
#!/bin/bash

# Declare an array
array=("one" "two" "three" "four" "five" "six" "seven")

# Slice the array from index 2, taking 3 elements
slice=("${array[@]:2:3}")

# Print the sliced array elements
echo "Sliced array: ${slice[@]}"
```

Output:

```bash
Sliced array: three four five
```

In this example, the slicing starts at index 2 and takes 3 elements from the array, resulting in the subset `("three" "four" "five")`.

### Array Slicing Syntax

- `${array[@]:start:length}`: Extracts `length` elements starting from `start` index.
- `${array[@]:start}`: Extracts all elements starting from `start` index.

#### Extracting All Elements from a Specific Index

```bash
#!/bin/bash

# Declare an array
array=("one" "two" "three" "four" "five" "six" "seven")

# Slice the array from index 4 to the end
slice=("${array[@]:4}")

# Print the sliced array elements
echo "Sliced array from index 4: ${slice[@]}"
```

Output:

```bash
Sliced array from index 4: five six seven
```

- **Extracting a Subset with Specified Length**

```bash
#!/bin/bash

# Declare an array
array=("one" "two" "three" "four" "five" "six" "seven")

# Slice the array from index 1, taking 2 elements
slice=("${array[@]:1:2}")

# Print the sliced array elements
echo "Sliced array from index 1, length 2: ${slice[@]}"
```

Output:

```
Sliced array from index 1, length 2: two three
```

- **Handling Sparse Arrays with Slicing**

Even when dealing with sparse arrays, slicing works similarly, but be aware that intermediate unset indices might affect your operations.

```bash
#!/bin/bash

# Declare a sparse array
sparse_array=( "first" "second" "third" )
sparse_array[10]="eleventh"

# Slice the sparse array from index 2, taking 5 elements
slice=("${sparse_array[@]:2:5}")

# Print the sliced sparse array elements
echo "Sliced sparse array from index 2, length 5: ${slice[@]}"
```

Output:

```bash
Sliced sparse array from index 2, length 5: third eleventh
```

> Note: In this example, since there are only two elements beyond index 2, the output contains the actual elements at those indices, skipping the uninitialized (sparse) parts.

##### Practical Usage: Array Slicing in Scripts

> Array slicing is particularly useful in scripts where you need to process only a part of an array. Here’s a practical example:

```bash
#!/bin/bash

# Declare an array of filenames
files=("file1.txt" "file2.txt" "file3.txt" "file4.txt" "file5.txt")

# Process only the first 3 files
slice=("${files[@]:0:3}")

# Iterate over the sliced array and process each file
for file in "${slice[@]}"; do
    echo "Processing $file"
    # Add your processing logic here
done
```

**Output:**

```bash
Processing file1.txt
Processing file2.txt
Processing file3.txt
```

**Example: Printing Indices of Array Elements**

```bash
#!/bin/bash

# Declare an array
array=("one" "two" "three" "four" "five" "six" "seven")

# Print array elements and their indices
echo "Array elements and their indices:"
for index in "${!array[@]}"; do
    echo "Index: $index, Value: ${array[$index]}"
done
```

**Output:**

```bash
Array elements and their indices:
Index: 0, Value: one
Index: 1, Value: two
Index: 2, Value: three
Index: 3, Value: four
Index: 4, Value: five
Index: 5, Value: six
Index: 6, Value: seven
```

**Example: Handling Sparse Arrays**

Let's consider a sparse array where some indices are not initialized.

```bash
#!/bin/bash

# Declare a sparse array
sparse_array=( "first" "second" "third" )
sparse_array[10]="eleventh"

# Print array elements and their indices
echo "Sparse array elements and their indices:"
for index in "${!sparse_array[@]}"; do
    echo "Index: $index, Value: ${sparse_array[$index]}"
done
```

**Output:**

```bash
Sparse array elements and their indices:
Index: 0, Value: first
Index: 1, Value: second
Index: 2, Value: third
Index: 10, Value: eleventh
```

In this example, the sparse array has elements at indices 0, 1, 2, and 10. The loop prints only the initialized indices and their values.

- **Array Slicing with Indices**

> When you slice an array, you can also print the indices of the sliced elements.

```bash
#!/bin/bash

# Declare an array
array=("one" "two" "three" "four" "five" "six" "seven")

# Slice the array from index 2, taking 3 elements
slice=("${array[@]:2:3}")

# Print the sliced array elements and their indices
echo "Sliced array elements and their indices:"
for index in "${!slice[@]}"; do
    echo "Index: $index, Value: ${slice[$index]}"
done
```

**Output:**

```bash
Sliced array elements and their indices:
Index: 0, Value: three
Index: 1, Value: four
Index: 2, Value: five
```

Note that the indices here are relative to the sliced array.

**Example: Retrieving Indices of a Sliced Sparse Array**

```bash
#!/bin/bash

# Declare a sparse array
sparse_array=( "first" "second" "third" )
sparse_array[10]="eleventh"

# Slice the sparse array from index 2, taking 5 elements
slice=("${sparse_array[@]:2:5}")

# Print the sliced sparse array elements and their indices
echo "Sliced sparse array elements and their indices:"
for index in "${!slice[@]}"; do
    echo "Index: $index, Value: ${slice[$index]}"
done
```

Output:

```bash
Sliced sparse array elements and their indices:
Index: 0, Value: third
Index: 1, Value: eleventh
```

> **The indices are relative to the sliced array, not the original array.**

- `${!array[@]}` retrieves the indices of elements in the array.
- Looping over `${!array[@]}` allows you to access both indices and values.
- This method works for both regular and sparse arrays.
- When slicing arrays, the indices in the sliced array are relative to the slice, not the original array.



#### **Associative array** : access the values by their key

- **Declaration:**

> To declare an associative array, use the `declare` keyword with `-A` option:

```bash
declare -A myArray
```

- **Initialization:**

> You can initialize an associative array by assigning key-value pairs:

```bash
myArray[key1]=value1
myArray[key2]=value2
```

##### Accessing Values:

> To access a value, use the key inside curly braces `${}`:

```bash
echo ${myArray[key1]}   # Output: value1
```

**Example:**

```bash
#!/bin/bash

# Declare an associative array
declare -A fruits

# Initialize the array with some values
fruits=([apple]="red" [banana]="yellow" [grape]="purple")

# Accessing values
echo "Color of apple: ${fruits[apple]}"     # Output: Color of apple: red
echo "Color of banana: ${fruits[banana]}"   # Output: Color of banana: yellow

# Adding a new key-value pair
fruits[orange]="orange"

# Iterating over keys
echo "All fruits:"
for key in "${!fruits[@]}"
do
  echo "$key - ${fruits[$key]}"
done
```

**Notes:**

>- **Declaration**: Use `declare -A` to create an associative array.
>- **Initialization**: Assign values using `[key]=value` syntax.
>- **Access**: Use `${array[key]}` to retrieve values.
>- **Adding**: Add new key-value pairs by assigning them directly.
>- **Iterating**: Use `"${!array[@]}"` to iterate over keys, and `${array[key]}` to get corresponding values.
>
>

- **Deleting an Item:**

> To delete a specific key-value pair from an associative array, use the `unset` command followed by the key in curly braces `${}`:

```bash
unset myArray[key1]
```

For example, if `myArray` is your associative array:

```bash
declare -A myArray
myArray=([key1]="value1" [key2]="value2")

# Delete key1 from myArray
unset myArray[key1]
```

- **Deleting the Whole Array:**

> To delete all elements in an associative array, you can unset the entire array variable:

```bash
unset myArray
```

> This removes the entire associative array `myArray` and all its contents from memory.

- **Example:**

Here’s a complete example demonstrating both deletion of an item and the entire array:

```bash
#!/bin/bash

# Declare and initialize an associative array
declare -A myArray
myArray=([key1]="value1" [key2]="value2" [key3]="value3")

# Print current array contents
echo "Before deletion:"
for key in "${!myArray[@]}"
do
  echo "$key - ${myArray[$key]}"
done

# Delete key2 from myArray
unset myArray[key2]

# Print array contents after deletion
echo "After deleting key2:"
for key in "${!myArray[@]}"
do
  echo "$key - ${myArray[$key]}"
done

# Delete the entire array
unset myArray

# Check if array is empty
if [ ${#myArray[@]} -eq 0 ]; then
  echo "Array 'myArray' is empty."
else
  echo "Array 'myArray' still exists."
fi
```

##### Notes:

>- **`unset` Command**: Use `unset` followed by the array name and key to delete individual elements (`unset myArray[key]`) or the entire array (`unset myArray`).
>- **Checking Array Existence**: After unsetting an array, you can check if it exists using `[ ${#array[@]} -eq 0 ]` condition
>





### 2.6. Arguments in Bash scripting

### Accessing Arguments:

1. **Using Positional Parameters:**
   - **`$1`, `$2`, ...**: These variables represent the first, second, etc., positional parameters passed to the script or function.
   - **`$0`**: Represents the script's name or function name.
   - **`$@`**: Represents all the positional parameters as separate quoted strings.
   - **`$\*`**: Represents all the positional parameters as a single quoted string.

**Example:**

Let's say you have a Bash script named `args_example.sh`:

```bash
#!/bin/bash

echo "Script name: $0"
echo "First argument: $1"
echo "Second argument: $2"
echo "All arguments (separate strings): $@"
echo "All arguments (single string): $*"

# Loop through all arguments
echo "Iterating through arguments:"
for arg in "$@"
do
    echo "$arg"
done
```

**Running the Script:** If you run the script with arguments:

```bash
bash args_example.sh arg1 arg2 arg3
```

You should see the following output:

```bash
Script name: args_example.sh
First argument: arg1
Second argument: arg2
All arguments (separate strings): arg1 arg2 arg3
All arguments (single string): arg1 arg2 arg3
Iterating through arguments:
arg1
arg2
arg3
```

**Explanation :**

>- **`$0`**: Outputs the script name (`args_example.sh` in this case).
>- **`$1`**, **`$2`**: Outputs the first and second arguments (`arg1` and `arg2`).
>- **`$@`**: Outputs all arguments as separate strings (`arg1 arg2 arg3`).
>- **`$\*`**: Outputs all arguments as a single string (`arg1 arg2 arg3`).
>- **`for arg in "$@"`:** Iterates through each argument separately and prints each one

**Notes :**

>- When accessing arguments, it's generally safer to use `"$@"` within double quotes to preserve arguments with spaces or special characters as single entities.
>- The number of arguments (`$#`) gives you the count of positional parameters.
>

### 2.7. Case in Bash Scripting

The `case` statement in Bash is a conditional statement that allows you to execute different blocks of code based on the value of a variable. It's particularly useful when you have multiple conditions to check.

```bash
case variable in
    pattern1)
        # Commands to execute if variable matches pattern1
        ;;
    pattern2)
        # Commands to execute if variable matches pattern2
        ;;
    pattern3|pattern4)
        # Commands to execute if variable matches pattern3 or pattern4
        ;;
    *)
        # Default case if none of the above patterns match
        # Optional: commands to execute for default case
        ;;
esac
```

**Explanation :**

>- **`case variable in`**: Begins the `case` statement where `variable` is the variable whose value you want to check against different patterns.
>- **`pattern1)`**: Specifies a pattern to match against the value of `variable`. If `variable` matches `pattern1`, the commands between `)` and `;;` are executed.
>- **`;;`**: Terminates each branch of the `case` statement. Use `;;` to separate each pattern and its corresponding commands.
>- **`pattern3|pattern4)`**: You can use `|` to specify multiple patterns that should execute the same commands.
>- **`\*)`**: The `*)` pattern matches anything that doesn't match any previous patterns. It serves as the default case if no other patterns match.
>- **`esac`**: Ends the `case` statement

**Example :**

```bash
#!/bin/bash

fruit="apple"

case $fruit in
    "apple")
        echo "Selected fruit is apple"
        ;;
    "banana" | "orange")
        echo "Selected fruit is either banana or orange"
        ;;
    "grape")
        echo "Selected fruit is grape"
        ;;
    *)
        echo "Unknown fruit"
        ;;
esac
```

**Output : Running the above script with `fruit="apple"` will output :**

```bash
Selected fruit is apple 
```

Changing `fruit="banana"` would output:

```bash
Selected fruit is either banana or orange
```

And setting `fruit="kiwi"` would output:

```bash
Unknown fruit
```

### Notes:

>- Each pattern in `case` can contain shell glob patterns (`*`, `?`, `[...]`) to match variable values.
>- The `case` statement is useful when you have multiple conditions to check against a single variable, offering a cleaner alternative to nested `if` statements in such scenarios.
>- Ensure each pattern in `case` is terminated with `;;` to correctly separate cases.
>



**Example Script :**

```bash
#!/bin/bash

# Prompt the user for input
echo "Select an option:"
echo "1. Display date"
echo "2. Display current directory"
echo "3. Display system uptime"
echo "4. Exit"

# Read user input
read -p "Enter your choice: " choice

# Case statement to process user input
case $choice in
    1)
        echo "Today's date is: $(date +%Y-%m-%d)"
        ;;
    2)
        echo "Current directory: $(pwd)"
        ;;
    3)
        echo "System uptime:"
        uptime
        ;;
    4)
        echo "Exiting..."
        exit 0
        ;;
    *)
        echo "Invalid choice. Please enter a number between 1 and 4."
        ;;
esac
```

**Explanation :**

>1. **Prompting for Input**: The script begins by displaying a menu of options using `echo` statements.
>2. **Reading User Input**: `read -p "Enter your choice: " choice` reads user input and stores it in the variable `choice`.
>3. **`case` Statement**: The `case` statement checks the value of `choice` against different patterns (`1`, `2`, `3`, `4`, and `*` for invalid input).
>4. **Processing Options**: Each pattern in the `case` statement corresponds to a menu option:
>   - Option `1`: Displays today's date using `date +%Y-%m-%d`.
>   - Option `2`: Displays the current directory using `pwd`.
>   - Option `3`: Displays system uptime using the `uptime` command.
>   - Option `4`: Exits the script gracefully with `exit 0`.
>5. **Default Case (`\*`)**: If the user enters any value other than `1`, `2`, `3`, or `4`, the script outputs an error message indicating an invalid choice

**Running the Script :**

When you run the script, it will display the menu and prompt you to enter a number. Depending on your input, it will execute the corresponding command or display an error for invalid choices.

**Notes :**

>- Ensure that your `read` statement is properly formatted to capture user input. The `-p` option is used to display a prompt message.
>- Use the `case` statement to handle multiple conditions based on the value of a variable (`choice` in this case).
>- Always include a `*)` default case to handle unexpected input gracefully.
>



### 2.8. Select in bash

The `select` statement in Bash is used to create simple menus. It provides a way to display a list of options and prompt the user to choose one. 

```bash
select variable in option1 option2 option3 ...
do
    # Commands to execute based on user's selection
    case $variable in
        option1)
            # Commands for option1
            ;;
        option2)
            # Commands for option2
            ;;
        option3)
            # Commands for option3
            ;;
        *)
            # Default case
            echo "Invalid option. Please choose again."
            ;;
    esac
done
```

**Explanation :**

>- **`select variable in option1 option2 option3 ...`**: This line initiates a menu where `option1`, `option2`, `option3`, etc., are presented as selectable choices. The user selects one of these options, and the value of `variable` is set to the selected option.
>- **`do`**: Begins the block of commands to execute based on the user's selection.
>- **`case $variable in ... esac`**: Inside the `do` block, a `case` statement is used to execute commands corresponding to the selected option (`$variable`).
>- **`option1)`**: Each option listed after `in` is a pattern that `variable` can match. When the user selects an option, the corresponding pattern executes the commands under that pattern.
>- **`\*)`**: The default case handles any input that doesn't match any specified option.
>- **`done`**: Ends the `select` loop.
>



```bash
#!/bin/bash

# Define a list of options
options=("Display date" "Display current directory" "Display system uptime" "Exit")

# Display a menu using select
PS3="Select an option (enter the number): "
select choice in "${options[@]}"
do
    case $REPLY in
        1)
            echo "Today's date is: $(date +%Y-%m-%d)"
            ;;
        2)
            echo "Current directory: $(pwd)"
            ;;
        3)
            echo "System uptime:"
            uptime
            ;;
        4)
            echo "Exiting..."
            break
            ;;
        *)
            echo "Invalid option. Please choose again."
            ;;
    esac
done
```

**Explanation :**

>1. **`options=("Display date" "Display current directory" "Display system uptime" "Exit")`**: Defines an array `options` containing menu items.
>2. **`PS3="Select an option (enter the number): "`**: Sets the prompt message displayed before the menu in `select`.
>3. **`select choice in "${options[@]}"`**: Displays the menu defined by `options`, prompting the user to select an option by entering the corresponding number. The selected option is stored in the variable `choice`, and `$REPLY` contains the number the user entered.
>4. **`case $REPLY in ... esac`**: Checks the value of `$REPLY` (the user's numeric selection) against different patterns (`1`, `2`, `3`, `4`, and `*` for invalid input).
>5. **Handling Selections**:
>   - Option `1`: Displays today's date using `date +%Y-%m-%d`.
>   - Option `2`: Displays the current directory using `pwd`.
>   - Option `3`: Displays system uptime using the `uptime` command.
>   - Option `4`: Exits the script gracefully with `break`.
>6. **Default Case (`\*`)**: If the user enters an invalid option (not `1`, `2`, `3`, or `4`), it prints an error message prompting the user to choose again.

**Running the Script :** When you run this script:

- It will display a menu with options numbered from `1` to `4`.
- The user enters a number corresponding to their choice.
- Based on the user's input, the script executes the corresponding action (display date, current directory, system uptime, or exit).
- The menu is displayed again after each action until the user chooses to exit (`4`).

**Notes :**

>- `select` simplifies the creation of interactive menus in Bash scripts, improving user interaction and script usability.
>
>- Ensure that your script handles user input validation appropriately, especially when expecting numerical input corresponding to menu options.
>



### 2.9. Strings in Bash

##### 2.9.1. String Assignment

```bash
#!/bin/bash

str="Hello, World!"
echo $str
```

##### 2.9.2. String Concatenation

```bash
#!/bin/bash

str1="Hello"
str2="World"
combined="$str1, $str2!"
echo $combined
```

##### 2.9.3. String Length

```bash
#!/bin/bash

str="Hello, World!"
length=${#str}
echo "Length of the string is: $length"
```

##### 2.9.4. Substring Extraction

```bash
#!/bin/bash

str="Hello, World!"
substring=${str:7:5}  # Extract 5 characters starting from index 7
echo $substring
```

##### 2.9.5. String Replacement

```bash
#!/bin/bash

str="Hello, World!"
new_str=${str/World/Bash}
echo $new_str
```

##### 2.9.6. String Comparison

```bash
#!/bin/bash

str1="Hello"
str2="World"

if [ "$str1" == "$str2" ]; then
    echo "Strings are equal"
else
    echo "Strings are not equal"
fi
```

##### 2.9.7. Checking if String Contains Substring

```bash
#!/bin/bash

str="Hello, World!"

if [[ $str == *"World"* ]]; then
    echo "The string contains 'World'"
else
    echo "The string does not contain 'World'"
fi
```

##### 2.9.8. String Case Conversion

- **Convert to Upper Case**

```bash
#!/bin/bash

str="Hello, World!"
uppercase=$(echo "$str" | tr '[:lower:]' '[:upper:]')
echo $uppercase
```

- **Convert to Lower Case**

```bash
#!/bin/bash

str="Hello, World!"
lowercase=$(echo "$str" | tr '[:upper:]' '[:lower:]')
echo $lowercase
```

##### 2.9.9. Splitting Strings

```bash
#!/bin/bash

str="apple,banana,cherry"
IFS=',' read -r -a array <<< "$str"

for element in "${array[@]}"; do
    echo $element
done
```

##### 2.9.10. Appending to Strings

```bash
#!/bin/bash

str="Hello"
str+=" World!"
echo $str
```

**Combining Examples in a Script**

```bash
#!/bin/bash

# String assignment
greeting="Hello"
name="Alice"

# String concatenation
welcome_message="$greeting, $name!"

# String length
length=${#welcome_message}

# Substring extraction
extracted=${welcome_message:7:5}

# String replacement
new_message=${welcome_message/Alice/Bob}

# Checking if string contains substring
if [[ $welcome_message == *"Alice"* ]]; then
    contains="contains 'Alice'"
else
    contains="does not contain 'Alice'"
fi

# Convert to upper case
uppercase=$(echo "$welcome_message" | tr '[:lower:]' '[:upper:]')

# Convert to lower case
lowercase=$(echo "$welcome_message" | tr '[:upper:]' '[:lower:]')

# Splitting strings
fruits="apple,banana,cherry"
IFS=',' read -r -a fruit_array <<< "$fruits"

# Output results
echo "Welcome message: $welcome_message"
echo "Length: $length"
echo "Extracted substring: $extracted"
echo "New message: $new_message"
echo "Contains check: $contains"
echo "Uppercase: $uppercase"
echo "Lowercase: $lowercase"
echo "Fruits:"

for fruit in "${fruit_array[@]}"; do
    echo " - $fruit"
done
```

**Explanation :**

>- **String Assignment and Concatenation**: Assigns and combines strings.
>- **String Length**: Determines the length of a string.
>- **Substring Extraction**: Extracts a part of a string.
>- **String Replacement**: Replaces part of a string.
>- **String Contains Check**: Checks if a string contains a substring.
>- **String Case Conversion**: Converts strings to upper and lower case.
>- **Splitting Strings**: Splits a string into an array using a delimiter.
>- **Appending to Strings**: Appends text to a string.
>



### 2.9. Functions in Bash

Functions in Bash allow you to encapsulate a series of commands in a reusable and organized manner. You can define a function using different syntaxes. Here are the three syntaxes you've mentioned:

1. **Standard Syntax:**

   ```bash
   fun() {
     # commands
   }
   ```

2. **`function` Keyword Syntax with Parentheses:**

   ```bash
   function fun() {
     # commands
   }
   ```

3. **`function` Keyword Syntax without Parentheses:**

   ```bash
   function fun {
     # commands
   }
   ```

**Explanation and Differences**

1. **Standard Syntax:**

   ```bash
   fun() {
     # commands
   }
   ```

   >- This is the most common way to define a function in Bash.
   >- The function name `fun` is followed by parentheses `()` and a block of commands enclosed in curly braces `{}`.
   >- Parentheses are used, but they don't take any parameters directly in Bash (parameters are accessed via positional parameters like `$1`, `$2`, etc. inside the function).
   >- This syntax is supported by all versions of Bash.

2. **`function` Keyword Syntax with Parentheses:**

   ```bash
   function fun() {
     # commands
   }
   ```

   >- This syntax explicitly uses the `function` keyword.
   >- The function name `fun` is followed by parentheses `()` and a block of commands enclosed in curly braces `{}`.
   >- The `function` keyword is mostly a legacy syntax from older shells and is not necessary in modern Bash scripts.
   >- It is more a matter of style or personal preference whether to use it or not.

3. **`function` Keyword Syntax without Parentheses:**

   ```bash
   function fun {
     # commands
   }
   ```

   >- This syntax uses the `function` keyword but omits the parentheses `()`.
   >- This is also valid in Bash and functions the same way as the previous two definitions.
   >- This style is less common and might be less readable to someone used to seeing the parentheses



#### 2.9.1. Basic Function Definition

```bash
#!/bin/bash

# Define a function
greet() {
    echo "Hello, World!"
}

# Call the function
greet
```

#### 2.9.2. Function with Arguments

```bash
#!/bin/bash

# Define a function with arguments
greet() {
    local name=$1
    echo "Hello, $name!"
}

# Call the function with an argument
greet "Alice"
```

#### 2.9.3. Function Returning a Value

Bash functions can't return values like other programming languages, but you can use `echo` to output a value or `return` to set an exit status.

#### Using `echo` to Return a Value

```bash
#!/bin/bash

# Define a function that returns a value using echo
add() {
    local a=$1
    local b=$2
    echo $((a + b))
}

# Capture the function's output
result=$(add 3 4)
echo "The sum is: $result"
```

#### Using `return` to Set an Exit Status

```bash
#!/bin/bash

# Define a function that returns a value using return
is_even() {
    local num=$1
    if (( num % 2 == 0 )); then
        return 0
    else
        return 1
    fi
}

# Call the function and check the exit status
is_even 4
if [ $? -eq 0 ]; then
    echo "The number is even."
else
    echo "The number is odd."
fi
```

#### 2.9.4. Function with Local Variables

```bash
#!/bin/bash

# Define a function with local variables
calculate_area() {
    local length=$1
    local width=$2
    local area=$((length * width))
    echo "The area is: $area"
}

# Call the function with arguments
calculate_area 5 3
```

#### 2.9.5. Recursive Function

```bash
#!/bin/bash

# Define a recursive function to calculate factorial
factorial() {
    local n=$1
    if [ $n -le 1 ]; then
        echo 1
    else
        local prev=$(factorial $((n - 1)))
        echo $((n * prev))
    fi
}

# Call the recursive function
result=$(factorial 5)
echo "The factorial of 5 is: $result"
```

#### 2.9.6. Function Library (Multiple Functions)

```bash
#!/bin/bash

# Define multiple functions
greet() {
    local name=$1
    echo "Hello, $name!"
}

calculate_sum() {
    local a=$1
    local b=$2
    echo $((a + b))
}

is_prime() {
    local num=$1
    if [ $num -le 1 ]; then
        echo "$num is not a prime number."
        return
    fi

    for ((i=2; i<num; i++)); do
        if ((num % i == 0)); then
            echo "$num is not a prime number."
            return
        fi
    done

    echo "$num is a prime number."
}

# Call the functions
greet "Alice"
sum=$(calculate_sum 3 7)
echo "The sum is: $sum"
is_prime 7
```

#### 2.9.7. Function with Default Arguments

```bash
#!/bin/bash

# Define a function with default arguments
greet() {
    local name=${1:-Guest}
    echo "Hello, $name!"
}

# Call the function without an argument
greet

# Call the function with an argument
greet "Alice"
```

#### Using Functions from Another Script

> You can source another script to use its functions.

#### main.sh

```bash
#!/bin/bash

# Source the functions script
source functions.sh

# Call the function defined in functions.sh
greet "Bob"
```

#### functions.sh

```bash
#!/bin/bash

greet() {
    local name=$1
    echo "Hello, $name!"
}
```

#### Notes:

>- **Local Variables**: Use `local` to define variables within a function to avoid affecting the global scope.
>- **Arguments**: Access function arguments using `$1`, `$2`, etc.
>- **Return Values**: Use `echo` to return values from a function. Use `return` to set the exit status of the function.
>- **Recursion**: Functions can call themselves, but be cautious of infinite loops.

