package com.gray.test.backTracking

import java.util.*

/**
 * 背包问题
 * n件物品，每件有不同的重量和单价，要求不超过总重量的情况下，尽可能让背包里物品总价值最高
 * 先按照 vi/wi 排序，让单价最高的在前面，这样可以方便裁剪
 * */

fun bags2(values: IntArray, weights: IntArray, maxWeight: Int) {
    val nums = IntArray(values.size)
    var best = 0
    fun dfs(step: Int, profit: Int, leftWeight: Int) {
        if (step == values.size) {
            if (profit > best) {
                best = profit
            }
            println("${Arrays.toString(nums)}, profit is $profit, total weight is ${maxWeight - leftWeight}")
            return
        }
        println("dfs $step $profit $leftWeight")
        if (leftWeight == 0) {
            for (i in step until nums.size) {
                nums[i] = 0
            }
            if (profit >= best) {
                dfs(nums.size, profit, 0)
            } else {
                return
            }
        } else {
            val w = weights[step]
            val factor = values[step].toFloat() / w.toFloat()
            if (leftWeight * factor + profit < best) {
                return
            }

            val maxNum = leftWeight / w
            for (n in maxNum downTo 0) {
                nums[step] = n
                dfs(step + 1, profit + n * values[step], leftWeight - w * n)
            }
        }
    }
    dfs(0, 0, maxWeight)
}

fun main() {
    bags2(intArrayOf(9, 5, 3, 1), intArrayOf(7, 4, 3, 2), 10)
}