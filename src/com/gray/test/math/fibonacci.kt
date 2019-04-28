package com.gray.test.math


/**
 * Fibonacci
 *
 * */
val cache = hashMapOf<Int, Int>()

/**
 * 缓存递归实现 O(n)
 */
fun fibonacci1(n: Int): Int {
    if (n == 0) {
        return 0
    }
    if (n == 1) {
        return 1
    }
    if (cache.containsKey(n)) {
        return cache[n]!!
    }
    val n1 = fibonacci1(n - 1)
    cache[n - 1] = n1
    val n2 = fibonacci1(n - 2)
    cache[n - 2] = n2
    val i = n1 + n2
    cache[n] = i
    return i
}

/**
 * 尾递归
 */
tailrec fun fibonacci(a: Int, b: Int, i: Int, n: Int): Int {
    if (i >= n) {
        return a
    }
    return fibonacci(b, a + b, i + 1, n)
}


/**
 * 关于斐波拉契的一个规律
 *
 * |Fn+1 Fn | = |1 1|^n
 * |Fn  Fn-1|   |1 0|
 *
 * 用归纳法证明
 * |Fn+1 Fn | = |Fn   Fn-1 |    |1 1| = |Fn+Fn-1   Fn   |
 * |Fn  Fn-1|   |Fn-1 Fn-2 | *  |1 0|   |Fn-1+Fn-2 Fn-1 |
 *
 * Fn+1 = Fn + Fn-1
 * Fn = Fn-1 +Fn-2
 *
 * 可证
 */

fun fibonacci2(n: Int): Int {
    if (n == 0) {
        return 0
    }

    fun times(m1: IntArray, m2: IntArray): IntArray {
        val n0 = m1[0] * m2[0] + m1[1] * m2[2]
        val n1 = m1[0] * m2[1] + m1[1] * m2[3]
        val n2 = m1[2] * m2[0] + m1[3] * m2[2]
        val n3 = m1[2] * m2[1] + m1[3] * m2[3]
        return intArrayOf(n0, n1, n2, n3)
    }

    fun help(matrix: IntArray, n: Int): IntArray {
        if (n == 1) {
            return matrix
        }
        return if (n and 1 == 0) {
            val p = n / 2
            val m = help(matrix, p)
            times(m, m)
        } else {
            val p = (n - 1) / 2
            val m = help(matrix, p)
            times(times(m, m), matrix)
        }
    }

    val m = help(intArrayOf(1, 1, 1, 0), n)
    return m[1]
}

fun main() {
    println(fibonacci1(186))
    println(fibonacci2(186))
    println(fibonacci(0, 1, 0, 186))
}