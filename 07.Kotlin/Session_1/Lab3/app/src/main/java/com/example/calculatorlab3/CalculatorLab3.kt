package com.example.calculatorlab3

fun main()
{
    var inputOne = readln()
    var inputTwo= readln()
    if(!(inputOne.isEmpty())&&!(inputTwo.isEmpty()))
    {
        var NumOne=inputOne.toDouble()
        var NumTwo=inputTwo.toDouble()

        var multiplication=NumOne*NumTwo
        var addition=NumOne+NumTwo
        var subtration=NumOne-NumTwo
        var division:Double=0.0

        println("Multiplication: $multiplication")
        println("Addition: $addition")
        println("Subration: $subtration")
        if(inputTwo.toInt() !=0)
        {
            division =NumOne/NumTwo
            println("Division : $division")
        }
        else
        {
            println(" Divided by Zero NOT allowed")
        }

    }
    else
    {
        println("Non Valid Input")
    }



}