package cn.liangqinghai.study

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread

fun main(args: Array<String>) {

//    firstCoroutine()

//    threadSum()

//    simpleCoroutine()

}

private fun asyncCoroutine() {

    val deferred = (1..1000000L).map { n ->
        GlobalScope.async {
            n
        }
    }

    println("deferred: $deferred")

//    val sum = deferred.map { it.await().toLong() }.sum()
//
//    println("sum: $sum")

}

private fun firstCoroutine() {
    GlobalScope.launch {
        delay(1000)
        println("Delay 1000")
    }

    Thread.sleep(2000)
    println("Sleep 2000")
}

private fun threadSum() {
    val c = AtomicLong()

    for (i in 1..1000000L) {

        thread(start = true) {
            c.addAndGet(i)
        }

    }

    println(c.get())
}

private fun simpleCoroutine() {
    val c = AtomicLong()
    for (i in 1..1000000L) {
        GlobalScope.launch {
            c.addAndGet(i)
        }
    }

    println(c.get())
}