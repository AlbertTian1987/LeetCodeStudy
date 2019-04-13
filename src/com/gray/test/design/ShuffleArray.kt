package com.gray.test.design

/**
 * Shuffle an Array
 * 打乱一个没有重复元素的数组。
 *
 * 示例:
 * // 以数字集合 1, 2 和 3 初始化数组。
 *      int[] nums = {1,2,3};
 *      Solution solution = new Solution(nums);
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 *      solution.shuffle();
 * // 重设数组到它的初始状态[1,2,3]。
 *      solution.reset();
 * // 随机返回数组[1,2,3]打乱后的结果。
 *      solution.shuffle();
 * */

import java.util.*

class Solution(nums: IntArray) {
    private val originalArray = nums
    private val array = IntArray(nums.size)
    private val random = Random()

    init {
        System.arraycopy(nums, 0, array, 0, nums.size)
    }

    /** Resets the array to its original configuration and return it. */
    fun reset(): IntArray = originalArray

    /** Returns a random shuffling of the array. */
    fun shuffle(): IntArray {
        val size = array.size
        repeat(size / 2) {
            val p1 = random.nextInt(size)
            val p2 = random.nextInt(size)
            array[p1] = array[p2].also { array[p2] = array[p1] }
        }
        return array
    }
}

fun main() {
    val nums = intArrayOf(1, 2, 3)
    val solution = Solution(nums)
    println(Arrays.toString(solution.shuffle()))
    println(Arrays.toString(solution.reset()))
    println(Arrays.toString(solution.shuffle()))
}