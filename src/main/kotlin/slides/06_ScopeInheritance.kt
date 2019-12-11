package slides

import kotlinx.coroutines.*

fun main() {
  runBlocking(Dispatchers.Default) {
    scopePassedAsArgument(this)
    scopeAsImplicitReceiver()
    scopeDemarcationCoroutineScope()
//    scopeDemarcationWithContext()
  }
}

fun scopePassedAsArgument(coroutineScope: CoroutineScope) {
  coroutineScope.launch {
    println("Scope passed as argument:          before delay")
    delay(1000)
    println("Scope passed as argument:          after delay")
  }.invokeOnCompletion { e ->
    println("Scope passed as argument:          completed with $e")
  }
}

fun CoroutineScope.scopeAsImplicitReceiver() {
  launch {
    println("Scope as implicit receiver:        before delay")
    delay(1000)
    println("Scope as implicit receiver:        after delay")
  }.invokeOnCompletion { e ->
    println("Scope as implicit receiver:        completed with $e")
  }
}

suspend fun scopeDemarcationCoroutineScope() {
  coroutineScope {
    val job = launch {
      println("Scope created with coroutineScope: before delay")
      delay(1000)
      println("Scope created with coroutineScope: after delay")
    }
    job.invokeOnCompletion { e ->
      println("Scope created with coroutineScope: completed with $e")
    }
  }
}

suspend fun scopeDemarcationWithContext() {
  withContext(Dispatchers.Unconfined) {
    val job = launch {
      println("Scope created with withContext: before delay")
      delay(1000)
      println("Scope created with withContext: after delay")
    }
    job.invokeOnCompletion { e ->
      println("Scope created with withContext: completed with $e")
    }
  }
}
