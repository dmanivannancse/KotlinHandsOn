package com.mani.apps.handson

fun main() {
    //Custom Comparator to sort
    var ss = mutableSetOf<String>("Mani", "Amudha", "Dhanam", "Dharma").toSortedSet(
        Comparator<String>(function = { s, s1 -> return@Comparator s.compareTo(s1) })
    )
    mutableSetOf<String>("Mani", "Amudha", "Dhanam", "Dharma").sortedBy { s -> s.length }
    var pair: Pair<String, String> = Pair("Mani", "Amudha")
    pair.let { it }.run { print(first) }

    ss.run {
        add("Tamil")
        ss
    }.let {
        println(it.size)
        it
    }.apply { add("Megala") }.also { it.add("Murugan") }

    with(ss) {
        println(this.size)

    }

    // apply - returns the same object. access without 'it' or 'this'.
    // also - return the same object. access with 'it'
    // let - doesn't return. access with 'it'
    // run - doesn't return. access without 'it' or 'this'
    // with - doesn't return. access with 'this'



//    open - any class to be inherited, needs to use open
//    sealed class - combination of abstract and enum class.
//    cannot create object
//    any class wants to inherit, needs to be declared inside sealed class only.
//    set of same members - like enum
//            whereas members of enum are typically singleton objects of enum. must have same signature


    val error = HttpError.UnAuth("Un")
    handleResult(error)

    val errorE = ErrorCodes.UnAuth
    handleEnumResult(errorE)

}

enum class ErrorCodes(val code: Int){
    UnAuth(401),
    NotFound(404),
    ServerError(500);
}
fun handleEnumResult(errorE: ErrorCodes) {
    when(errorE){
        ErrorCodes.UnAuth -> TODO()
        ErrorCodes.NotFound -> TODO()
        ErrorCodes.ServerError -> TODO()
    }
}

sealed class HttpError(val code: Int){
    open class UnAuth(val reason: String): HttpError(401)
    open class NotFound: HttpError(404)

}


fun handleResult(error: HttpError){
    when(error){
        is HttpError.NotFound -> Unit
        is HttpError.UnAuth -> when(error.reason){
            "UnAuth" -> Unit
            "Expired" -> Unit
        }
    }
}


open class ScopeFunctions private constructor(){

    // value will be initialized at the time of first access.
    // only val can be used. So its a singleton now. because of private constructor


    //There is no static keyword in Kotlin. So everything declared inside companion object are static/ class members.
    companion object {
        internal var i = 0
        private val pi: Double = 3.14
        fun area(radius: Double): Double {
            return pi * radius * radius
        }
        val instance:ScopeFunctions by lazy {
            ScopeFunctions()
        }
    }
    fun printSomething(value: Any){
        println(value)
    }
}

// Singleton class can be created using object
// it will be created at the time of first access
object SingleTon {
    var i = 0
    fun token() {
        ScopeFunctions.instance.printSomething("Hi")
    }

    fun getII() = (i).also { i++ }
}

// Another way tp create Singleton object.
// For multithreaded environment
class Singleton private constructor() {
    companion object {

        @Volatile
        private var instance: Singleton? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Singleton().also { instance = it }
            }
    }
    fun doSomething() = "Doing something"
}