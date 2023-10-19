package com.mani.apps.handson

import java.util.SortedMap
import java.util.Stack
import java.util.TreeMap


fun main() {

//    val s = "[()]{}{[()()]()}"
//    println(isBalanced(s)) // Balanced Brackets like programming
//    occurrence(s) //find number of occurrence for each char in a string

//    println("race and care are anagrams. (True/False) ${isItAnagram("race","rose")}")
//    palindromeOfNumber(4123)
//    println("Factorial of 5 is ${factorial(5)}")
//    println("2100 is leap year. (True/False) ${isLeapYear(2200)}")
//    println("First 10 numbers of fibanacci series are ${fib(10)}")
//    reverseString("mouse")
}

fun reverseString(s: String) {
    val ca = s.toCharArray()
    var len = ca.size
    while (len >0){
        print(ca[len-1])
        len--
    }
}

fun fib(number: Int): String {
    var n = number
    var i = 0
    var j = 1
    var temp: Int
    var res = ""
    while (n > 0){
        res = "$res $i"
        temp = i
        i = j
        j = i + temp
        n--
    }

    //Using Array
    val arr = Array<Int>(size = number, init = {0})
    arr[0] = 0
    arr[1] = 1
    for (i1 in 2..<number) {
        arr[i1] = arr[i1-1]+arr[i1 -2]
    }

    arr.forEach {
        print("$it ")
    }

    //Find nth number
    println( recursion(number))

    return res
}

fun recursion(number: Int) : Int{
    if(number <= 1){
        return number
    }
    return recursion(number - 1)+ recursion( number -2)
}


fun factorial(num: Int) : Number {

    var temp = num
    if(num < 2)
        return 1

    var res = 1
    while(temp > 1){
        res *= temp
        temp--
    }
    return res

}

fun isLeapYear(year: Int) : Boolean{
    if(year % 4 == 0){
        return if(year % 100 == 0){
            year % 400 == 0
        } else {
            true
        }
    }
    return false
}

//
fun palindromeOfNumber(i: Int) : Int{
    var pal = 0
    var temp = i
    while(temp > 0){
        val rem = temp % 10
        pal = rem + pal * 10
        temp /= 10
    }
    println("Palindrome of $i = $pal")
    return pal
}

fun isItAnagram(s:String, s1:String): Boolean {
    if(s.length != s1.length)
        return false

    //Using Sorting
    val arr = s.toCharArray()
    arr.sort()
    val arr1 = s1.toCharArray()
    arr1.sort()


    //Using Sorted Map
    val mapS = getOccurrenceCountMap(s)
    val mapS1 = getOccurrenceCountMap(s1)

    return (arr.concatToString() == arr1.concatToString()) && (mapS == mapS1)

}
fun occurrence(s: String): String{

    var map = mutableMapOf<Char,Int>()

    s.toCharArray().forEach {
        if(map.keys.contains(it)){
            println("Existing $it")
            val temp = map[it]
            if (temp != null) {
                map[it] = temp+1
            }
            println(map[it])
        }else {
            println("$it 1")
            map[it] = 1
        }
    }
    var s: String = ""
    for (item in map){
        s = s + " "+ item.key +""+item.value
    }
    println(s)
    return s

}

private fun getOccurrenceCountMap(
    s: String
): String {
    var map: SortedMap<Char, Int> = TreeMap<Char, Int>()
    s.toCharArray().forEach {
        if (map.keys.contains(it)) {
            val temp = map[it]
            if (temp != null) {
                map[it] = temp + 1
            }
            println(map[it])
        } else {
            map[it] = 1
        }
    }
    var s: String = ""
    for (item in map){
        s = s + " "+ item.key +""+item.value
    }
    return s
}

fun isBalanced(s: String): Boolean {

    println(s)
    val stack: Stack<Char> = Stack()

    if (s.isNotEmpty() && s.length % 2 != 0) {
        return false
    }

    s.toCharArray().forEachIndexed lit@{ index, c ->

        println("Char $c at $index")

        if (c in "{[(") {
            println("push $c")
            stack.push(c)
            return@lit
        }

        if (c in "}])") {

            val ex = stack.peek()
            when (c) {
                ')' -> {
                    if (ex == '(') {
                        println("pop (")
                        stack.pop()
                    }
                }

                '}' -> {
                    if (ex == '{') {
                        println("pop {")
                        stack.pop()
                    }
                }

                ']' -> {
                    if (ex == '[') {
                        println("pop [")
                        stack.pop()
                    }
                }
            }
        }


    }

    return stack.isEmpty()
}



