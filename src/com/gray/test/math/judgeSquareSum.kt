package com.gray.test.math

/**
 * 633. 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。
 *
 * 示例1:
 * 输入: 5
 * 输出: True
 * 解释: 1 * 1 + 2 * 2 = 5
 *
 * 示例2:
 * 输入: 3
 * 输出: False
 * */
fun judgeSquareSum(c: Int): Boolean {
    for (i in 0..c) {
        val q = c - i * i
        if (q == 0) {
            return true
        }
        if (q < 0) {
            return false
        }
        val k = Math.sqrt(q.toDouble()).toInt()
        if (k * k == q) {
            return true
        }
    }
    return false
}