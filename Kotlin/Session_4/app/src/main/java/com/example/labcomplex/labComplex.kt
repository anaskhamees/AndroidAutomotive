package com.example.labcomplex

class Complex(val realPart: Int = 0, val imaginaryPart: Int = 0) {

    constructor(real: Int) : this(real, 0)

    operator fun plus(other: Complex): Complex {
        return Complex(this.realPart + other.realPart, this.imaginaryPart + other.imaginaryPart)
    }

    // Operator function for subtraction
    operator fun minus(other: Complex): Complex {
        return Complex(this.realPart - other.realPart, this.imaginaryPart - other.imaginaryPart)
    }
}

fun Complex.print() {
    if(imaginaryPart<0)
    {
        println("$realPart ${imaginaryPart}i")

    }
    else if(imaginaryPart>0)
    {
        println("$realPart + ${imaginaryPart}i")
    }
    else{
        println("$realPart")
    }
}

fun main() {
    println("Enter the real 1: ")
    val real1 = readLine()
    println("Enter the imaginary 1: ")
    val img1 = readLine()

    // Get user input for the second complex number
    println("Enter the real 2: ")
    val real2 = readLine()
    println("Enter the imaginary 2: ")
    val img2 = readLine()

    if (real1.isNullOrEmpty() || img1.isNullOrEmpty() || real2.isNullOrEmpty() || img2.isNullOrEmpty()) {
        println("Input Not Valid")
    } else {
        val complex1 = Complex(real1.toInt(), img1.toInt()) // Using the primary constructor
        val complex2 = Complex(real2.toInt(), img2.toInt()) // Using the primary constructor

        val sum = complex1 + complex2
        val difference = complex1 - complex2

        // Using the extension function to display the results
        println("The sum of the two complex numbers is: ")
        sum.print()

        println("The difference of the two complex numbers is: ")
        difference.print()
    }
}
