package com.example.a05_session

class Person(var name: String, var id: Int, var gender: String) {
    fun printDetails() {
        println("Name: $name, ID: $id, Gender: $gender")
    }
}

fun main() {

    val personA = Person("Anas", 1, "Male").let {
        println("############# Before #############")
        it.printDetails()
        it.name = "Belal"
        it
    }

    val personB = Person("Ali", 2, "Male").run {
        println("############# Before #############")
        this.printDetails()
        this.id=5
        this
    }

    val personC = Person("Mohamed", 3, "Female").also {
        println("############# Before #############")
        it.printDetails()
        it.id = 4
        it.name = "Ahmed"
    }
    println("############# After #############")
    personA.printDetails()
    println("############# After #############")
    personB.printDetails()
    println("############# After #############")
    personC.printDetails()
}