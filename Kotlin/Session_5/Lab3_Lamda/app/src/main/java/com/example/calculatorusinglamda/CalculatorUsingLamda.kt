package com.example.calculatorusinglamda

typealias IntToInt = (Int, Int) -> Int

// Define operations using lambda expressions
val sum: IntToInt = { no1, no2 -> no1 + no2 }
val sub: IntToInt = { num1, num2 -> num1 - num2 }
val mul: IntToInt = { num1, num2 -> num1 * num2 }
val div: IntToInt = { num1, num2 ->
    if (num2 != 0) {
        num1 / num2
    } else {
        println("Can't divide by zero")
        0
    }
}

// Higher-order function to perform the calculation
fun calculate(num1: Int, num2: Int, operation: IntToInt): Int {
    return operation(num1, num2)
}

// Main function to execute the calculator
fun main() {
    // Prompt the user for input
    println("Enter first number:")
    val firstInput = readLine()
    val num1 = firstInput?.toIntOrNull() ?: return println("Invalid input for first number")

    println("Enter second number:")
    val secondInput = readLine()
    val num2 = secondInput?.toIntOrNull() ?: return println("Invalid input for second number")

    // Prompt the user to select an operation
    println("Select an operation: (+) Sum (-) Subtraction (x) Multiplication (/) Division")
    val operationInput = readLine()?:println("NULL INPUT ")

    val result = when (operationInput) {
        "+" -> calculate(num1, num2, sum)
        "-" -> calculate(num1, num2, sub)
        "x" -> calculate(num1, num2, mul)
        "/" -> calculate(num1, num2, div)
        else -> return println("Invalid operation selected")
    }

    // Print the result
    println("Result: $result")
}
