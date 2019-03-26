package com.gray.test.array

/**
 * 存在重复
 *
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 *
 * 示例 1:
 *      输入: [1,2,3,1]
 *      输出: true
 *
 * 示例 2:
 *      输入: [1,2,3,4]
 *      输出: false
 *
 * 示例 3:
 *      输入: [1,1,1,3,3,4,3,2,4,2]
 *      输出: true
 */

fun containsDuplicate(nums: IntArray): Boolean {
    if (nums.size < 2) {
        return false
    }

    nums.sort()
    for (i in 0..nums.size - 2) {
        if (nums[i] == nums[i + 1]) {
            return true
        }
    }
    return false
}

fun containsDuplicate2(nums: IntArray): Boolean {
    if (nums.size < 2) {
        return false
    }

    val set = hashSetOf<Int>()
    nums.forEach {
        if (set.contains(it)) {
            return true
        } else {
            set.add(it)
        }
    }

    return false
}

fun main() {
    println(containsDuplicate(intArrayOf(1, 2, 3, 1)))
    println(containsDuplicate(intArrayOf(1, 2, 3, 4)))
    println(containsDuplicate(intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)))

    println(containsDuplicate2(intArrayOf(1, 2, 3, 1)))
    println(containsDuplicate2(intArrayOf(1, 2, 3, 4)))
    println(containsDuplicate2(intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)))

}