package com.gray.test.sortAndSearch

import java.util.*

/**
 * 选择排序
 */

fun selectionSort(array: IntArray) {

    for (i in 0 until array.size) {
        var min = array[i]
        var index = i
        for (j in i + 1 until array.size) {
            if (array[j] < min) {
                min = array[j]
                index = j
            }
        }
        if (index != i) {
            array[i] = array[index].also { array[index] = array[i] }
        }
    }
}

fun testSelectionSort(array: IntArray) {
    selectionSort(array)
    println(Arrays.toString(array))
}

fun main() {
    testSelectionSort(intArrayOf(1, 2, 3, 4, 5, 2))
    testSelectionSort(intArrayOf(6, 5, 4, 3))
    testSelectionSort(intArrayOf(6, 5, 4))
    testSelectionSort(intArrayOf())
}
