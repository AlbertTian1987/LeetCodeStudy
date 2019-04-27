package com.gray.test.sortAndSearch

import java.util.*

/**
 * 插入排序
 * */

fun insertSort(array: IntArray) {
    var j = 1
    while (j < array.size) {
        val value = array[j]
        var insertIndex = j
        while ((insertIndex - 1) >= 0 && array[insertIndex - 1] > value) {
            insertIndex--
        }
        if (insertIndex != j) {
            for (i in j downTo insertIndex + 1) {
                array[i] = array[i - 1]
            }
            array[insertIndex] = value
        }
        j++
    }
}

fun main() {
    val array = intArrayOf(2, 3, 1, 5, 7, 4, 9)
    insertSort(array)
    println(Arrays.toString(array))
}