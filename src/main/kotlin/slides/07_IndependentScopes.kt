package slides

import kotlinx.coroutines.*

fun main() {
  try {
    runBlocking(Dispatchers.Default) {
      launch {
        println("main launch")
        delay(200)
        throw IllegalArgumentException()
      }
//      async_globalScope().await()
      suspendedAsync_globalScope()
    }
  } finally {
    Thread.sleep(1000)
  }
}

fun async_globalScope(): Deferred<Unit> {
  val deferred = GlobalScope.async {
    println("Global (independent) scope:        before delay")
    delay(1000)
    println("Global (independent) scope:        after delay")
  }
  deferred.invokeOnCompletion { e ->
    println("Global (independent) scope:        completed with $e")
  }
  return deferred
}

suspend fun suspendedAsync_globalScope() {
  async_globalScope().await()
}
