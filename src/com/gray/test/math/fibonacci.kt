package com.gray.test.math

import kotlin.system.measureNanoTime


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

fun fibonacci2(pow: Int): Int {
    val temp = Array(2) { IntArray(2) }
    //二阶矩阵乘法
    fun matrixPow(a: Array<IntArray>, b: Array<IntArray>) {
        for (i in 0..1) {
            for (j in 0..1) {
                temp[i][j] = 0
                for (k in 0..1) {
                    temp[i][j] += a[i][k] * b[k][j]
                }
            }
        }
        for (i in 0..1) {
            for (j in 0..1) {
                a[i][j] = temp[i][j]
            }
        }
    }

    var n = pow
    val base = arrayOf(intArrayOf(1, 1), intArrayOf(1, 0))

    //单位矩阵
    val res = arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))
    while (n > 0) {
        if (n and 1 != 0) {
            matrixPow(res, base)
        }
        matrixPow(base, base)
        n /= 2
    }

    return res[0][1]
}

/**
 * 迭代的写法
 */
fun fibonacci3(n: Int): Int {
    var i = 0
    var j = 1
    repeat(n) {
        j = i + j.also { i = j }
    }
    return i
}
fun main() {
    println(measureNanoTime { fibonacci1(6) })
    println(fibonacci2(6))
    println(fibonacci3(6))
    println(fibonacci(0, 1, 0, 9898))
}