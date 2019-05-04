package com.gray.test.math

import kotlin.math.min


/**
 * 69. x 的平方根
 *
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *  输入: 4
 *  输出: 2
 *
 * 示例 2:
 *  输入: 8
 *  输出: 2
 *  说明: 8 的平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 */

fun mySqrt(x: Int): Int {
    print("$x : ${Math.sqrt(x.toDouble())} sqrt = ")
    if (x == 0 || x == 1) {
        return x
    }
    val max = 46340// Math.sqrt(Int.MAX_VALUE)
    var end = min(x / 2, max)
    var start = 0
    while (start <= end) {
        val mid = (start + end) / 2
        val midpow2 = mid * mid
        when {
            midpow2 < x -> start = mid + 1
            midpow2 == x -> return mid
            else -> end = mid - 1
        }
    }
    return (start + end) / 2
}

fun main() {
    println(mySqrt(2147483647))
    repeat(200) {
        println(mySqrt(it))
    }
}