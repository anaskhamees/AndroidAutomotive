#include <stdio.h>      // For printf
#include <stdlib.h>     // For exit
#include <unistd.h>     // For fork

int main(int argc, const char *argv[]) {
    int my_var = 5;         // Declare an integer variable and initialize it to 5
    pid_t pid = fork();     // Create a new process by forking

    if (pid == 0) {         // If pid is 0, this is the child process
        my_var += 3;        // Increment my_var by 3 in the child process
        printf("Child copy my_var %d\n", my_var);  // Print the value of my_var in the child process
    } else if (pid > 0) {   // If pid is greater than 0, this is the parent process
        my_var++;           // Increment my_var by 1 in the parent process
        printf("Parent copy my_var %d\n", my_var); // Print the value of my_var in the parent process
    } else {                // If pid is less than 0, fork failed
        printf("Fork failed\n"); // Print an error message
        return 1;           // Return with an error code
    }

    return 0;               // Return from main
}

