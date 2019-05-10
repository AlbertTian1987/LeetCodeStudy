package com.gray.test.array

/**
 * 303. 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * 示例：
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 *
 * 说明:
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 * */

class NumArray(nums: IntArray) {
    private val sum = IntArray(nums.size)

    init {
        if (nums.isNotEmpty()) {
            sum[0] = nums[0]
            for (i in 1 until nums.size) {
                sum[i] = sum[i - 1] + nums[i]
            }
        }
    }

    fun sumRange(i: Int, j: Int): Int {
        return if (i == 0) {
            sum[j]
        } else {
            sum[j] - sum[i - 1]
        }
    }
}

fun main() {
    val numArray = NumArray(intArrayOf(-2, 0, 3, -5, 2, -1))
    println(numArray.sumRange(0, 2))
    println(numArray.sumRange(2, 5))
    println(numArray.sumRange(0, 5))
}