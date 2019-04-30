package com.gray.test.array

/**
 * 找出最大最小值
 * 只将”比较”操作考虑为基本操作的话，这样是最少的
 * 3/2*n - 2
 */
fun maxAndMin(array: IntArray) {
    if (array.isEmpty()) {
        return
    }
    if (array.size == 1) {
        println("max is ${array[0]}, min is ${array[0]}")
        return
    }
    val n = array.size
    val odd = (n % 2 == 1)
    val last = array.last()
    for (i in 0 until n - 1 step 2) {
        if (array[i] < array[i + 1]) {
            array[i] = array[i + 1].also { array[i + 1] = array[i] }
        }
    }
    var max = array[0]
    for (i in 2 until n - 1 step 2) {
        if (array[i] > max) {
            max = array[i]
        }
    }
    if (odd) {
        if (max < last) {
            max = last
        }
    }
    var min = array[1]
    for (i in 3 until n - 1 step 2) {
        if (array[i] < min) {
            min = array[i]
        }
    }
    if (odd) {
        if (min > last) {
            min = last
        }
    }
    println("max is $max, min is $min")
}

fun main() {
    maxAndMin(intArrayOf(1, 2, 3))
    maxAndMin(intArrayOf(1, 2, 3, 4, 5))
    maxAndMin(intArrayOf(1, 2))
    maxAndMin(intArrayOf(1, 2, 3, 4))
}