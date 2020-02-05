package slides

import kotlinx.coroutines.*

fun main() {
  try {
    runBlocking(Dispatchers.Default) {
      coroutineContext[Job]!!.invokeOnCompletion { e -> println("runBlocking completed with $e") }
      launch {
        println("main launch")
        delay(200)
        throw IllegalArgumentException()
      }
//      val result = suspendedAsync_globalScope()
      val result = async_globalScope().await()
      println("Result: $result")
    }
  } finally {
    Thread.sleep(1000)
  }
}

private fun async_globalScope(): Deferred<Int> {
  val deferred = GlobalScope.async {
    println("Global (independent) scope:        before delay")
    delay(1000)
    println("Global (independent) scope:        after delay")
    1
  }
  deferred.invokeOnCompletion { e ->
    println("Global (independent) scope:        completed with $e")
  }
  return deferred
}

private suspend fun suspendedAsync_globalScope(): Int =
  async_globalScope().await()
