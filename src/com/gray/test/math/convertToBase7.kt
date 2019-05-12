package com.gray.test.math

import kotlin.math.abs

/***
 *
 * 504. 七进制数
 * 给定一个整数，将其转化为7进制，并以字符串形式输出。
 *
 * 示例 1:
 * 输入: 100
 * 输出: "202"
 *
 * 示例 2:
 * 输入: -7
 * 输出: "-10"
 *
 * 注意: 输入范围是 [-1e7, 1e7]
 */
fun convertToBase7(num: Int): String {
    if (num == 0) {
        return "0"
    }
    val flag = if (num >= 0) 1 else -1
    var p = abs(num)
    val ret = StringBuilder()
    while (p > 0) {
        ret.append(p.rem(7))
        p /= 7
    }
    if (flag == -1) {
        ret.append("-")
    }
    return ret.reverse().toString()
}

fun main() {
    println(convertToBase7(100))
    println(convertToBase7(-7))
    println(convertToBase7((-1E7).toInt()))
    println(convertToBase7((1E7).toInt()))
}
