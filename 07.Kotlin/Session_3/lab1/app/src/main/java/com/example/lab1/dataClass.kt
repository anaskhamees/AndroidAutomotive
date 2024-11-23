package com.example.lab1

data class Person (var name:String,var personAddress: PersonAddress)
{
     data class PersonAddress(var streetName:String,var city:String,var building:Building)
    enum class Building
    {
        villa,
        apartment
    }

}
fun main()
{

   println(Person("Anas",Person.PersonAddress("AlexandriaDesert","Cairo",Person.Building.villa)).toString())
}


