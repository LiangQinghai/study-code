package cn.liangqinghai.study

import java.lang.Integer.parseInt
import java.text.SimpleDateFormat
import java.util.*

fun main(args: Array<String>) {
    println("Hello, World")
    println(SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(Date()))

    val str = "Hello"

    println(str is String)
    // kotlin 类型class
    println(str::class)
    // java 类型class
    println(str::class.java)

    val template = """
        fun print():Any {
            return "";
        }
    """.trimIndent()

    println(template)

    val a = 1
    val b = 1

    var conditionRes = if (a < b) {
        print(a)
        a
    } else {
        print(b)
        b
    }

    println(conditionRes)

    val res = if(a == b) true else false
    println(res)

    when (res) {
        true -> println("boolean val \t" + res::class.java)
        is Boolean -> println("Boolean")
        else -> println("else")
    }

    when (a) {

        0, 1 -> println("0 or 1")
        2 -> println("2")
        else -> println(a)

    }

    val c = "123"
    when (a) {
        1, 2 -> println("a")
        parseInt(c) -> println()
    }

    val intArrayOf = intArrayOf(1, 2, 3, 4)

    for (arg in intArrayOf)
        println(arg)

    for (i in intArrayOf.indices)
        println(intArrayOf[i])

    for ((index, value) in intArrayOf.withIndex()) {

        println("${index}, $value")

    }

    intArrayOf.forEach {

        if (it == 2) return@forEach

        println(it)

    }

    outerLoop@ for (i in 1..5) {

        println(i)

        for (j in 1..5) {

            if (j == 2) {
                break@outerLoop
            }

        }

    }

}

