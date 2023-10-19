package com.mani.apps.handson

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext



fun main(args: Array<String>): Unit = runBlocking{

    println(Dispatchers.Main)
    printValue()

    supervisorScopeFun()
    coRoutineScopeFun()

    loadImage()
    launch{
        delay(3000)
        println("launch ${Thread.currentThread().id} ${Thread.currentThread().name} $coroutineContext")
        launch {
                delay(3000)
                println("launch child ${Thread.currentThread().id} ${Thread.currentThread().name} $coroutineContext")
        }
//        throw Exception() // cancels parent and its children coroutine job immediately except its CancellationException.
        // it will wait until its cancelled fully.
    } //make sure this job executes first. Then starts the subsequent jobs

    withContext(Dispatchers.Default){
        println("withContext ${Thread.currentThread().id} ${Thread.currentThread().name} $coroutineContext")
    }
    async{
        println("async ${Thread.currentThread().id} ${Thread.currentThread().name} $coroutineContext")
    }

    // Exception will not stop other childeren's execution as its caught properly
    launch {
        try {
            throw Exception()
        }catch (e: Exception){
            println("Caught Exception")
        }
    }

    //Below will not catch exception. Try/catch must be inside launch
    try {
        delay(10000)
        launch {
            throw Exception()
        }
    }catch (e: Exception){
        println("Caught Exception")
    }

}

suspend fun supervisorScopeFun() {
    supervisorScope {
        val task1 = launch {
            println("Task 1 started")
            delay(100)
            if (true) throw Exception("Oops!")
            println("Task 1 completed!")
        }
        val task2 = launch {
            println("Task 2 started")
            delay(1000)
            println("Task 2 completed!")
        }

        listOf(task1, task2).joinAll()
        println("Finished waiting for both tasks")
    }

    println("Done!")
    println("=======================================")
}

suspend fun coRoutineScopeFun() {
    coroutineScope {
        val task1 = launch {
            println("Task 1 started")
            delay(100)
            if (true) throw Exception("Oops!")
            println("Task 1 completed!")
        }
        val task2 = launch {
            println("Task 2 started")
            delay(1000)
            println("Task 2 completed!")
        }

        listOf(task1, task2).joinAll()
        println("Finished waiting for both tasks")
    }

    print("Done!")
}

//if its called inside coroutine, by default its a suspend function.
//Suspend function can only be called inside coroutine or another suspend function
fun printValue(){
    println("Not a Suspend Function")
}

@OptIn(DelicateCoroutinesApi::class)
fun loadImage()  {

    GlobalScope.launch(Dispatchers.IO) {
            delay(4000)
        println("GlobalScope ${Thread.currentThread().id} ${Thread.currentThread().name} $coroutineContext")
    }
    CoroutineScope(SupervisorJob()).launch{
        println("SupervisorJob ${Thread.currentThread().id} ${Thread.currentThread().name} $coroutineContext")
        throw Exception() // It will not affect parent or other child's execution as its in supervisorscope
    }
    CoroutineScope(Dispatchers.IO).async {
        delay(6000)
        println("Async IO ${Thread.currentThread().id} ${Thread.currentThread().name} $coroutineContext")
        val res = withContext(Dispatchers.Default){
            println("WithContext Default ${Thread.currentThread().id} ${ Thread.currentThread().name } $coroutineContext")
            return@withContext "Mani"
        }
        withContextExample(6)
        println(res)
    }
}

suspend fun withContextExample(i: Int) = withContext(Dispatchers.Unconfined){
    println("WithContext UnConfined ${Thread.currentThread().id} ${ Thread.currentThread().name } $coroutineContext")
    return@withContext "Mani$i"
}