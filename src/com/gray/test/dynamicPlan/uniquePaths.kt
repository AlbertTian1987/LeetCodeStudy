package com.gray.test.dynamicPlan

/***
 * 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 *
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 */
/**
 * dp[i][ j] = dp[i-1][j ] + dp[i][j-1]
 * 一个格子只能从它左边来，或者从上面下来。
 * 第一个格子dp[0][0]为1
 */
fun uniquePaths(m: Int, n: Int): Int {
    val dp = Array(m) { IntArray(n) }
    for (i in 0 until m) {
        for (j in 0 until n) {
            if (i == 0 && j == 0) {
                dp[i][j] = 1
                continue
            }
            val left = if (i - 1 < 0) {
                0
            } else {
                dp[i - 1][j]
            }
            val top = if (j - 1 < 0) {
                0
            } else {
                dp[i][j - 1]
            }
            dp[i][j] = left + top
        }
    }
    return dp[m - 1][n - 1]
}

fun main() {
    println(uniquePaths(7, 3))
}