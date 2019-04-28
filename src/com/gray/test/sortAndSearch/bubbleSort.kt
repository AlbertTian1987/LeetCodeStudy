package com.gray.test.sortAndSearch

import java.util.*

/**
 * 冒泡排序
 * */

fun bubbleSort(array: IntArray) {
    for (i in array.size - 1 downTo 0) {
        for (j in 0 until i) {
            if (array[j] > array[j + 1]) {
                array[j] = array[j + 1].also { array[j + 1] = array[j] }
            }
        }
    }
}

fun testBubbleSort(array: IntArray) {
    bubbleSort(array)
    println(Arrays.toString(array))
}

fun main() {
    testBubbleSort(intArrayOf(1, 2, 3, 4, 5, 2))
    testBubbleSort(intArrayOf(6, 5, 4, 3))
    testBubbleSort(intArrayOf(6, 5, 4))
    testBubbleSort(intArrayOf())
}