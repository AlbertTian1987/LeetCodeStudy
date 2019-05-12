package com.gray.test.array

import java.util.*

/***
 * 496. 下一个更大元素 I
 *
 * 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出-1。
 *
 * 示例 1:
 *  输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 *  输出: [-1,3,-1]
 * 解释:
 *  对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 *  对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 *  对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 *
 * 示例 2:
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *  对于num1中的数字2，第二个数组中的下一个较大数字是3。
 *  对于num1中的数字4，第二个数组中没有下一个更大的数字，因此输出 -1。
 *
 * 注意:
 * nums1和nums2中所有元素是唯一的。
 * nums1和nums2 的数组大小都不超过1000。
 */

fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
    if (nums1.isEmpty()) {
        return nums1
    }
    val dict = hashMapOf<Int, Int>()
    val stack = LinkedList<Int>()
    stack.push(nums2[0])
    for (i in 1 until nums2.size) {
        val cur = nums2[i]
        while (stack.isNotEmpty() && cur > stack.peek()) {
            val pop = stack.pop()!!
            dict[pop] = cur
        }
        stack.push(cur)
    }
    for ((i, n) in nums1.withIndex()) {
        nums1[i] = dict.getOrDefault(n, -1)
    }
    return nums1
}

/**
 * 这是之前自己的写法，nums2从后向前遍历，最后一个的更大值是-1，借助map存储对应关系map[ value] = nextGreater
 * 当前是 num[ i] 它小于 num[ i+1]时，它的map[ num[ i]] = num[ i+1]
 * 如果不是，那查询 num[ i+1]的更大值，之前已经存在map里了，找到比num[ i]大的，或者是-1
 *
 * 看过别人的写法，用一个栈来辅助，也可以实现，感觉更易懂
 */
fun nextGreaterElement2(nums1: IntArray, nums2: IntArray): IntArray {
    if (nums1.isEmpty()) {
        return nums1
    }
    val dict = hashMapOf<Int, Int>()
    dict[nums2.last()] = -1
    for (i in nums2.size - 2 downTo 0) {
        val cur = nums2[i]
        var next = nums2[i + 1]
        if (cur < next) {
            dict[cur] = next
            continue
        }
        while (dict[next] != -1 && dict[next]!! < cur) {
            next = dict[next]!!
        }
        dict[cur] = dict[next]!!
    }
    for ((i, n) in nums1.withIndex()) {
        nums1[i] = dict[n]!!
    }
    return nums1
}

fun main() {
    println(Arrays.toString(nextGreaterElement(intArrayOf(4, 1, 2), intArrayOf(1, 3, 4, 2))))
    println(Arrays.toString(nextGreaterElement(intArrayOf(2, 4), intArrayOf(1, 2, 3, 4))))
    println(Arrays.toString(nextGreaterElement(intArrayOf(1, 3, 5, 2, 4), intArrayOf(6, 5, 4, 3, 2, 1, 7))))
}