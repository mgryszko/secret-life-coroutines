package slides

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
  runBlocking {
    launch {
      launch {
        println("Short launch 1")
        delay(100)
      }
      launch {
        println("Short launch 2")
        delay(200)
      }
    }.invokeOnCompletion { println("Short launches completed") }
    launch {
      println("Long launch")
      delay(1000)
    }.invokeOnCompletion { println("Long launch completed") }
  }
  println("After runBlocking")
}

