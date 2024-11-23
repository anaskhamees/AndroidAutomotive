package com.example.lab2

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take

fun evenNumbers()=flow{
    for(i in 0..40 step 2){
        delay(1000)
        emit(i)
    }
}
suspend fun main():Unit=
    coroutineScope {
        evenNumbers()
            .take(10)
            .collect{
                value->
                println(value)
            }
    }