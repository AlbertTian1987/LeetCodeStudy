package com.gray.test.array

import java.util.*

/**
 * 27. 移除元素
 *
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1:
 *  给定 nums = [3,2,2,3], val = 3,
 *  函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *  你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2:
 *  给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 *  函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 *  注意这五个元素可为任意顺序。
 *  你不需要考虑数组中超出新长度后面的元素。
 */

fun removeElement(nums: IntArray, `val`: Int): Int {
    var n = nums.size
    var i = 0
    while (i < n) {
        if (nums[i] == `val`) {
            var step = 1
            for (j in i + 1 until n) {
                if (nums[j] == `val`) {
                    step++
                } else {
                    nums[j - step] = nums[j]
                }
            }
            n -= step
        } else {
            i++
        }
    }
    return n
}

fun removeElement2(nums: IntArray, `val`: Int): Int {
    var slow = 0
    for (quick in 0 until nums.size) {
        if (nums[quick] != `val`) {
            nums[slow++] = nums[quick]
        }
    }
    return slow
}

fun testRemoveElement(nums: IntArray, `val`: Int) {
    val i = removeElement2(nums, `val`)
    println("after remove size is $i, array is " + Arrays.toString(nums))
}

fun main() {
    testRemoveElement(intArrayOf(3, 2, 2, 3), 3)
    testRemoveElement(intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), 2)
    testRemoveElement(intArrayOf(2), 2)
}