package com.example.lab5

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job1 = launch {
            repeat(5) {
                println("Job1: lanuch $it")
                delay(500)
            }
        }

        job1.join()
        println("Job completed")

        val job2 = launch {
            repeat(5) {
                println("Job2: launch $it")
                delay(1000)
            }
        }

        delay(2000)
        job2.cancelAndJoin()
        println("Job2 is cancelled")
    }
}