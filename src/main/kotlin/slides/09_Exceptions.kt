package slides

import kotlinx.coroutines.*

fun main() {
  try {
    runBlocking(Dispatchers.Default) {
      coroutineContext[Job]!!.invokeOnCompletion { e ->
        if (e == null) println("Completed successfully")
        else println("Completed exceptionally with $e")
      }
//      async_sharedScope()
//      launch_sharedScope()
//      async_globalScope()
      launch_globalScope()
//      async_globalScope().await()
//      launch_globalScope().join()
      delay(200)
    }
    println("After runBlocking")
  } catch (e: Exception) {
    println("Exception: $e")
  }
}

private fun CoroutineScope.async_sharedScope() = async {
  delay(100)
  throw IllegalArgumentException("async shared scope")
}

private fun CoroutineScope.launch_sharedScope() = launch {
  delay(100)
  throw IllegalArgumentException("launch shared scope")
}

private fun async_globalScope() = GlobalScope.async {
  delay(100)
  throw IllegalArgumentException("async global scope")
}

private fun launch_globalScope() = GlobalScope.launch {
  delay(100)
  throw IllegalArgumentException("launch global scope")
}
