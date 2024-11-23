package com.example.lab4

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

suspend fun sumArray(arr: IntArray): Int {
    return arr.sum()
}

fun main() {
    runBlocking {
        val arr = intArrayOf(1,2,3,4,5,6,7,8,9)

        val sum = async {
            sumArray(arr)
        }

        println("Sum of array elements: ${sum.await()}")
    }
}