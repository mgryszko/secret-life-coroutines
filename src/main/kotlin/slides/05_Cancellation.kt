package slides

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
  runBlocking {
    coroutineContext[Job]!!.invokeOnCompletion { e -> println("runBlocking completed with $e") }
    launch {
      delay(100)
      throw IllegalArgumentException("Crash")
    }
    launch {
      delay(1000)
      println("Successful launch")
    }.invokeOnCompletion { e -> println("Successful launch completed with $e") }
  }
  println("After runBlocking")
}

