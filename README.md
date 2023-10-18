

inline function
  no memory will be created as a function. at runtime inline function code will be copied at the calling place.
  syntax: inline fun getValue(): String{} 
  
scope function - perform actions on an object. inside we can access the objects properties.
let - it - can be used to replace if not null condition. 
run - 
also 
apply
with

Concurrency/Asynchronous
coroutines are not thread. its a set of instructions will be executed in a thread. each coroutine is a Job in kotlin. We can start/join/cancel jobs manaully as well. 
we dont have to create thread and manage lifecycle. if we want we can create threadpool and execute coroutines.
whenever we create coroutines, jvm will execute in a thread. we can get to choose where it should run whether it can be Main thread (mostly UI) or WorkerThread.
It can be configured in code itself by mentioning Dispatcher.

Three type of Dispatchers / Provides CoroutineContext
Main - UI Thread
IO - CPU Intensive work
Default - Network Calls


Coroutine Builders: coroutines can be launched using coroutine builders. 
1. launch - Job - doesnot return result
     launch{}: Job
2. async - Deferred - returns result. Deferred is extension of Job but returns result. 
     async{}: Deferred<T>
3. withcontext(Dispatchers.Main) - any desired result - able to run in its own mentioned context.
      launch and async will be run in its parent context (scope context). if we want to run some tasks in different context
      for ex if we want to send data back to UI, we need Main Thread Context. using withcontext we can switch context.
      Syntax:  withcontext(Dispatchers.Main) {}


coroutines - any failure in child coroutine, will cancel parent coroutine and all the other child coroutine.
two ways to launch coroutines

CoroutineScope(coroutinecontext){launch/await/withcontext} - based on the context.

GlobalScope - Runs Globally. Cancelled when application is destroyed.

CoroutineScope(GlobalScope/CoroutineScope/ViewModelScope/LifecycleScope) -> CoroutineContext(Dispatchers.Main/IO/Default) -> CoroutineBuilders(launch/await/withcontext) -> Job/Deferred
