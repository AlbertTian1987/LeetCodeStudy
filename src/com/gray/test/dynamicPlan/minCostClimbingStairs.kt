package com.gray.test.dynamicPlan

import kotlin.math.min


/***
 * 746. 使用最小花费爬楼梯
 * 数组的每个索引做为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 *
 * 示例 1:
 * 输入: cost = [10, 15, 20]
 * 输出: 15
 * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 *
 * 示例 2:
 * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出: 6
 * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 *
 * 注意：
 * cost 的长度将会在 [2, 1000]。
 * 每一个 cost[ i] 将会是一个Integer类型，范围为 [0, 999]。
 */
/**
 * 思路：
 * 当前如果是房顶的话
 * lastOne代表之前爬到离房顶还有1层台阶需要的花费
 * lastTwo代表之前爬到离房顶还有2层台阶需要的花费
 * 所以到房顶的最小花费是 lastOne+最后1级台阶 ， lastTwo和倒数第二级台阶 里较小的
 * 接着再往上走一层，之前的房顶变成lastOne，lastOne变成lastTwo
 */
fun minCostClimbingStairs(cost: IntArray): Int {
    var lastOne = 0
    var lastTwo = 0
    for (n in 2..cost.size) {
        lastOne = min(cost[n - 1] + lastOne, cost[n - 2] + lastTwo).also { lastTwo = lastOne }

    }
    return lastOne
}

fun main() {
    println(minCostClimbingStairs(intArrayOf(10, 15, 20)))
    println(minCostClimbingStairs(intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1)))
    println(minCostClimbingStairs(intArrayOf(1, 100)))
    println(minCostClimbingStairs(intArrayOf(1, 100, 1)))
    println(minCostClimbingStairs(intArrayOf(0, 1, 2, 2)))
}