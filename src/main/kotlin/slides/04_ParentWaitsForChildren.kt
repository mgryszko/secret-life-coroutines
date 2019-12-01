package slides

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
  runBlocking {
    launch {
      delay(100)
      println("Short launch")
    }
    launch {
      delay(1000)
      println("Long launch")
    }
  }
  println("After runBlocking")
}

