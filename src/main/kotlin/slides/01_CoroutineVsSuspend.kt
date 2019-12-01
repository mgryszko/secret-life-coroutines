package slides

import kotlinx.coroutines.*

fun main() {
  val urls = runBlocking(Dispatchers.Default) {
    val url1 = url1()
    url1.await() + url2()
  }

  println("Urls: $urls")
}

fun url1(): Deferred<List<String>> =
  GlobalScope.async {
    println("URL 1: ${Thread.currentThread().name}")
    delay(100)
    listOf("http://example.com/url1")
  }

suspend fun url2(): List<String> =
  GlobalScope.async {
    println("URL 2: ${Thread.currentThread().name}")
    delay(1000)
    listOf("http://example.com/url2")
  }.await()

