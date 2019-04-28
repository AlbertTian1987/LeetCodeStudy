package com.gray.test.sortAndSearch

import java.util.*

/**
 * 归并排序
 *
 * 1. 将数组分半
 * 2. 得到两个排好序的数组
 * 3. 将两个排好序的数组合并起来
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
    for (index in l..r) {
        array[index] = temp[index - l]
    }
}

fun main() {
    val array = intArrayOf(3, 2, 1, 7, 8, 0, 11, 3, 5, 9, 2, 19, 99, 8, 37, 5)
    mergeSort(array)
    println(Arrays.toString(array))
}