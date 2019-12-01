package slides

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun main() {
  runBlocking(Dispatchers.Default) {
    var continuation: Continuation<Int>? = null

    launch {
      println("before suspension ${Thread.currentThread().name}")
      suspendCoroutine { cont: Continuation<Int> ->
        continuation = cont
      }
      println("after suspension ${Thread.currentThread().name}")

    }

    launch {
      println("resume called in ${Thread.currentThread().name}")
      continuation!!.resume(1)
    }
  }
}

