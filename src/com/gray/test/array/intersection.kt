package com.gray.test.array


/**
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 *
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 *
 * 说明:
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * */
import java.util.*

fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
    val short = if (nums1.size < nums2.size) nums1 else nums2
    val long = if (nums1.size < nums2.size) nums2 else nums1

    val hits = hashSetOf<Int>()
    for (i in short) {
        hits.add(i)
    }
    val ret = arrayListOf<Int>()
    for (n in long) {
        if (hits.contains(n)) {
            ret.add(n)
            hits.remove(n)
        }
    }
    return ret.toIntArray()
}

fun main() {
    println(Arrays.toString(intersection(intArrayOf(1, 2, 2, 1), intArrayOf(2, 2))))
    println(Arrays.toString(intersection(intArrayOf(), intArrayOf())))
    println(Arrays.toString(intersection(intArrayOf(4, 9, 5), intArrayOf(9, 4, 9, 8, 4))))
}
