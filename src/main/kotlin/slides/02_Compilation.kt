package slides

import kotlinx.coroutines.*

fun main() {
  runBlocking {
    val url1 = async {
      listOf("http://example.com/url1")
    }
    val url2 = async {
      listOf("http://example.com/url2")
    }
    url1.await() + url2.await()
  }
}

