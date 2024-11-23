package com.example.intarraylab2
import java.util.Random
fun main()
{
    val rand = Random()
    val arr = IntArray(100)
    for (i in arr.indices)
    {
        val randomNumber=rand.nextInt(100)
        arr[i]=randomNumber
    }

    println("The Array Values : ")
    for (i in arr.indices)
    {
        if(arr[i]<=10)
            println(arr[i])
    }

}