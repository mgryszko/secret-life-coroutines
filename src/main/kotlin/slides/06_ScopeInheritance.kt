package slides

import kotlinx.coroutines.*

fun main() {
  runBlocking(Dispatchers.Default) {
    coroutineContext[Job]!!.invokeOnCompletion { e -> println("runBlocking completed with $e") }
    launch {
      println("main launch")
      delay(200)
      throw IllegalArgumentException()
    }
    scopePassedAsArgument(this)
    scopeAsImplicitReceiver()
    scopeCoroutineScope()
    scopeWithContext()
  }
}

private fun scopePassedAsArgument(coroutineScope: CoroutineScope) {
  coroutineScope.launch {
    println("Scope passed as argument:          before delay")
    delay(1000)
    println("Scope passed as argument:          after delay")
  }.invokeOnCompletion { e ->
    println("Scope passed as argument:          completed with $e")
  }
}

private fun CoroutineScope.scopeAsImplicitReceiver() {
  launch {
    println("Scope as implicit receiver:        before delay")
    delay(1000)
    println("Scope as implicit receiver:        after delay")
  }.invokeOnCompletion { e ->
    println("Scope as implicit receiver:        completed with $e")
  }
}

private suspend fun scopeCoroutineScope() {
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

private suspend fun scopeWithContext() {
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
