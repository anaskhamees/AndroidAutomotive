### Explanation:

1. **Include Necessary Headers**:
   - `#include <stdio.h>`: For input and output functions like `printf`.
   - `#include <stdlib.h>`: For standard library functions like `exit` and `atoi`.
   - `#include <unistd.h>`: For Unix standard functions like `fork` and `execl`.
2. **Define Constants**:
   - `#define BASH "/bin/bash"`: Defines the path to the bash shell.
   - `#define NR_PROGS 2`: Defines the number of programs in the `arr_prog` array.
3. **Initialize an Array of Commands**:
   - `char * arr_prog[NR_PROGS]`: Declares an array of command strings.
   - `arr_prog[0] = "ls -l"`: Sets the first command to `ls -l`.
   - `arr_prog[1] = "date"`: Sets the second command to `date`.
4. **Main Function**:
   - `int main(int argc, const char *argv[])`: Defines the main function that takes command-line arguments.
   - `if (argc < 3)`: Checks if there are at least three arguments. If not, it prints a usage message and returns an error code.
5. **Forking a Process**:
   - `pid_t pid`: Declares a variable to store the process ID.
   - `pid = fork()`: Creates a new process. The return value of `fork` is:
     - `0` in the child process.
     - Negative if the fork failed.
     - Positive in the parent process.
6. **Child Process**:
   - `if (pid == 0)`: This block is executed by the child process.
     - `int child_index = atoi(argv[2])`: Converts the third argument to an integer.
     - `if (child_index >= NR_PROGS)`: Checks if the index is valid. If not, it prints an error message and returns an error code.
     - `printf("\nChild process will execute %s\n", arr_prog[child_index])`: Prints the command the child process will execute.
     - `execl(BASH, BASH, "-c", arr_prog[child_index], NULL)`: Replaces the child process with the specified command.
     - `exit(0)`: Exits the child process after executing the command.
7. **Fork Failure**:
   - `else if (pid < 0)`: This block is executed if the fork failed.
     - ` printf("The fork failed\n")`: Prints an error message.
     - `return -1`: Returns an error code.
8. **Parent Process**:
   - `else` : This block is executed by the parent process.
     - `int parent_index = atoi(argv[1])`: Converts the second argument to an integer.
     - `if (parent_index >= NR_PROGS)`: Checks if the index is valid. If not, it prints an error message and returns an error code.
     - `printf("\nThe parent will execute %s\n", arr_prog[parent_index])`: Prints the command the parent process will execute.
     - `execl(BASH, BASH, "-c", arr_prog[parent_index], NULL)`: Replaces the parent process with the specified command.
     - `exit(0)`: Exits the parent process after executing the command.
9. **Return from Main**:
   - `return 0`: Returns from the main function. Although this line will never be reached because both the parent and child processes call `exit`.

