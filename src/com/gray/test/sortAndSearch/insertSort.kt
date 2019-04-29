package com.gray.test.sortAndSearch

import java.util.*

/**
 * 插入排序
 * */

fun insertSort(array: IntArray) {
    var j = 1
    while (j < array.size) {
        val value = array[j]
        var i = j - 1
        while (i >= 0 && array[i] > value) {
            array[i + 1] = array[i]
            i--
        }
        array[i + 1] = value
        j++
    }
}

fun main() {
    val array = intArrayOf(2, 3, 1, 5, 7, 4, 9)
    insertSort(array)
    println(Arrays.toString(array))
}