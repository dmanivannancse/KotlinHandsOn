

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
IO - Network Calls/Disk Read/Write
Default - CPU Intensive work

Coroutine Builders: coroutines can be launched using coroutine builders. 
1. launch - Job - doesnot return result
     launch{}: Job
2. async - Deferred - returns result. Deferred is extension of Job but returns result. 
     async{}: Deferred<T>
3. withcontext(Dispatchers.Main) - any desired result - able to run in its own mentioned context.
      launch and async will be run in its parent context (scope context). if we want to run some tasks in different context
      for ex if we want to send data back to UI, we need Main Thread Context. using withcontext we can switch context.
      Syntax:  withcontext(Dispatchers.Main) {}

Coroutines - any failure in child coroutine, will cancel parent coroutine and all the other child coroutine.
two ways to launch coroutines.

supervisorScope{} - to avoid cancellation of parent and its child coroutines, we can use supervisorscope.
                  cancellation will not escalate up to cancel parent.
                  
CoroutineScope:
coroutines needs context. coroutinescope provides context to coroutine to run. 
CoroutineScope(coroutinecontext){launch/await/withcontext} - based on the context.
GlobalScope - Runs Globally. Cancelled when application is destroyed.
viewmodlescope
lifecyclescope

CoroutineScope(GlobalScope/CoroutineScope/ViewModelScope/LifecycleScope) -> CoroutineContext(Dispatchers.Main/IO/Default) -> CoroutineBuilders(launch/await/withcontext) -> Job/Deferred


Job:
States: New/Active/Cancelling/Completing/Completed/Cancelled

Return below boolean
isActive - Active/Completing
isCompleted - completed/cancelled
isCancelled - cancelling/cancelled

syntax: job().isActive()

when the cancel is called, it will enter into cancelling state first, then move to cancelling state.
join - launch{}.join() - after the completion of this coroutine only, next coroutines will be executed. 
cancel - come out and stop everything immediately even if its in cancelling state. 
cancelAndJoin - Cancel and wait for the job to enter Cancelled state. 

for async{} also we can use join to complete the job and also we can use await() to wait and get the result. 
launch{}.join = async{}.await()


