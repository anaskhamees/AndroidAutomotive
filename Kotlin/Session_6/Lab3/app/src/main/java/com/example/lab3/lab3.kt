package com.example.lab3

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.cancellation.CancellationException

fun main()

{
    runBlocking {
        val job1 = launch {
            try {
                repeat(5) {
                    delay(1000)
                    println("Job 1: launch $it")
                }
            } catch (e: CancellationException) {
                println("Job 1 is cancelled")
            }
        }

        val job2 = launch {
            try {
                repeat(5) {
                    delay(1000)
                    println("Job 2: launch $it")
                }
            } catch (e: CancellationException) {
                println("Job 2 is cancelled")
            }
        }

        delay(10000)
        job1.cancel()
        // job1.cancelAndJoin()
        job2.cancelAndJoin()
        println("Job1 and Job2 are cancelled in 10 seconds.")
    }
}