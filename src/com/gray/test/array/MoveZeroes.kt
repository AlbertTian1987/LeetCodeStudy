package com.gray.test.array

import java.util.*

/**
 * 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *      输入: [0,1,0,3,12]
 *      输出: [1,3,12,0,0]
 *
 * 说明:
 *      必须在原数组上操作，不能拷贝额外的数组。
 *      尽量减少操作次数。
 * */

fun moveZeroes(nums: IntArray) {
    if (nums.size < 2) {
        return
    }

    var i = 0
    for (v in nums) {
        if (v == 0) {
            break
        }
        i++
    }
    var j = nums.size - 1
    while (i < j) {
        if (nums[j] == 0) {
            var k = j
            while (k < nums.size - 1 && nums[k + 1] != 0) {
                nums[k] = nums[k + 1].also { nums[k + 1] = nums[k] }
                k++
            }
            j--
            continue
        }
        nums[i] = nums[j].also { nums[j] = nums[i] }
        j--
    }
}

fun moveZeroes2(nums: IntArray) {
    if (nums.size < 2) {
        return
    }

    var left = 0
    var right = 0

    while (right < nums.size) {
        if (nums[right] != 0) {
            if (left != right) {
                nums[left] = nums[right].also { nums[right] = nums[left] }
                left++
            } else {
                left++
            }
        }
        right++
    }
}

fun main() {
    var ints = intArrayOf(0, 1, 0, 3, 12)
    moveZeroes(ints)
    println(Arrays.toString(ints))

    ints = intArrayOf(0, 1, 0, 3, 12)
    moveZeroes2(ints)
    println(Arrays.toString(ints))

    ints = intArrayOf(0)
    moveZeroes(ints)
    println(Arrays.toString(ints))

    ints = intArrayOf(0)
    moveZeroes2(ints)
    println(Arrays.toString(ints))

    ints = intArrayOf(0, 1)
    moveZeroes(ints)
    println(Arrays.toString(ints))

    ints = intArrayOf(0, 1)
    moveZeroes2(ints)
    println(Arrays.toString(ints))


    ints = intArrayOf(1, 0)
    moveZeroes(ints)
    println(Arrays.toString(ints))

    ints = intArrayOf(1, 0)
    moveZeroes2(ints)
    println(Arrays.toString(ints))

    ints = intArrayOf(1, 0, 2, 0, 3, 0, 4, 0, 0, 5)
    moveZeroes(ints)
    println(Arrays.toString(ints))

    ints = intArrayOf(1, 0, 2, 0, 3, 0, 4, 0, 0, 5)
    moveZeroes2(ints)
    println(Arrays.toString(ints))
}