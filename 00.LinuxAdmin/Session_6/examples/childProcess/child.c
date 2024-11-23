#include <stdio.h>      // For printf
#include <stdlib.h>     // For exit, atoi
#include <unistd.h>     // For fork, execl

// Define the path to the bash shell
#define BASH "/bin/bash"

// Define the number of programs in the array
#define NR_PROGS 2

// Array of commands to execute
char * arr_prog[NR_PROGS] = {
    [0] = "ls -l",    // List directory contents
    [1] = "date",     // Display the current date and time
};

int main(int argc, const char *argv[])
{
    // Check if the required arguments are provided
    if (argc < 3) {
        // Print usage message if not enough arguments
        printf("Usage: %s <parent_index> <child_index>\n", argv[0]);
        return -1; // Return with an error code
    }

    pid_t pid; // Declare a variable to store the process ID

    pid = fork(); // Create a new process by forking
    if (pid == 0) {
        // Child process
        int child_index = atoi(argv[2]); // Convert the third argument to an integer
        if (child_index >= NR_PROGS) {
            // Check if the index is valid
            printf("Invalid child index\n");
            return -1; // Return with an error code
        }
        // Print the command the child process will execute
        printf("\nChild process will execute %s\n", arr_prog[child_index]);
        // Replace the child process with the specified command
        execl(BASH, BASH, "-c", arr_prog[child_index], NULL);
        exit(0); // Exit the child process
    }
    else if (pid < 0) {
        // Fork failed
        printf("The fork failed\n");
        return -1; // Return with an error code
    }
    else {
        // Parent process
        int parent_index = atoi(argv[1]); // Convert the second argument to an integer
        if (parent_index >= NR_PROGS) {
            // Check if the index is valid
            printf("Invalid parent index\n");
            return -1; // Return with an error code
        }
        // Print the command the parent process will execute
        printf("\nThe parent will execute %s\n", arr_prog[parent_index]);
        // Replace the parent process with the specified command
        execl(BASH, BASH, "-c", arr_prog[parent_index], NULL);
        exit(0); // Exit the parent process
    }

    return 0; // Return from main
}

