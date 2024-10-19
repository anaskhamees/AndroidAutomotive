package com.example.flow

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

//Observable
fun getData()=flow{
    for(i in 1..10){
        emit(i)
    }
}

// Observer
fun main()= runBlocking {
    getData()
        .filter { it>5 }
        .map { it*5 }
        .collect{
            println(it)
        }
}