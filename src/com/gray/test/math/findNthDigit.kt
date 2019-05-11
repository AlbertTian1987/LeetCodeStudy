package com.gray.test.math


/**
 * 400. 第N个数字
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。
 *
 * 注意:
 * n 是正数且在32为整形范围内 ( n < 231)。
 *
 * 示例 1:
 * 输入:
 *  3
 * 输出:
 *  3
 *
 * 示例 2:
 * 输入:
 *  11
 * 输出:
 *  0
 * 说明:
 *  第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。
 * */
/**
 * 1 - [1,9]                9
 * 2 - [10,99]              90
 * 3 - [100,999]            900
 * 4 - [1000,9999]          9000
 * .........2^31 =          2147483648
 */
import kotlin.math.pow

fun findNthDigit(n: Int): Int {
    if (n < 10) {
        return n
    }
    var length: Long = 0
    var i = 1
    var step: Long = 9
    while (length + i * step < n) {
        length += i * step
        i++
        step *= 10
    }
    val num = (10.0.pow(i - 1) - 1 + (n - length + 1) / i).toLong().toString()
    val index = ((n - length - 1) % i).toInt()
    return num[index] - '0'
}

fun main() {
    println(findNthDigit(10))
}