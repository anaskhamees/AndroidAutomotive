package com.example.startspatternlab4

fun main()
{
    val singlestar = "*"
    var startSpaces = "          *"
    var leftPatternString = singlestar
    var rightPatternString = startSpaces

    for (i in 0 until 5) {
        println(leftPatternString + rightPatternString)
        leftPatternString += singlestar
        rightPatternString = rightPatternString.substring(2) + " *"
    }
}