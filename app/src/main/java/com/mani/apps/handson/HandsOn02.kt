package com.mani.apps.handson

// Primary Constructor is declared in the class header
// If its declared, all secondary constructors has to invoke this
// Secondary constructors are declared inside the class.
// if primary constructor has any visibility or annotations, constructor keyword must be specified
// class Person() is the same but this is public and below is internal (equivalent to default in java).
// By default class is public final. To make it available for extension, needs to be declare it as open.
// extends - classname with primary constructor, implements interface name
// if derived class doesn't have primary constructor, secondary constructors must use super()
class Person private constructor() :  PersonA("male"), PersonI {
    var name: String = ""

    // Init block will execute top to bottom. Below, id will be null.
    // If id is declared before this init, it will print that value
    init {
        println("Init name $name")
    }
    var age: Int? = null
    var id: Int = 0

    override var test: Boolean
        get() = super.test
        set(value) {}

    constructor(name: String, age: Int) : this() {
        this.name = name
        this.age = age
    }

    constructor(id: Int) : this() {
        this.id = id
    }
    //static variables
    companion object {
        var PI = 3.14
    }

    override fun grow() {
    }

    // Override from Interface
    override fun eat() {
        sleep()
        // if multiple implementation available for the same method in base classes, use super<class/interface>
        super<PersonA>.eat()
        super<PersonI>.eat()
    }

    //override from base class
    override fun getGenderValue(): String {
        setGenderValue("female")
        var gender = super.getGenderValue()
        println(gender)
        return gender
    }

    init {
        println("Init age $age")
    }


}

// Creates Anonymous Class and Create Only one Immutable Object
// All its members and functions are final
object ObjectAnonymous{
    var PI = 3.14
    fun setValue(){

    }
}

interface PersonI {

    fun grow()
    fun eat(){

    }
    fun sleep(){
        println("Sleeping")
    }
}

open class PersonA (var gender: String) {
    open var test: Boolean = true
    // Must mention open to functions as well to override in derived class
    open fun getGenderValue(): String{
        return gender
    }

    fun setGenderValue(gender: String){
        this.gender = gender
    }

    open fun eat(){

    }
}

// we can declare variable inside primary constructor as well
// default value can be set, which makes not mandatory to pass when creating object.
data class Food (var isVeg: Boolean = false, var spiceLevel: Int = 5)


fun main() {

    // Object Classes. Cannot change value as all members of object is final
    ObjectAnonymous.PI
    ObjectAnonymous.setValue()

    Person(1).getGenderValue()

    var person = Person(name = "Mani", age = 1)

    var personAlso = person.also {
        it.name = "Manivannan"
    }.let {
        println(it.name)
    }

    personAlso.apply {
        person.eat()
    }.let {

    }

    with(personAlso){

    }



    person.run {
        this.name = "Manivannan"
        return@run this.name
    }.let {
        println(it)
        return@let "let"
    }.let {
        println(it)
    }


    var food = Food()


}
