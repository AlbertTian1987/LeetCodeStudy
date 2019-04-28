package com.gray.test.sortAndSearch

import java.util.*
import kotlin.math.min

/**
 * 归并排序
 *
 * 1. 将数组分半
 * 2. 得到两个排好序的数组
 * 3. 将两个排好序的数组合并起来
 *
 * O(nlogn)
 * */

fun mergeSort(array: IntArray) {
    val temp = IntArray(array.size)
    mergeSort(array, 0, array.size - 1, temp)
}

fun mergeSort(array: IntArray, lo: Int, hi: Int, temp: IntArray) {
    if (lo >= hi) {
        return
    }
    val mid = (lo + hi) / 2
    mergeSort(array, lo, mid, temp)
    mergeSort(array, mid + 1, hi, temp)
    mergeSorted(array, lo, mid, hi, temp)
}

fun mergeSorted(array: IntArray, l: Int, mid: Int, r: Int, temp: IntArray) {
    var k = 0
    var i = l
    var j = mid + 1
    while (i <= mid || j <= r) {
        if (i > mid) {
            temp[k++] = array[j++]
            continue
        }
        if (j > r) {
            temp[k++] = array[i++]
            continue
        }
        if (array[i] < array[j]) {
            temp[k++] = array[i++]
        } else {
            temp[k++] = array[j++]
        }
    }
    System.arraycopy(temp, 0, array, l, r - l + 1)
}

/**
 * 使用迭代实现
 */
fun mergeSort2(array: IntArray) {
    val temp = IntArray(array.size)
    val size = array.size
    var step = 1
    while (step <= size) {
        val offset = step + step
        for (index in 0 until size step offset) {
            val lo = index
            val mid = min(index + step - 1, size - 1)
            val hi = min(index + offset - 1, size - 1)
            mergeSorted(array, lo, mid, hi, temp)
        }
        step = step shl 1
    }
}

fun main() {
    val array = intArrayOf(3, 2, 1, 7, 8, 0, 11, 3, 5, 9, 2, 19, 99, 8, 37, 5, 9)
    mergeSort(array)
    println(Arrays.toString(array))

    val array2 = intArrayOf(3, 2, 1, 7, 8, 0, 11, 3, 5, 9, 2, 19, 99, 8, 37, 5, 9)
    mergeSort2(array2)
    println(Arrays.toString(array2))
}