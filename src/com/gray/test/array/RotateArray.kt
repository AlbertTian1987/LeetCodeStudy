package com.gray.test.array

import java.lang.IllegalArgumentException
import java.util.*

/**
 * 旋转数组
 *
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *      输入: [1,2,3,4,5,6,7] 和 k = 3
 *      输出: [5,6,7,1,2,3,4]
 * 解释:
 *      向右旋转 1 步: [7,1,2,3,4,5,6]
 *      向右旋转 2 步: [6,7,1,2,3,4,5]
 *      向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 * 示例 2:
 *      输入: [-1,-100,3,99] 和 k = 2
 *      输出: [3,99,-1,-100]
 * 解释:
 *      向右旋转 1 步: [99,-1,-100,3]
 *      向右旋转 2 步: [3,99,-1,-100]
 *
 * 说明:
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的原地算法。
 */

fun rotate(nums: IntArray, k: Int) {

    if (k == 0) {
        return
    }

    val size = nums.size

    val realK = if (k == size) {
        k
    } else {
        k % size
    }

    if (size <= 1 || size == realK) {
        return
    }

    val maxCommonDivider = maxCommonDivider(size, realK)
    repeat(maxCommonDivider){i->
        var index = i
        var temp = nums[index]
        repeat(size/maxCommonDivider) {
            val nextIndex = (index + realK) % size
            val v = nums[nextIndex]
            nums[nextIndex] = temp
            temp = v
            index = nextIndex
        }
    }
}

fun maxCommonDivider(m:Int,n:Int):Int{
    if (m == 0 || n == 0){
        throw IllegalArgumentException("args can not be zero")
    }
    var mm = m
    var nn = n

    if (mm < nn){
        val temp = mm
        mm = nn
        nn = temp
    }

    while (mm % nn != 0){
        val temp = mm % nn
        mm = nn
        nn= temp
    }
    return nn
}

fun main() {

    var nums = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    rotate(nums, 3)
    println(Arrays.toString(nums))

    nums = intArrayOf(1, 2)
    rotate(nums, 2)
    println(Arrays.toString(nums))

    nums = intArrayOf(-1, -100, 3, 99)
    rotate(nums, 2)
    println(Arrays.toString(nums))


    nums = intArrayOf(1, 2, 3)
    rotate(nums, 2)
    println(Arrays.toString(nums))


    nums = intArrayOf(1, 2, 3, 4, 5, 6)
    rotate(nums, 1)
    println(Arrays.toString(nums))

    nums = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    rotate(nums, 6)
    println(Arrays.toString(nums))

    nums = intArrayOf(1, 2, 3, 4, 5, 6)
    rotate(nums, 4)
    println(Arrays.toString(nums))

}