#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    pid_t pid;

    printf("Parent process (PID: %d)\n", getpid());

    // Create a child process
    pid = fork();
    if (pid == 0) {
        // Child process
        printf("Child process created (PID: %d)\n", getpid());
        printf("Child process is running...\n");
        while(1); // Infinite loop to keep the child process running
        exit(0);
    } else if (pid > 0) {
        // Parent process
        printf("Parent process created child with PID: %d\n", pid);
        printf("In another terminal, use 'kill %d' to terminate the child process.\n", pid);

        // Wait for child to exit and reap its status
        wait(NULL);
        printf("################################################\n");
        printf("** The child process killed **\n");
	printf("################################################\n");
        printf("Parent process exiting (PID: %d)\n", getpid());
    } else {
        // Error occurred
        perror("fork");
        return 1;
    }

    return 0;
}

