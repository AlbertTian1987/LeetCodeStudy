package com.gray.test.array

import java.util.*

/**
 * 两个数组的交集 II
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *      输入: nums1 = [1,2,2,1], nums2 = [2,2]
 *      输出: [2,2]
 *
 * 示例 2:
 *      输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *      输出: [4,9]
 *
 * 说明：
 *      输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 *      我们可以不考虑输出结果的顺序。
 *
 * 进阶:
 *      如果给定的数组已经排好序呢？你将如何优化你的算法？
 *      如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 *      如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * */

fun intersect(nums1: IntArray, nums2: IntArray): IntArray {

    if (nums1.isEmpty() || nums2.isEmpty()) {
        return intArrayOf()
    }

    val shortArray: IntArray
    val longArray: IntArray
    if (nums1.size < nums2.size) {
        shortArray = nums1
        longArray = nums2
    } else {
        shortArray = nums2
        longArray = nums1
    }
    val result = mutableListOf<Int>()

    val map = hashMapOf<Int, Int>()
    shortArray.forEach { map[it] = (map[it] ?: 0) + 1 }

    for (i in longArray) {
        map[i]?.let { count ->
            result.add(i)
            if (count - 1 > 0) {
                map[i] = count - 1
            } else {
                map.remove(i)
            }
        }
        if (map.isEmpty()) {
            break
        }
    }

    return result.toIntArray()
}

/**
 * 对已经排序的队列进行操作
 */
fun intersectSortedArray(nums1: IntArray, nums2: IntArray): IntArray {

    if (nums1.isEmpty() || nums2.isEmpty()) {
        return intArrayOf()
    }
    nums1.sort()
    nums2.sort()

    val result = mutableListOf<Int>()

    var i = 0
    var j = 0
    while (i < nums1.size && j < nums2.size) {
        val v1 = nums1[i]
        val v2 = nums2[j]
        when {
            v1 == v2 -> {
                result.add(v1)
                i++
                j++
            }
            v1 < v2 -> i++
            else -> j++
        }
    }
    return result.toIntArray()
}

fun main() {

    var nums1 = intArrayOf(1, 2, 2, 1)
    var nums2 = intArrayOf(2, 2)
    println(Arrays.toString(intersect(nums1, nums2)))
    println(Arrays.toString(intersectSortedArray(nums1, nums2)))

    nums1 = intArrayOf(4, 9, 5)
    nums2 = intArrayOf(9, 4, 9, 8, 4)
    println(Arrays.toString(intersect(nums1, nums2)))
    println(Arrays.toString(intersectSortedArray(nums1, nums2)))
}