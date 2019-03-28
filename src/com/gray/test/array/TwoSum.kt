package com.gray.test.array

import java.util.*

/**
 * 两数之和
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *      给定 nums = [2, 7, 11, 15], target = 9
 *      因为 nums[0] + nums[1] = 2 + 7 = 9
 *      所以返回 [0, 1]
 *
 */


fun twoSum(nums: IntArray, target: Int): IntArray {
    var x: Int
    var y: Int
    for (i in 0 until nums.size) {
        x = nums[i]
        y = target - x
        val j = nums.indexOf(y)
        if (j >= 0 && i != j) {
            return intArrayOf(i, j)
        }
    }
    return intArrayOf()
}


fun twoSum2(nums: IntArray, target: Int): IntArray {
    val map = hashMapOf<Int, Int>()
    for ((index, value) in nums.withIndex()) {
        val visitedValue = target - value
        if (map.containsKey(visitedValue)) {
            return intArrayOf(index, map[visitedValue]!!)
        } else {
            map[value] = index
        }
    }
    return intArrayOf()
}

fun main() {
    var twoSum = twoSum2(intArrayOf(2, 7, 11, 15), 9)
    println(Arrays.toString(twoSum))
    twoSum = twoSum(intArrayOf(2, 7, 11, 15), 9)
    println(Arrays.toString(twoSum))
}