package com.gray.test.math

/***
 * 258. 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 *
 * 示例:
 * 输入: 38
 * 输出: 2
 * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
 */
fun addDigits(num: Int): Int {
    var ret = num
    while (ret >= 10) {
        var temp = 0
        while (ret > 0) {
            temp += ret % 10
            ret /= 10
        }
        ret = temp
    }
    return ret
}

fun addDigits2(num: Int): Int {
    return (num - 1) % 9 + 1
}

fun main() {
    println(addDigits(38))
    println(addDigits2(38))
}