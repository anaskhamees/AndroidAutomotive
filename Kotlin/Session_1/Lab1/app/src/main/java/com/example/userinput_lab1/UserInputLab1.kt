package com.example.userinput_lab1

fun main()
{
    var name :String= readln()
    if(name!="")
    {
        println("Hello, ${name}")
    }
    else
    {
        println("Spaces is entered")
    }
}