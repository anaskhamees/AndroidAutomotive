package com.example.lab2

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun doHeavyFactorial(number: Int): Int {
    var counter = 1
    repeat(number) {
        counter *= (it + 1)
        delay(50)
    }
    return counter
}

fun main() {
    runBlocking {
        val asyncRef = async {
            doHeavyFactorial(4)
        }

        println("Factorial: ${asyncRef.await()}")
    }
}