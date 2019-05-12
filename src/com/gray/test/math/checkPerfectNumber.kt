package com.gray.test.math

/**
 * 507. 完美数
 * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
 * 给定一个 正整数 n， 如果他是完美数，返回 True，否则返回 False
 *
 * 示例：
 * 输入: 28
 * 输出: True
 * 解释: 28 = 1 + 2 + 4 + 7 + 14
 *
 * 注意:
 * 输入的数字 n 不会超过 100,000,000. (1e8)
 * */
fun checkPerfectNumber(num: Int): Boolean {
    if (num == 1) {
        return false
    }
    val max = Math.sqrt(num.toDouble()).toInt()
    var sum = 1
    for (k in max downTo 2) {
        if (num % k == 0) {
            val q = num / k
            sum += k + q
            if (sum > num) {
                break
            }
        }
    }
    return sum == num
}

fun main() {
    println(checkPerfectNumber(1))
    println(checkPerfectNumber(2))
    println(checkPerfectNumber(28))
    println(checkPerfectNumber(30))
    println(checkPerfectNumber(1e8.toInt()))
}