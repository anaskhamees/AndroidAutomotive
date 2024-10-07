package com.example.lab1_coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
        launch {
            repeat(10) {
                delay(100)
                println("Launch $it")
            }
        }
        val asyncRef = async {
            repeat(10) {
                delay(100)
                println("Async $it")
            }
        }

        asyncRef.await()
    }
}