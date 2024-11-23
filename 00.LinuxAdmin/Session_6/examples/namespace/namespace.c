#define _GNU_SOURCE  // Enable GNU extensions to use the clone function
#include <stdio.h>   // Standard I/O functions like printf
#include <stdlib.h>  // Standard library functions like exit
#include <unistd.h>  // Unix standard functions like getpid
#include <sched.h>   // Scheduler functions and macros like clone
#include <sys/wait.h>// Wait for process termination

// Allocate a stack for the child process with a size of 1 MB (1048576 bytes)
// The stack is declared as static to ensure its scope is limited to this file
static char child_stack[1048576];

// Define a function to be executed by the child process
static int child_fn() {
    // Print the process ID of the child process
    printf("PID: %ld\n", (long)getpid());
    // Return 0 to indicate successful execution of the child function
    return 0;
}

int main() {
    // Create a new process using the clone function
    // clone arguments:
    // 1. child_fn: The function to be executed by the child process
    // 2. child_stack + 1048576: Pointer to the top of the stack for the child process
    //    Note that stacks grow downward, so we pass the end of the allocated stack space
    // 3. CLONE_NEWPID | SIGCHLD: Flags for creating a new PID namespace and sending a SIGCHLD signal on termination
    // 4. NULL: No additional arguments for the child function
    pid_t child_pid = clone(child_fn, child_stack + 1048576, CLONE_NEWPID | SIGCHLD, NULL);

    // Print the process ID of the cloned process
    printf("clone() = %ld\n", (long)child_pid);

    // Wait for the child process to terminate
    // waitpid arguments:
    // 1. child_pid: The process ID of the child process to wait for
    // 2. NULL: No status information is requested
    // 3. 0: No options are specified
    waitpid(child_pid, NULL, 0);

    // Return 0 to indicate successful execution of the main function
    return 0;
}

