package com.gray.test.math

/**
 * 回文数
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *  输入: 121
 *  输出: true
 *
 * 示例 2:
 *  输入: -121
 *  输出: false
 *  解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 *  输入: 10
 *  输出: false
 *  解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * */

fun isPalindrome(x: Int): Boolean {
    if (x < 0) {
        return false
    }
    if (x % 10 == 0 && x != 0) {
        return false
    }
    if (x < 10) {
        return true
    }
    var n = x
    var ret = 0
    while (n > 0) {
        ret = ret * 10 + n % 10
        n /= 10
    }
    return ret == x
}

fun main() {
    println(isPalindrome(121))
    println(isPalindrome(1221))
    println(isPalindrome(1))
    println(isPalindrome(1321))
    println(isPalindrome(-1321))
    println(isPalindrome(Int.MAX_VALUE))
    println(isPalindrome(1111121111))
    println(isPalindrome(1111221111))
}