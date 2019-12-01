package slides

import kotlinx.coroutines.*

fun main() {
  runBlocking(Dispatchers.Default) {
    launch {
      println("main launch")
      delay(200)
      throw IllegalArgumentException()
    }
    async_scopePassedAsArgument(this)
    async_scopeAsImplicitReceiver()
    async_scopeDemarcation().await()
  }
}

fun async_scopePassedAsArgument(coroutineScope: CoroutineScope) {
  coroutineScope.async {
    println("Scope passed as argument:          before delay")
    delay(1000)
    println("Scope passed as argument:          after delay")
  }.invokeOnCompletion { e ->
    println("Scope passed as argument:          completed with $e")
  }
}

fun CoroutineScope.async_scopeAsImplicitReceiver() {
  async {
    println("Scope as implicit receiver:        before delay")
    delay(1000)
    println("Scope as implicit receiver:        after delay")
  }.invokeOnCompletion { e ->
    println("Scope as implicit receiver:        completed with $e")
  }
}

suspend fun async_scopeDemarcation(): Deferred<Unit> {
  return coroutineScope {
    val deferred = async {
      println("Scope created with coroutineScope: before delay")
      delay(1000)
      println("Scope created with coroutineScope: after delay")
    }
    deferred.invokeOnCompletion { e ->
      println("Scope created with coroutineScope: completed with $e")
    }
    deferred
  }
}
