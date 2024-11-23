#include <stdio.h>

int main() {
    int num1, num2;
    char op;
    
    printf("Enter the first number: ");
    scanf("%d", &num1);
    printf("Enter the second number: ");
    scanf("%d", &num2);
    printf("Enter the operator (+, -, *, /): ");
    scanf(" %c", &op);
    
    switch(op) {
        case '+':
            printf("Result: %d\n", num1 + num2);
            break;
        case '-':
            printf("Result: %d\n", num1 - num2);
            break;
        case '*':
            printf("Result: %d\n", num1 * num2);
            break;
        case '/':
            if(num2 != 0)
                printf("Result: %d\n", num1 / num2);
            else
                printf("Error: Division by zero\n");
            break;
        default:
            printf("Error: Invalid operator\n");
    }
    
    return 0;
}

