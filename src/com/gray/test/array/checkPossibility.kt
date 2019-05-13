package com.gray.test.array

/**
 * 665. 非递减数列
 * 给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 array[i ] <= array[i + 1]。
 *
 * 示例 1:
 * 输入: [4,2,3]
 * 输出: True
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 *
 * 示例 2:
 * 输入: [4,2,1]
 * 输出: False
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *
 * 说明:  n 的范围为 [1, 10,000]。
 * */

/**
 * 思路，当前的和右边的比
 * 在发生逆序的处进行处理
 * 如果是在开始处，比如 4,3,.... 那只能将nums[ 0] = nums[1]
 * 如果在中间，有两种处理方法，，缩小nums[ i]的值或者放大nums[i+1]的值
 *  1.  ...2,4,3,.... 4/3逆序，2 < 3 ，将4改成2
 *  2.  ...2,3,1,.... 3/1逆序，2>1, 将1变成3
 */
fun checkPossibility(nums: IntArray): Boolean {
    if (nums.size < 3) {
        return true
    }
    var count = 0
    for (i in 0 until nums.size - 1) {
        val right = nums[i + 1]
        if (nums[i] > right) {
            count++
            if (count > 1) {
                return false
            }
            if (i == 0) {
                nums[0] = nums[1]
                continue
            }
            val left = nums[i - 1]
            if (left <= right) {
                nums[i] = left
            } else {
                nums[i + 1] = nums[i]
            }
        }
    }
    return true
}

fun main() {
    println(checkPossibility(intArrayOf(1, 2, 3)))
}