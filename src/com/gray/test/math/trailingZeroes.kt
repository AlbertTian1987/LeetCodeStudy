package com.gray.test.math

/**
 * 172. 阶乘后的零
 *
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * 注意，这道题所说的尾数是指结尾部分，结果中间夹杂的0不算
 * 末尾要有0，比如得有5*偶数，偶数肯定比5多，那么数5就行
 *
 * 示例 1:
 *  输入: 3
 *  输出: 0
 *  解释: 3! = 6, 尾数中没有零。
 *
 * 示例 2:
 *  输入: 5
 *  输出: 1
 *  解释: 5! = 120, 尾数中有 1 个零.
 *
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 * */

fun trailingZeroes(n: Int): Int {
    var ret = 0
    var num = n
    while (num > 1) {
        num /= 5
        ret += num
    }
    return ret
}

fun main() {
    println(trailingZeroes(5))
    println(trailingZeroes(3))
    println(trailingZeroes(31))
}