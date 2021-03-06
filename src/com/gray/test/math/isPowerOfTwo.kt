package com.gray.test.math

/***
 * 231. 2的幂
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * 示例 1:
 * 输入: 1
 * 输出: true
 * 解释: 2^0 = 1
 *
 * 示例 2:
 * 输入: 16
 * 输出: true
 * 解释: 2^4 = 16
 *
 * 示例 3:
 * 输入: 218
 * 输出: false
 */
fun isPowerOfTwo(n: Int): Boolean {
    if (n <= 0) {
        return false
    }
    if (n == 1) {
        return true
    }
    var p = n
    while (p != 2) {
        if (p % 2 == 0) {
            p /= 2
        } else {
            return false
        }
    }
    return true
}

fun main() {
    println(isPowerOfTwo(-2))
    println(isPowerOfTwo(4))
    println(isPowerOfTwo(-8))
    println(isPowerOfTwo(-16))
    println(isPowerOfTwo(1))
    println(isPowerOfTwo(2))
    println(isPowerOfTwo(4))
    println(isPowerOfTwo(-1))
    println(isPowerOfTwo(-4))
    println(isPowerOfTwo(218))
}