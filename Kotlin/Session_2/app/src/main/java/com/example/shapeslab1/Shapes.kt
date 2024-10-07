package com.example.shapeslab1

abstract class Shapes {
    open var dim: Double = 0.0
        get() {
            return field
        }
        set(value) {
            field = value
        }

    abstract fun calcArea(): Double
}

class Rectangle(w: Double, var h: Double) : Shapes() {
    init {
        this.dim = w
    }

    override fun calcArea(): Double {
        return this.dim * h
    }
}

class Circle(var R: Double) : Shapes() {
    override var dim: Double = 0.0
    init {
        this.dim = R
    }

    override fun calcArea(): Double {
        return dim * dim * 3.14
    }
}

class Triangle(base: Double, var height: Double) : Shapes() {
    init {
        this.dim = base
    }

    override fun calcArea(): Double {
        return 0.5 * dim * height
    }
}

class Picture {
    fun sumAreas(shape1: Shapes, shape2: Shapes, shape3: Shapes): Double {
        return shape1.calcArea() + shape2.calcArea() + shape3.calcArea()
    }
}

fun main() {
    val circle = Circle(10.0)
    val rectangle = Rectangle(5.0, 4.0)
    val triangle = Triangle(1.0, 6.0)
    val picture = Picture()
    println("The total Area: ${picture.sumAreas(circle, rectangle, triangle)}")
}
