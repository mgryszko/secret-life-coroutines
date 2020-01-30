package slides

import kotlinx.coroutines.*

fun main() {
  runBlocking(Dispatchers.Default) {
    println("before crawling: ${Thread.currentThread().name}")
    val url = async {
      println("retrieving URL: ${Thread.currentThread().name}")
      delay(100)
      println("got URL after some delay: ${Thread.currentThread().name}")
      listOf("http://example.com/url1")
    }.await()
    println("URL after crawling: ${Thread.currentThread().name} $url")
    delay(100)
    println("After final delay: ${Thread.currentThread().name}")
  }
}
