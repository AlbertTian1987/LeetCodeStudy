package com.gray.test.array

import java.util.*

/**
 * 加一
 *
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *      输入: [1,2,3]
 *      输出: [1,2,4]
 *      解释: 输入数组表示数字 123。
 *
 * 示例 2:
 *      输入: [4,3,2,1]
 *      输出: [4,3,2,2]
 *      解释: 输入数组表示数字 4321。
 *
 * */

fun plusOne(digits: IntArray): IntArray {
    var i = digits.size - 1
    var dx = 1
    while (i >= 0) {
        var value = digits[i] + dx
        if (value >= 10) {
            value -= 10
            dx = 1
        } else {
            dx = 0
        }
        digits[i] = value
        i--
    }
    return if (dx == 0) {
        digits
    } else {
        IntArray(1 + digits.size) {
            if (it == 0) {
                dx
            } else {
                digits[it - 1]
            }
        }
    }
}

//更好的算法
fun plusOne2(digits: IntArray): IntArray {
    val n = digits.size
    for (i in n - 1 downTo 0) {
        if (digits[i] < 9) {
            digits[i]++
            return digits
        }
        digits[i] = 0
    }
    val ints = IntArray(n + 1)
    ints[0] = 1
    return ints
}

fun main() {
    println(Arrays.toString(plusOne(intArrayOf(0))))
    println(Arrays.toString(plusOne(intArrayOf(1, 2, 3))))
    println(Arrays.toString(plusOne(intArrayOf(9, 9, 9))))

    println(Arrays.toString(plusOne2(intArrayOf(0))))
    println(Arrays.toString(plusOne2(intArrayOf(1, 2, 3))))
    println(Arrays.toString(plusOne2(intArrayOf(9, 9, 9))))
}