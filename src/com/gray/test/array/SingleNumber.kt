package com.gray.test.array

/**
 * 只出现一次的数字
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *      你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *      输入: [2,2,1]
 *      输出: 1
 *
 * 示例 2:
 *      输入: [4,1,2,1,2]
 *      输出: 4
 *
 * */

fun singleNumber(nums: IntArray): Int {
    if (nums.size == 1) {
        return nums[0]
    }
    nums.sort()
    var i = 0
    while (i < nums.size - 2) {
        if (nums[i] == nums[i + 1]) {
            i += 2
            continue
        }
        if (nums[i + 2] == nums[i + 1]) {
            return nums[i]
        }
    }
    return nums.last()
}

fun singleNumber2(nums: IntArray): Int {
    var result = 0
    nums.forEach {
        result = result xor it
    }
    return result
}

fun main() {

    println(singleNumber(intArrayOf(2, 2, 1)))
    println(singleNumber(intArrayOf(4, 1, 2, 1, 2)))

    println(singleNumber2(intArrayOf(2, 2, 1)))
    println(singleNumber2(intArrayOf(4, 1, 2, 1, 2)))

}