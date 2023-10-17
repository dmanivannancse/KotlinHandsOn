

inline function - inline fun getValue(): String{} - no memory will be created as a function. at runtime inline function code will be copied at the calling place.
scope function - perform actions on an object. inside we can access the objects properties.
let
run
also
apply
with



async/await

await is not a keyword in kotlin. its function inside Deferred Interface.

fun processImage() async {

}



await - suspend function

launch - async

coroutines are not thread. its set of instructions will be executed in a thread.
we dont have to create thread and manage lifecycle.
whenver we create coroutines, jvm will execute in a thread whether it can be Main thread or WorkerThread.
It can be configured in code itself by mentioning Dispatcher.



Three type of Dispatchers / Provides CoroutineContext
Main - UI Thread
IO - CPU Intensive work
Default - Network Calls


coroutines - any failure in child coroutine, will cancel parent coroutine and all the other child coroutine.
two ways to launch coroutines

Coroutine Builders:
launch - job - doesnot return result
launch{}: Job
async - Deferred - returns result
async{}: Deferred<T>
withcontext - any result - able to run in its own mentioned context.
launch and async needs its parent context (scope context).
using withcontext, we can mention context.



CoroutineScope(coroutinecontext){launch/await/withcontext} - based on the context.

GlobalScope - Runs Globally. Cancelled when application is destroyed.

CoroutineScope(GlobalScope/CoroutineScope/ViewModelScope/LifecycleScope) -> CoroutineContext(Dispatchers.Main/IO/Default) -> CoroutineBuilders(launch/await/withcontext) -> Job/Deferred