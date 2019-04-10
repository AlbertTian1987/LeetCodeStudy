package com.gray.test.sortAndSearch

import java.util.*

/**
 * 合并两个有序数组
 *
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 示例:
 *
 *  输入:
 *      nums1 = [1,2,3,0,0,0], m = 3
 *      nums2 = [2,5,6],       n = 3
 *
 * 输出:
 *      [1,2,2,3,5,6]
 *
 * */

/**
 * 自己的解法，太笨
 */
fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
    if (m == 0) {
        System.arraycopy(nums2, 0, nums1, 0, n)
        return
    }
    if (n == 0) {
        return
    }
    if (nums1[0] > nums2[n - 1]) {
        System.arraycopy(nums1, 0, nums1, n, m)
        repeat(n) {
            nums1[it] = nums2[it]
        }
        return
    }
    if (nums1[m - 1] < nums2[0]) {
        repeat(n) {
            nums1[m + it] = nums2[it]
        }
        return
    }


    var i = 0
    var size: Int
    while (i < n) {
        val newValue = nums2[i]
        size = m + i
        val insertIndex = getInsertIndex(0, size - 1, newValue, nums1)
        when (insertIndex) {
            size -> nums1[insertIndex] = newValue
            else -> {
                System.arraycopy(nums1, insertIndex, nums1, insertIndex + 1, size - insertIndex)
                nums1[insertIndex] = newValue
            }
        }
        i++
    }
}

fun getInsertIndex(lo: Int, hi: Int, value: Int, nums: IntArray): Int {
    if (lo >= hi) {
        return if (nums[lo] >= value) {
            lo
        } else {
            lo + 1
        }
    }
    while (lo < hi) {
        val mid = (lo + hi) / 2
        val i = nums[mid]
        if (i == value) {
            return mid + 1
        }
        return if (i < value) {
            getInsertIndex(mid + 1, hi, value, nums)
        } else {
            getInsertIndex(lo, mid - 1, value, nums)
        }
    }
    return hi + 1
}


fun merge2(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
    var index1 = m - 1
    var index2 = n - 1
    var indexTotal = m + n - 1
    while (true) {
        if (index2 < 0) {
            return
        }
        if (index1 < 0) {
            nums1[indexTotal] = nums2[index2]
            indexTotal--
            index2--
            continue
        }

        if (nums1[index1] < nums2[index2]) {
            nums1[indexTotal] = nums2[index2]
            index2--
            indexTotal--
        } else {
            nums1[indexTotal] = nums1[index1]
            index1--
            indexTotal--
        }
    }
}

fun main() {
    var num1 = intArrayOf(1, 2, 3, 0, 0, 0)
    var num2 = intArrayOf(2, 5, 6)
    merge(num1, 3, num2, 3)
    println(Arrays.toString(num1))

    num1 = intArrayOf(1, 2, 3, 0, 0, 0)
    num2 = intArrayOf(2, 5, 6)
    merge2(num1, 3, num2, 3)
    println(Arrays.toString(num1))


    num1 = intArrayOf(4, 5, 6, 0, 0, 0)
    num2 = intArrayOf(1, 2, 3)
    merge(num1, 3, num2, 3)
    println(Arrays.toString(num1))

    num1 = intArrayOf(4, 5, 6, 0, 0, 0)
    num2 = intArrayOf(1, 2, 3)
    merge2(num1, 3, num2, 3)
    println(Arrays.toString(num1))
}