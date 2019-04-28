package com.gray.test.sortAndSearch

import java.util.*

/**
 * 快速排序
 * 它的本质是递归着划分
 * */

/**
 * 划分算法
 * 1. 选着array[p]为pivot，i初始值为p, j 初始值为p+1
 * 2. 循环遍历，将遇到的小于pivot的值放到i的左边，i..j之间的是大于pivot的值，j..q是未知
 * 3. 最后将pivot和i的位置交换
 */
fun partion(array: IntArray, p: Int, q: Int): Int {
    val pivot = array[p]
    var i = p
    var j = p + 1
    while (j <= q) {
        if (array[j] < pivot) {
            i++
            array[i] = array[j].also { array[j] = array[i] }
        }
        j++
    }
    array[p] = array[i].also { array[i] = array[p] }
    return i
}

fun quickSort(array: IntArray, p: Int, q: Int) {
    if (p >= q) {
        return
    }
    val r = partion(array, p, q)
    quickSort(array, p, r - 1)
    quickSort(array, r + 1, q)
}

fun test(array: IntArray) {
    quickSort(array, 0, array.size - 1)
    println(Arrays.toString(array))
}

fun main() {
    test(intArrayOf(3, 2, 1, 7, 8, 0, 11, 3, 5, 9, 2, 19, 99, 8, 37, 5, 9))
    test(intArrayOf())
    test(intArrayOf(3))
    test(intArrayOf(3, 2))
    test(intArrayOf(3, 2, 9))
}

