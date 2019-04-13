package com.gray.test.math

/**
 * 3的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 *
 * 示例 1:
 *  输入: 27
 *  输出: true
 *
 * 示例 2:
 *  输入: 0
 *  输出: false
 *
 * 示例 3:
 *  输入: 9
 *  输出: true
 *
 * 示例 4:
 *  输入: 45
 *  输出: false
 *
 * 进阶：
 *  你能不使用循环或者递归来完成本题吗？
 * */

/**
 * 使用循环
 */
fun isPowerOfThree(n: Int): Boolean {
    if (n <= 0) {
        return false
    }
    if (n == 1) {
        return true
    }
    var num = n
    while (true) {
        if (num % 3 != 0) {
            return false
        }
        num /= 3
        if (num == 1) {
            return true
        }
    }
}

/**
 * 取巧，1162261467是Int范围内最大的3的幂，3的幂次的质因子只有3
 */
fun isPowerOfThree2(n: Int): Boolean {
    return n > 0 && 1162261467 % n == 0
}

fun main() {
    println(isPowerOfThree(27))
    println(isPowerOfThree(0))
    println(isPowerOfThree(1))
    println(isPowerOfThree(-3))
    println(isPowerOfThree(9))
    println(isPowerOfThree(45))
    println(isPowerOfThree(243))
    println()
    println(isPowerOfThree2(27))
    println(isPowerOfThree2(0))
    println(isPowerOfThree2(1))
    println(isPowerOfThree2(-3))
    println(isPowerOfThree2(9))
    println(isPowerOfThree2(45))
    println(isPowerOfThree2(243))
}