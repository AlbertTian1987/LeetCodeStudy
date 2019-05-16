package com.gray.test.math

/***
 * 762. 二进制表示中质数个计算置位
 * 给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。
 * （注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）
 *
 * 示例 1:
 * 输入: L = 6, R = 10
 * 输出: 4
 * 解释:
 * 6 -> 110 (2 个计算置位，2 是质数)
 * 7 -> 111 (3 个计算置位，3 是质数)
 * 9 -> 1001 (2 个计算置位，2 是质数)
 * 10-> 1010 (2 个计算置位，2 是质数)
 *
 * 示例 2:
 * 输入: L = 10, R = 15
 * 输出: 5
 * 解释:
 * 10 -> 1010 (2 个计算置位, 2 是质数)
 * 11 -> 1011 (3 个计算置位, 3 是质数)
 * 12 -> 1100 (2 个计算置位, 2 是质数)
 * 13 -> 1101 (3 个计算置位, 3 是质数)
 * 14 -> 1110 (3 个计算置位, 3 是质数)
 * 15 -> 1111 (4 个计算置位, 4 不是质数)
 *
 * 注意:
 * L, R 是 L <= R 且在 [1, 10^6] 中的整数。
 * R - L 的最大值为 10000。
 */
fun countPrimeSetBits(L: Int, R: Int): Int {
    val primes = intArrayOf(2, 3, 5, 7, 11, 13, 17, 19)
    val isPrime = hashMapOf<Int, Boolean>()
    (0..20).forEach {
        isPrime[it] = it in primes
    }
    return (L..R).map { countNum1(it) }.filter { isPrime[it]!! }.count()
}

private fun countNum1(n: Int): Int {
    var p = n
    var ret = 0
    while (p != 0) {
        if (p % 2 != 0) {
            ret++
        }
        p = p ushr 1
    }
    return ret
}

fun main() {
    println(countPrimeSetBits(1, 1e6.toInt()))
    println(countPrimeSetBits(10, 15))
}