package com.gray.test.sortAndSearch

/**
 * 返回排序数组旋转后的最小值
 * 示例：
 *    数组[0,1,2,4,5]是有序的，它旋转后为[4,5,0,1,2]
 *    返回 0
 */
fun findRotateArrayMinValue(array: IntArray): Int {
    var lo = 0
    var hi = array.size - 1
    if (array[lo] < array[hi]) {
        return array[lo]
    }
    while (lo < hi) {
        val mid = lo + (hi - lo) / 2
        if (array[lo] > array[mid]) {
            hi = mid
        } else {
            lo = mid + 1
        }
    }
    return array[lo]
}

fun main() {
    println(findRotateArrayMinValue(intArrayOf(4, 5, 0, 1, 2)))
    println(findRotateArrayMinValue(intArrayOf(4, 5, 5, 1, 2, 3, 3)))
    println(findRotateArrayMinValue(intArrayOf(2, 3, 1)))
    println(findRotateArrayMinValue(intArrayOf(2, 1)))
    println(findRotateArrayMinValue(intArrayOf(1)))
    println(findRotateArrayMinValue(intArrayOf(0, 1, 2, 4, 5)))
}