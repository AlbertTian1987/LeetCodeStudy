package com.gray.test.math

import kotlin.math.pow
import kotlin.random.Random
import kotlin.system.measureNanoTime

/**
 * 多项式求值
 * a系数 a0 + a1*x + a2*x^2 + .... an*x^n
 *
 * */

fun polynEval1(a: DoubleArray, x: Double): Double {
    var p = a.last()
    var n = a.size - 2
    while (n >= 0) {
        p = x * p + a[n--]
    }
    return p
}

//笨方法
fun polynEvalFoo(a: DoubleArray, x: Double): Double {
    var p = 0.0
    for (i in 0 until a.size) {
        p += a[i] * x.pow(i)
    }
    return p
}

fun main() {
    val a = DoubleArray(1000_000) { Random.nextDouble(30.0) }
    println(measureNanoTime { println(polynEval1(a, 2.0)) })
    println(measureNanoTime { println(polynEvalFoo(a, 2.0)) })
}