package com.gray.test.array

import kotlin.math.min

/**
 *
 * 第K小，第k大
 * k从0开始计算
 *
 * */

fun getPivot(array: IntArray): Int {
    val subArraySize = 5
    var mediansSize = array.size / subArraySize
    if (array.size.rem(subArraySize) != 0) {
        mediansSize++
    }
    val mediansArray = IntArray(mediansSize)
    for (i in 0 until mediansSize) {
        val start = i * subArraySize
        val end = min(start + subArraySize, array.size)
        val temp = IntArray(end - start)
        System.arraycopy(array, start, temp, 0, temp.size)
        mediansArray[i] = getKMin(temp, temp.size / 2)
    }
    return getKMin(mediansArray, mediansSize / 2)
}

fun splitArrayByPivot(array: IntArray, pivot: Int, lt: ArrayList<Int>, eq: ArrayList<Int>, gt: ArrayList<Int>) {
    for (i in array) {
        when {
            i == pivot -> eq.add(i)
            i < pivot -> lt.add(i)
            else -> gt.add(i)
        }
    }
}

tailrec fun getKMin(intArray: IntArray, k: Int): Int {
    if (intArray.size <= 10) {
        intArray.sort()
        return intArray[k]
    }
    val pivot = getPivot(intArray)
    val lt = ArrayList<Int>()
    val eq = ArrayList<Int>()
    val gt = ArrayList<Int>()
    splitArrayByPivot(intArray, pivot, lt, eq, gt)
    return when {
        k < lt.size -> getKMin(lt.toIntArray(), k)
        k < (lt.size + eq.size) -> eq.first()
        else -> getKMin(gt.toIntArray(), k - lt.size - eq.size)
    }
}

tailrec fun getKMax(intArray: IntArray, k: Int): Int {
    val pivot = getPivot(intArray)
    val lt = ArrayList<Int>()
    val eq = ArrayList<Int>()
    val gt = ArrayList<Int>()
    splitArrayByPivot(intArray, pivot, lt, eq, gt)
    return when {
        k < gt.size -> getKMax(gt.toIntArray(), k)
        k < (gt.size + eq.size) -> eq.first()
        else -> getKMax(lt.toIntArray(), k - gt.size - eq.size)
    }
}

fun main() {
    //[1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 8, 9, 9, 13, 13, 15, 16, 42, 598]
    for (i in 0..23) {
        println(getKMin(intArrayOf(8, 9, 9, 3, 2, 1, 3, 2, 1, 2, 3, 4, 2, 13, 1, 4, 1, 13, 2, 598, 3, 15, 16, 42), i))
    }
    println()
    for (i in 0..23) {
        println(getKMax(intArrayOf(8, 9, 9, 3, 2, 1, 3, 2, 1, 2, 3, 4, 2, 13, 1, 4, 1, 13, 2, 598, 3, 15, 16, 42), i))
    }
}
