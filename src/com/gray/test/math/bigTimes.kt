package com.gray.test.math

import kotlin.math.abs

fun sign(x: Long) = if (x < 0) -1 else 1

/**
 * 两个大数相乘，n是他们的位数
 */
fun bigTimes(x: Long, y: Long, n: Int): Long {
    if (x == 0L || y == 0L) {
        return 0
    }
    if (n == 1) {
        return x * y
    }
    val sign = sign(x) * sign(y)
    val mask = Math.pow(10.0, n.toDouble() / 2).toInt()
    val a = abs(x) / mask
    val b = abs(x) - a * mask
    val c = abs(y) / mask
    val d = abs(y) - c * mask
    val ac = bigTimes(a, c, n / 2)
    val bd = bigTimes(b, d, n / 2)
    val abcd = bigTimes(a - b, d - c, n / 2) + ac + bd
    return sign * (ac * mask * mask + abcd * mask + bd)
}

fun main() {
    println(bigTimes(99999999, 88888888, 8))
    val x = 999999999L
    val y = 888888888L
    println(x * y)
}