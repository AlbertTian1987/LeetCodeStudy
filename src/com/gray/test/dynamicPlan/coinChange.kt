package com.gray.test.dynamicPlan

import kotlin.math.min

/***
 * 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 */
fun coinChange(coins: IntArray, amount: Int): Int {
    val n = coins.size
    val dp = IntArray(amount + 1) { -1 }
    dp[0] = 0
    for (money in 1..amount) {
        var d = Int.MAX_VALUE
        for (k in 0 until n) {
            val coin = coins[k]
            var j = 1
            while (money - j * coin >= 0) {
                val pre = money - j * coin
                if (dp[pre] != -1) {
                    d = min(d, j + dp[pre])
                }
                j++
            }
        }
        dp[money] = if (d == Int.MAX_VALUE) -1 else d
    }
    return dp[amount]
}

fun main() {
    println(coinChange(intArrayOf(1, 2, 5), 12))
    println(coinChange(intArrayOf(2), 3))
    println(coinChange(intArrayOf(2), 4))
    println(coinChange(intArrayOf(2), 5))
}