package slides

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CustomScope : CoroutineScope {
  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Default

  fun main(): Job =
    launch {
      launch {
        println("main launch")
        delay(200)
        throw IllegalArgumentException()
      }
      launch {
        println("Scope via inheritance: before delay")
        delay(1000)
        println("Scope via inheritance: after delay")
      }.invokeOnCompletion { e ->
        println("Scope via inheritance: completed with $e")
      }
    }
}

fun main() {
  runBlocking {
    CustomScope().main().join()
  }
}
