package com.gray.test.math

/**
 * 367. 有效的完全平方数
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 * 说明：不要使用任何内置的库函数，如  sqrt。
 *
 * 示例 1：
 * 输入：16
 * 输出：True
 *
 * 示例 2：
 * 输入：14
 * 输出：False
 * */
//开平方根有个结论就是 一个非负数n的平方根小于n/2+1
fun isPerfectSquare(num: Int): Boolean {
    var lo = 0
    var hi = num / 2 + 1
    val longN = num.toLong()
    while (lo <= hi) {
        val mid = lo + (hi - lo) / 2
        val temp = mid.toLong() * mid
        if (temp == longN) {
            return true
        }
        if (temp < longN) {
            lo = mid + 1
        } else {
            hi = mid - 1
        }
    }
    return false
}

fun main() {
    println(isPerfectSquare(808201))
}