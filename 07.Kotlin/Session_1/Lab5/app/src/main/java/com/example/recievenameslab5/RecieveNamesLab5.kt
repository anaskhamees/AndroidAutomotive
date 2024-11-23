package com.example.recievenameslab5

fun main()
{
    var firstName= readln()
    var lastName= readln()
    if(!firstName.isEmpty()&&!lastName.isEmpty())
    {
        println("$firstName $lastName ")
    }
    else{
        println("Not Valid Input")
    }
}