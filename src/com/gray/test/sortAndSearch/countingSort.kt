package com.gray.test.sortAndSearch

import java.util.*

/**
 * 计数排序
 * 该排序算法基于一个前提，即数组里所有元素都是正整数，且最大值为k
 * 它的时间复杂度为 O(k+n) 当k为一个小值时有O(n)的良好性能
 *
 * 1.开辟一个长度为k的计数数组C[ k]
 * 2.从头遍历数组A，A[ i]=j 就将C[ j]对应的数值+1
 * 3.从头遍历数组C，C[ i] = C[i-1]+C[ i]
 * 4.生成最终数组B
 * 5.从尾遍历数组A，A[ i]=j ，得到C[ j]的值pos,B[ pos] = j,C[ j]--
 *
 */

fun countingSort(a: IntArray, k: Int) {
    val C = IntArray(k)
    //O(n)
    for (i in a) {
        C[i - 1]++
    }
    //O(k)
    for (i in 1 until k) {
        C[i] = C[i - 1] + C[i]
    }
    val B = IntArray(a.size)
    //O(n)
    for (i in a.size - 1 downTo 0) {
        val j = a[i]
        val pos = C[j - 1] - 1
        C[j - 1]--
        B[pos] = j
    }
    System.arraycopy(B, 0, a, 0, B.size)
}

fun testCountingSort(a: IntArray, k: Int) {
    countingSort(a, k)
    println(Arrays.toString(a))
}

fun main() {
    testCountingSort(intArrayOf(3, 1, 2, 7, 8, 3, 5, 16, 25, 17, 22, 31, 20, 22, 27, 28, 39), 39)
}
